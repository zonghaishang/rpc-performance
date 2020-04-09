/**
 * Apache License
 * <p>
 * http://code.google.com/p/nfs-rpc (c) 2011
 */
package com.opensource.rpc.benchmark;

/**
 * @author yiji
 * @version : AbstractExchangeClientRunnable.java, v 0.1 2020年04月09日 7:25 下午 yiji Exp $
 */
public abstract class AbstractExchangeRunnable extends AbstractClientRunnable {

    protected ProxyFactory serviceFactory = new ProxyFactory();

    public AbstractExchangeRunnable(InvokeOption option) {
        super(option);
        this.serviceFactory.setTargetIP(option.targetIP);
        this.serviceFactory.setConnections(option.connections);
        this.serviceFactory.setTargetPort(option.targetPort);
        this.serviceFactory.setTimeout(option.rpcTimeout);
    }
}