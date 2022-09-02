package com.departmentservice.taskutil;

import com.departmentservice.bean.Message;
import com.departmentservice.dao.MessageMysqlDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 邵鑫雨
 * @version 1.0
 * @description: TODO
 * @date 2022/9/1 23:23
 */
@Component
@Slf4j
@Transactional
public class TaskSendMessage {

    @Resource
    private MessageMysqlDao messageMysqlDao;
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Scheduled(cron="*/6 * * * * ?")
    public void sendMessage(){
        log.info("定时任务执行了");
        List<Message> messages = messageMysqlDao.getAllMessageNeedSend();

        if (messages.size() == 0){
            log.info("没有需要发送的消息");
            return;
        }

        for (Message message : messages) {
            String exchange = message.getExchange();
            String content = message.getContent();
            String routingKey = message.getRoutingKey();

            content = message.getId().toString() + ":" + content;
            CorrelationData correlationData = new CorrelationData(message.getId().toString());
            if (StringUtils.isEmpty(exchange)){
                rabbitTemplate.convertAndSend(routingKey,(Object) content,correlationData);
            }else {
                rabbitTemplate.convertAndSend(exchange,routingKey,content,correlationData);
            }

        }

    }

}
