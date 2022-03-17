package com.cheeseocean.message.service;

import com.cheeseocean.message.api.MessageService;
import com.cheeseocean.message.api.SendRequest;
import com.cheeseocean.message.api.SendResult;

public class MessageServiceImpl implements MessageService {
    @Override
    public SendResult sendMessage(SendRequest request) {
        System.out.println("request:" + request);
        return new SendResult(200, "send successful");
    }
}
