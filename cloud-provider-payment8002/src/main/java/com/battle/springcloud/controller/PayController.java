package com.battle.springcloud.controller;
import com.battle.springcloud.service.PaymentService;
import com.cloud.commons.entity.CommonResult;
import com.cloud.commons.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PayController {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/payment/create")
    public CommonResult<Payment> createPayment(@RequestBody Payment payment){
        Integer i = paymentService.create(payment);
        if (i > 0){
            return new CommonResult(200,"插入数据成功,serverPort="+serverPort,i);
        }
        return new CommonResult(404,"数据插入失败",i);
    }
    @GetMapping("/payment/{id}")
    public CommonResult<Payment> findById(@PathVariable("id") Integer id){
       Payment payment = paymentService.findById(id);
       if (payment != null){
           return new CommonResult(200,"数据查询成功,serverPort="+serverPort,payment);
       }
       return new CommonResult<>(404,"未查询到数据",null);
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("***** element:"+element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }
}
