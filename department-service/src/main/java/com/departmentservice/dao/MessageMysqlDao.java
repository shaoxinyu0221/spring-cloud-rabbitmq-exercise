package com.departmentservice.dao;

import com.departmentservice.bean.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 邵鑫雨
 * @version 1.0
 * @description: TODO
 * @date 2022/9/1 22:58
 */
@Repository
public interface MessageMysqlDao extends JpaRepository<Message,Long> {

    List<Message> findAllByStatusNotAndRetryCountGreaterThan(String status,Integer count);

    default List<Message> getAllMessageNeedSend(){
        return findAllByStatusNotAndRetryCountGreaterThan("C",0);
    }

}
