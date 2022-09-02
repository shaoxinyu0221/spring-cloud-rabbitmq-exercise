package com.departmentservice;

import com.departmentservice.bean.Message;
import com.departmentservice.dao.DepartmentMysqlDao;
import com.departmentservice.dao.MessageMysqlDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

@SpringBootApplication
@EnableRabbit
@Slf4j
@Transactional
@EnableScheduling
public class DepartmentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DepartmentServiceApplication.class, args);
    }

    @Resource
    private MessageMysqlDao messageMysqlDao;

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);

        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            log.info("消息到达rabbitTemplate");
            assert correlationData != null;
            String id = correlationData.getId();
            assert id != null;
            Optional<Message> optional = messageMysqlDao.findById(Long.parseLong(id));
            Message message = optional.orElseThrow(RuntimeException::new);
            //如果消息送达,修改消息的状态为B
            if (ack){
                message.setStatus("B");
            }
            //不管送没送达,发消息次数减一
            message.setRetryCount(message.getRetryCount()-1);
            messageMysqlDao.save(message);
        });

        return rabbitTemplate;
    }


}
