/**
 * Apache License
 * <p>
 * http://code.google.com/p/nfs-rpc (c) 2011
 */
package com.opensource.rpc.benchmark;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author yiji
 * @version : AbstractClientRunnable.java, v 0.1 2020年04月09日 6:41 下午 yiji Exp $
 */
public abstract class AbstractClientRunnable implements ClientRunnable {

    private static final Logger LOGGER = LogManager.getLogger(AbstractClientRunnable.class);

    private CyclicBarrier barrier;

    private CountDownLatch latch;

    private long endTime;

    private boolean running = true;

    // response time spread
    private long[] responseSpreads = new long[9];

    // error request per second
    private long[] errorTPS = null;

    // error response times per second
    private long[] errorResponseTimes = null;

    // tps per second
    private long[] tps = null;

    // response times per second
    private long[] responseTimes = null;

    // benchmark startTime
    private long startTime;

    // benchmark maxRange
    private int maxRange;

    protected InvokeOption option;

    public AbstractClientRunnable(InvokeOption option) {
        this.option = option;
        this.barrier = this.option.barrier;
        this.latch = this.option.latch;
        this.startTime = this.option.startTime;
        this.endTime = this.option.endTime;
        maxRange = (Integer.parseInt(String.valueOf((endTime - startTime))) / 1000000) + 1;
        errorTPS = new long[maxRange];
        errorResponseTimes = new long[maxRange];
        tps = new long[maxRange];
        responseTimes = new long[maxRange];
        // init
        for (int i = 0; i < maxRange; i++) {
            errorTPS[i] = 0;
            errorResponseTimes[i] = 0;
            tps[i] = 0;
            responseTimes[i] = 0;
        }
    }

    public void run() {
        try {
            barrier.await();
        } catch (Exception e) {
            // IGNORE
        }
        runJavaAndHessian();
        latch.countDown();
    }

    private void runJavaAndHessian() {
        while (running) {
            long beginTime = getCurrentTime();
            if (beginTime >= endTime) {
                running = false;
                break;
            }

            try {
                Object result = doInvoke();
                long currentTime = getCurrentTime();
                // warm up ??
                if (beginTime <= startTime) {
                    continue;
                }
                long consumeTime = currentTime - beginTime;
                sumResponseTimeSpread(consumeTime);
                int offset = Integer.parseInt(String.valueOf(beginTime - startTime)) / 1000000;
                if (offset >= maxRange) {
                    LOGGER.error("benchmark range exceeds maxRange,range is: " + offset + ",maxRange is: " + maxRange);
                    continue;
                }

                if (result != null) {
                    tps[offset] = tps[offset] + 1;
                    responseTimes[offset] = responseTimes[offset] + consumeTime;
                } else {
                    LOGGER.error("server return result is null");
                    errorTPS[offset] = errorTPS[offset] + 1;
                    errorResponseTimes[offset] = errorResponseTimes[offset] + consumeTime;
                }
            } catch (Exception e) {
                LOGGER.error("failed to trigger doInvoke", e);
                long currentTime = getCurrentTime();
                // warm up ??
                if (beginTime <= startTime) {
                    continue;
                }
                long consumeTime = currentTime - beginTime;
                sumResponseTimeSpread(consumeTime);
                int offset = Integer.parseInt(String.valueOf(beginTime - startTime)) / 1000000;
                if (offset >= maxRange) {
                    LOGGER.error("benchmark range exceeds maxRange,range is: " + offset + ",maxRange is: " + maxRange);
                    continue;
                }
                errorTPS[offset] = errorTPS[offset] + 1;
                errorResponseTimes[offset] = errorResponseTimes[offset] + consumeTime;
            }
        }
    }

    private long getCurrentTime() {
        return System.nanoTime() / 1000L;
    }

    public abstract Object doInvoke();

    public List<long[]> getResults() {
        List<long[]> results = new ArrayList<long[]>();
        results.add(responseSpreads);
        results.add(tps);
        results.add(responseTimes);
        results.add(errorTPS);
        results.add(errorResponseTimes);
        return results;
    }

    private void sumResponseTimeSpread(long responseTime) {
        // 更精确统计落在时间range计数
        double rt = responseTime;

        if (rt <= 0) {
            responseSpreads[0] = responseSpreads[0] + 1;
        } else if (rt <= T_1_MICROS) {
            responseSpreads[1] = responseSpreads[1] + 1;
        } else if (rt <= T_5_MICROS) {
            responseSpreads[2] = responseSpreads[2] + 1;
        } else if (rt <= T_10_MICROS) {
            responseSpreads[3] = responseSpreads[3] + 1;
        } else if (rt <= T_50_MICROS) {
            responseSpreads[4] = responseSpreads[4] + 1;
        } else if (rt <= T_100_MICROS) {
            responseSpreads[5] = responseSpreads[5] + 1;
        } else if (rt <= T_500_MICROS) {
            responseSpreads[6] = responseSpreads[6] + 1;
        } else if (rt <= T_1000_MICROS) {
            responseSpreads[7] = responseSpreads[7] + 1;
        } else {
            responseSpreads[8] = responseSpreads[8] + 1;
        }
    }

    private static final long T_1_MICROS    = TimeUnit.MILLISECONDS.toMicros(1);
    private static final long T_5_MICROS    = TimeUnit.MILLISECONDS.toMicros(5);
    private static final long T_10_MICROS   = TimeUnit.MILLISECONDS.toMicros(10);
    private static final long T_50_MICROS   = TimeUnit.MILLISECONDS.toMicros(50);
    private static final long T_100_MICROS  = TimeUnit.MILLISECONDS.toMicros(100);
    private static final long T_500_MICROS  = TimeUnit.MILLISECONDS.toMicros(500);
    private static final long T_1000_MICROS = TimeUnit.MILLISECONDS.toMicros(1000);

}