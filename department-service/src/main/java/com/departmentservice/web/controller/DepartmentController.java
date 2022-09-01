package com.departmentservice.web.controller;

import com.departmentservice.service.DepartmentService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 邵鑫雨
 * @version 1.0
 * @description: TODO
 * @date 2022/9/1 22:11
 */
@RestController
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;


    @PostMapping("/department/{id}/delete")
    public String deleteDepartment(@PathVariable("id") Long id){
        departmentService.deleteById(id);
        return "success";
    }
}
