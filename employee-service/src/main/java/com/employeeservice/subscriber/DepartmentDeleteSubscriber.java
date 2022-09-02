package com.employeeservice.subscriber;

import com.employeeservice.bean.ReceivedMessage;
import com.employeeservice.dao.mysql.EmployeeMysqlDao;
import com.employeeservice.dao.mysql.ReceiveMessageMysqlDao;
import com.example.departmentserviceapi.client.MessageFeignClient;
import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author 邵鑫雨
 * @version 1.0
 * @description: TODO
 * @date 2022/9/2 20:08
 */
@Component
@Slf4j
@Transactional
public class DepartmentDeleteSubscriber {

    @Resource
    private ReceiveMessageMysqlDao receiveMessageMysqlDao;
    @Resource
    private EmployeeMysqlDao employeeMysqlDao;
    @Resource
    private MessageFeignClient messageFeignClient;

    @SneakyThrows
    @RabbitListener(queues = "department.delete")
    public void deleteEmpByDeptId(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag){
        String[] array = StringUtils.tokenizeToStringArray(message, ":");
        if (array==null || array.length!=2){
            channel.basicAck(tag,false);
            log.info("当前数组拆分长度不为2,逻辑错误");
            throw new RuntimeException("接收消息有误");
        }

        //向数据库中存接收到的数据,以此判断是否为重复数据
        ReceivedMessage receivedMessage = new ReceivedMessage();
        receivedMessage.setId(Long.parseLong(array[0]));
        receivedMessage.setReceivedAt(new Timestamp(new Date().getTime()));
        try {
            receiveMessageMysqlDao.save(receivedMessage);
        } catch (Exception e) {
            channel.basicAck(tag,false);
            log.info("重复消息");
            throw new RuntimeException(e);
        }

        //如果不是重复消息,执行业务逻辑
        String departmentId = array[1];
        employeeMysqlDao.deleteByDepartmentId(Long.parseLong(departmentId));

        //通知department微服务,将修改消息状态为C
        messageFeignClient.modifyMessageStatus(Long.parseLong(array[1]));
        channel.basicAck(tag,false);
        log.info("已经发出通知,让department微服务修改状态");
    }

}
