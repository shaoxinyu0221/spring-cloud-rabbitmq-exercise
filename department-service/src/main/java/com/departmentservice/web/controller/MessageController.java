package com.departmentservice.web.controller;

import com.departmentservice.service.MessageService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 邵鑫雨
 * @version 1.0
 * @description: TODO
 * @date 2022/9/2 20:36
 */
@RestController
public class MessageController {

    @Resource
    private MessageService messageService;

    @PostMapping("/message/{id}/update")
    public String modifyMessageStatus(@PathVariable("id") Long id){
        messageService.modifyStatus(id);
        return "success";
    }

}
