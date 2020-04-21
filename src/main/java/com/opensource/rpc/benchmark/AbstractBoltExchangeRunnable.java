/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.opensource.rpc.benchmark;

/**
 * only for sofa rpc benchmark
 *
 * @author yiji
 * @version : AbstractBoltExchangeRunnable.java, v 0.1 2020年04月21日 4:05 下午 yiji Exp $
 */
public abstract class AbstractBoltExchangeRunnable extends AbstractClientRunnable {

    protected BoltProxyFactory serviceFactory = new BoltProxyFactory();

    public AbstractBoltExchangeRunnable(InvokeOption option) {
        super(option);
        this.serviceFactory.setTargetIP(option.targetIP);
        this.serviceFactory.setConnections(option.connections);
        this.serviceFactory.setTargetPort(option.targetPort);
        this.serviceFactory.setTimeout(option.rpcTimeout);
    }
}