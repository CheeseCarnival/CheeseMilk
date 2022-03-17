package com.cheeseocean.message.api;

public class MessageServiceStub implements MessageService{

    private MessageService messageService;

    public MessageServiceStub(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public SendResult sendMessage(SendRequest request) {
        System.out.println("MessageServiceStub.sendMessage");
        return this.messageService.sendMessage(request);
    }
}
