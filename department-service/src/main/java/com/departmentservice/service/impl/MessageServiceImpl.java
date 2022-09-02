package com.departmentservice.service.impl;

import com.departmentservice.bean.Message;
import com.departmentservice.dao.MessageMysqlDao;
import com.departmentservice.service.MessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author 邵鑫雨
 * @version 1.0
 * @description: TODO
 * @date 2022/9/2 20:36
 */
@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageMysqlDao messageMysqlDao;

    @Override
    public void modifyStatus(Long id) {
        Message message = messageMysqlDao.getOne(id);
        message.setStatus("C");
        messageMysqlDao.save(message);
    }
}
