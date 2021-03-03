package com.battle.springcloud;

import com.battle.myrule.MyselfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MyselfRule.class)
public class Order80ApplicationRibbon {
    public static void main(String[] args) {
        SpringApplication.run(Order80ApplicationRibbon.class);
    }
}
