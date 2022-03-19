package com.cheeseocean.community.service;

import org.apache.dubbo.rpc.service.Destroyable;

import com.cheeseocean.message.api.MessageService;
import com.cheeseocean.message.api.SendRequest;
import com.cheeseocean.message.api.SendResult;

public class MessageServiceLocal implements MessageService, Destroyable {

    private MessageService messageService;

    public MessageServiceLocal(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public SendResult sendMessage(SendRequest request) {
        System.out.println("MessageServiceLocal.sendMessage()");
        return messageService.sendMessage(request);
    }

    @Override
    public void $destroy() {
        //do sth
    }

    public void onDisconnect(){
        System.out.println("MessageServiceLocal.onDisconnect");
    }
}
