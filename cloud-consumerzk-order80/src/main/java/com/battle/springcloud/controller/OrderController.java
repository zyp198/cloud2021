package com.battle.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class OrderController {
    private static final String URL = "http://localhost:8004";
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/zk")
    public String payment(){
        return String.valueOf(restTemplate.getForObject(URL+"/payment/zk", String.class));
    }
}
