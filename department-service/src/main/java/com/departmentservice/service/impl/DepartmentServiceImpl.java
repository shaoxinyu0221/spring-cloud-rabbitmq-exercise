package com.departmentservice.service.impl;

import com.departmentservice.bean.Message;
import com.departmentservice.dao.DepartmentMysqlDao;
import com.departmentservice.dao.MessageMysqlDao;
import com.departmentservice.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author 邵鑫雨
 * @version 1.0
 * @description: TODO
 * @date 2022/9/1 22:08
 */
@Service
@Slf4j
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentMysqlDao departmentMysqlDao;
    @Resource
    private MessageMysqlDao messageMysqlDao;

    @Override
    public void deleteById(Long id) {
        departmentMysqlDao.deleteById(id);

        //将消息存放到表中
        Message message = new Message(null,null,"department.delete",id.toString(),5,"A");
        messageMysqlDao.save(message);
    }
}
