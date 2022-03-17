package com.cheeseocean.community;

import org.apache.dubbo.config.ReferenceConfig;

import com.cheeseocean.message.api.MessageService;

public class CommunityApplication {
    public static void main(String[] args) {
        ReferenceConfig referenceConfig = new ReferenceConfig();
        referenceConfig.setInterface(MessageService.class);
    }
}
