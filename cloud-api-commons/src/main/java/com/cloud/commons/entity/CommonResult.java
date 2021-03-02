package com.cloud.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String massage;
    private T data;
    public CommonResult(Integer code, String message){
        this(code,message,null);
    }
}
