package com.battle.springcloud.service;

import com.cloud.commons.entity.CommonResult;
import com.cloud.commons.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping("/payment/{id}")
    public CommonResult<Payment> findById(@PathVariable("id") Integer id);
}
