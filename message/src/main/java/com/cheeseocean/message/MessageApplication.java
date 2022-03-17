package com.cheeseocean.message;

import java.util.HashMap;
import java.util.Map;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

import com.cheeseocean.message.api.MessageService;
import com.cheeseocean.message.service.MessageServiceImpl;

public class MessageApplication {

    public static void main(String[] args) {
        System.setProperty("dubbo.application.logger", "slf4j");
        System.setProperty("native", "true");
        ServiceConfig<MessageServiceImpl> service = new ServiceConfig<>();
        service.setInterface(MessageService.class);
        service.setRef(new MessageServiceImpl());

        DubboBootstrap bootstrap = DubboBootstrap.getInstance();

        ApplicationConfig applicationConfig = new ApplicationConfig("cheese-message-api-provider");
        applicationConfig.setQosEnable(false);
        applicationConfig.setCompiler("jdk");
        Map<String, String> m = new HashMap<>(1);
        m.put("proxy", "jdk");
        applicationConfig.setParameters(m);

        bootstrap.application(applicationConfig)
                .registry(new RegistryConfig("nacos://korea.cheeseocean.com:8848"))
                .protocol(new ProtocolConfig(CommonConstants.DUBBO, -1))
                .service(service)
                .start()
                .await();
    }
}
