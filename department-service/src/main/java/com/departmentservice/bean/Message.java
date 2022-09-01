package com.departmentservice.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author 邵鑫雨
 * @version 1.0
 * @description: TODO
 * @date 2022/9/1 22:43
 */
@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "exchange")
    private String exchange;
    @Basic
    @Column(name = "routing_key")
    private String routingKey;
    @Basic
    @Column(name = "content")
    private String content;
    @Basic
    @Column(name = "retry_count")
    private int retryCount;
    @Basic
    @Column(name = "status")
    private String status;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (id != message.id) return false;
        if (retryCount != message.retryCount) return false;
        if (exchange != null ? !exchange.equals(message.exchange) : message.exchange != null) return false;
        if (routingKey != null ? !routingKey.equals(message.routingKey) : message.routingKey != null) return false;
        if (content != null ? !content.equals(message.content) : message.content != null) return false;
        if (status != null ? !status.equals(message.status) : message.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (exchange != null ? exchange.hashCode() : 0);
        result = 31 * result + (routingKey != null ? routingKey.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + retryCount;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
