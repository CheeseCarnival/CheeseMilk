package com.cheeseocean.message.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import com.cheeseocean.message.api.MessageService;
import com.cheeseocean.message.api.SendRequest;
import com.cheeseocean.message.api.SendResult;

@DubboService
@Service
public class MessageServiceImpl implements MessageService {
    @Override
    public SendResult sendMessage(SendRequest request) {
        System.out.println("request:" + request);
        return new SendResult(200, "send successful");
    }
}
