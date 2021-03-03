package com.battle.myrule;

import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MyselfRule {
    @Bean
    public IRule iRule(){
        //return new RandomRule(); LB策略 随机数法
        //return new RoundRobinRule(); 轮询
        return new WeightedResponseTimeRule();
    }
}
