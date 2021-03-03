package com.battle.springcloud.controller;

import com.battle.springcloud.service.PaymentFeignService;
import com.cloud.commons.entity.CommonResult;
import com.cloud.commons.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {

    private static final String URL = "CLOUD-PAYMENT-SERVICE";
    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/{id}")
    public CommonResult<Payment> payment(@PathVariable("id") Integer id){
        log.info("查询订单接口");
        return  paymentFeignService.findById(id);
    }

}
