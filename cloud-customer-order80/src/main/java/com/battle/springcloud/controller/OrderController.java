package com.battle.springcloud.controller;

import com.cloud.commons.entity.CommonResult;
import com.cloud.commons.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {
    private static final String URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping("/consumer/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        //去调用Payment的服务接口
        log.info("调用支付服务的接口");
        return restTemplate.postForObject(URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/{id}")
    public CommonResult<Payment> getById(@PathVariable("id")Integer id){
        log.info("调用支付服务的接口，查询订单");
        return restTemplate.getForObject(URL+"/payment/"+id,CommonResult.class);
    }
}
