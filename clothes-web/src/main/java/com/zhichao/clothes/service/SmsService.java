package com.zhichao.clothes.service;

public interface SmsService {

    void sendCode(String phone, String code);
}
