/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.opensource.rpc.benchmark;

import com.alipay.sofa.rpc.common.RpcConstants;
import com.alipay.sofa.rpc.config.ApplicationConfig;
import com.alipay.sofa.rpc.config.ConsumerConfig;

/**
 * @author yiji
 * @version : BoltProxyFactory.java, v 0.1 2020年04月21日 4:06 下午 yiji Exp $
 */
public class BoltProxyFactory<T> extends ProxyFactory<T> {

    protected T createClient(Class<T> targetInterface, String targetIP, int targetPort, int timeout, int connections) {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setInterfaceId(targetInterface.getName());
        consumerConfig.setDirectUrl("bolt://" + targetIP + ":" + targetPort);
        consumerConfig.setProtocol(RpcConstants.PROTOCOL_TYPE_BOLT);
        consumerConfig.setBootstrap("bolt");
        consumerConfig.setApplication(new ApplicationConfig().setAppName("benchmark_client"));
        consumerConfig.setReconnectPeriod(1000);
        consumerConfig.setTimeout(timeout);
        consumerConfig.setConnectionNum(connections);

        return (T) consumerConfig.refer();
    }

}
