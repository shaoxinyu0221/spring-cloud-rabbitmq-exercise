package com.employeeservice.bean;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author 邵鑫雨
 * @version 1.0
 * @description: TODO
 * @date 2022/9/2 20:15
 */
@Entity
@Table(name = "received_message", schema = "employee_service_db")
public class ReceivedMessage {
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "received_at")
    private Timestamp receivedAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(Timestamp receivedAt) {
        this.receivedAt = receivedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReceivedMessage that = (ReceivedMessage) o;

        if (id != that.id) return false;
        if (receivedAt != null ? !receivedAt.equals(that.receivedAt) : that.receivedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (receivedAt != null ? receivedAt.hashCode() : 0);
        return result;
    }
}
