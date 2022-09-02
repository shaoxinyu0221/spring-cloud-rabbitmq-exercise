package com.employeeservice.dao.mysql;

import com.employeeservice.bean.ReceivedMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author 邵鑫雨
 * @version 1.0
 * @description: TODO
 * @date 2022/9/2 20:29
 */
@Repository
public interface ReceiveMessageMysqlDao extends JpaRepository<ReceivedMessage,Long> {
}
