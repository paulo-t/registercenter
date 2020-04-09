package com.paulo.springcloud.registercenter.event;

import com.netflix.appinfo.InstanceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author: create by paulo
 * @version: v1.0
 * @description: com.paulo.springcloud.registercenter.event
 * @date:2020/4/9
 */
@Component
@Slf4j
public class EurekaStateChangeListener {
    @EventListener
    public void listen(EurekaInstanceCanceledEvent event){
        //服务下线
        String appName = event.getAppName();
        String serverId = event.getServerId();
        log.info("{}服务下线,id:{},isReplication:{}",appName,serverId,event.isReplication());
    }

    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event){
        //服务注册
        InstanceInfo instanceInfo = event.getInstanceInfo();
        log.info("{}服务注册, id:{}, isReplication:{}",instanceInfo.getAppName(),instanceInfo.getInstanceId(),event.isReplication());
    }

    @EventListener
    public void listen(EurekaInstanceRenewedEvent event){
        //服务更新
        log.info("{}服务更新, id:{}, isReplication:{}",event.getAppName(),event.getServerId(),event.isReplication());
    }

    @EventListener
    public void listen(EurekaRegistryAvailableEvent event){
        //服务可用
        log.info("服务可用:{}",event.getSource());
    }

    @EventListener
    public void listen(EurekaServerStartedEvent event){
        //服务启动
        log.info("服务启动:{}",event.getSource());
    }

}
