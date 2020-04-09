/**
 * Apache License
 * <p>
 * http://code.google.com/p/nfs-rpc (c) 2011
 */
package com.opensource.rpc.benchmark;

/**
 * @author yiji
 * @version : Options.java, v 0.1 2020年04月09日 4:10 下午 yiji Exp $
 */
public class ParsedOption {

    /**
     * server host or ip
     */
    private String host;

    /**
     * server port
     */
    private int    port;

    /**
     * request timeout (milliseconds)
     */
    private int timeout;

    /**
     * benchmark duration (seconds)
     */
    private int duration;

    /**
     * The number of concurrent
     */
    private int threads;

    /**
     * The number of tcp connections
     */
    private int connections;

    /**
     *  save detail to benchmark.all.results file ?
     */
    private boolean writeStatistics;

    /**
     * benchmark target class
     */
    private String targetInterface;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public int getConnections() {
        return connections;
    }

    public void setConnections(int connections) {
        this.connections = connections;
    }

    public boolean isWriteStatistics() {
        return writeStatistics;
    }

    public void setWriteStatistics(boolean writeStatistics) {
        this.writeStatistics = writeStatistics;
    }

    public String getTargetInterface() {
        return targetInterface;
    }

    public void setTargetInterface(String targetInterface) {
        this.targetInterface = targetInterface;
    }
}