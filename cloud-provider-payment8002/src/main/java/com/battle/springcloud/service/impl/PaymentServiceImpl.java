package com.battle.springcloud.service.impl;

import com.battle.springcloud.dao.PaymentDao;
import com.battle.springcloud.service.PaymentService;
import com.cloud.commons.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public Integer create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment findById(Integer id) {
        return paymentDao.getPaymentById(id);
    }
}
