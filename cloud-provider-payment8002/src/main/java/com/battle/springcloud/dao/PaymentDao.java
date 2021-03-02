package com.battle.springcloud.dao;

import com.cloud.commons.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
   // @Insert("insert into payment values(id,serial)")
    public Integer create(Payment payment);
    //@Select("select * from payment where id = #{id}")
    public Payment getPaymentById(@Param("id") Integer id);
}
