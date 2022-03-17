package com.cheeseocean.community;

import java.util.HashMap;
import java.util.Map;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

import com.cheeseocean.message.api.MessageService;
import com.cheeseocean.message.api.SendRequest;
import com.cheeseocean.message.api.SendResult;

public class CommunityApplication {
    public static void main(String[] args) {
        ReferenceConfig<MessageService> reference = new ReferenceConfig<>();
        reference.setInterface(MessageService.class);
        reference.setGeneric("false");
//        reference.setStub("com.cheeseocean.dubbo.consumer.CustomMessageServiceStub");
//        reference.setOnconnect("onConnect");
//        reference.setUrl("dubbo://127.0.0.1:20880/com.cheeseocean.dubbo.provider.MessageService");

        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        ApplicationConfig applicationConfig = new ApplicationConfig("cheese-message-api-consumer");
        applicationConfig.setQosEnable(false);
        applicationConfig.setCompiler("jdk");
        Map<String, String> m = new HashMap<>(1);
        m.put("proxy", "jdk");
        applicationConfig.setParameters(m);

        bootstrap.application(applicationConfig)
                .registry(new RegistryConfig("nacos://korea.cheeseocean.com:8848"))
                .protocol(new ProtocolConfig(CommonConstants.DUBBO, -1))
                .reference(reference)
                .start();

        MessageService messageService = bootstrap.getCache().get(reference);
        SendResult result = messageService.sendMessage(new SendRequest("root", "locahost", "korea.cheeseocean.com", "hello"));
        System.out.println(result);
    }
}
