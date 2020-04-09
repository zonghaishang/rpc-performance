/**
 * Apache License
 * <p>
 * http://code.google.com/p/nfs-rpc (c) 2011
 */
package com.opensource.rpc.benchmark;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @author yiji
 * @version : ClientOption.java, v 0.1 2020年04月09日 8:02 下午 yiji Exp $
 */
public class InvokeOption {

    String         targetInterface;
    String         targetIP;
    int            targetPort;
    int            connections;
    int            rpcTimeout;
    long           startTime;
    long           endTime;
    CyclicBarrier  barrier;
    CountDownLatch latch;

    public InvokeOption(String targetInterface, String targetIP, int targetPort, int connections, int rpcTimeout,
                        CyclicBarrier barrier, CountDownLatch latch, long startTime, long endTime) {
        this.targetInterface = targetInterface;
        this.targetIP = targetIP;
        this.targetPort = targetPort;
        this.connections = connections;
        this.rpcTimeout = rpcTimeout;
        this.barrier = barrier;
        this.latch = latch;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}