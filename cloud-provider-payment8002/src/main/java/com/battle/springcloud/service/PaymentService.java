package com.battle.springcloud.service;

import com.cloud.commons.entity.Payment;

public interface PaymentService {
    public Integer create(Payment payment);
    public Payment findById(Integer id);
}
