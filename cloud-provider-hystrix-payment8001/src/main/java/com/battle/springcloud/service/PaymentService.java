package com.battle.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class PaymentService {
    //成功
    public String paymentInfo_OK(Integer id){
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_OK,id：  "+id+"\t"+"哈哈哈"  ;
    }

    //失败
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
          @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
    })
    public String paymentInfo_TimeOut(Integer id){
        int timeNumber = 3;
        try { TimeUnit.SECONDS.sleep(timeNumber); }catch (Exception e) {log.info(e.getMessage());}
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_TimeOut,id：  "+id+"\t"+"呜呜呜"+" 耗时(秒)"+timeNumber;
    }

    //失败之后的调用方法
    public String paymentInfo_TimeOutHandler(Integer id){
        return "支付接口调用超时，id"+id+"当前线程为："+Thread.currentThread().getName();
    }
}
