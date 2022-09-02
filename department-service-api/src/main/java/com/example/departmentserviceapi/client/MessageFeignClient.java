package com.example.departmentserviceapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author 邵鑫雨
 * @version 1.0
 * @description: TODO
 * @date 2022/9/2 20:48
 */
@FeignClient(name = "department-service")
public interface MessageFeignClient {

    @PostMapping("/message/{id}/update")
    public String modifyMessageStatus(@PathVariable("id") Long id);

}
