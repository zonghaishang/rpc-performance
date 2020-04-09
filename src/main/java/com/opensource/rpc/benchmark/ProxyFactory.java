/**
 * Apache License
 * <p>
 * http://code.google.com/p/nfs-rpc (c) 2011
 */
package com.opensource.rpc.benchmark;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yiji
 * @version : ServiceFactory.java, v 0.1 2020年04月09日 6:46 下午 yiji Exp $
 */
public class ProxyFactory<T> {

    String targetIP = null;

    int targetPort = 0;

    int timeout = 0;

    int connections = 0;

    public String getTargetIP() {
        return targetIP;
    }

    public void setTargetIP(String targetIP) {
        this.targetIP = targetIP;
    }

    public int getTargetPort() {
        return targetPort;
    }

    public void setTargetPort(int targetPort) {
        this.targetPort = targetPort;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getConnections() {
        return connections;
    }

    public void setConnections(int connections) {
        this.connections = connections;
    }

    // Cache ExchangeClient
    private static ConcurrentHashMap<String, Object> services = new ConcurrentHashMap<String, Object>();

    @SuppressWarnings("unchecked")
    public T getReference(final Class<T> targetInterface) {
        String key = targetInterface.getName();
        if (!services.containsKey(key)) {
            T service = createClient(targetInterface, targetIP, targetPort, timeout, connections);
            services.put(key, service);
        }
        return (T) services.get(key);
    }

    protected T createClient(Class<T> targetInterface, String targetIP, int targetPort, int timeout, int connections) {
        ReferenceConfig<T> referenceConfig = new ReferenceConfig<T>();
        referenceConfig.setInterface(targetInterface);

        referenceConfig.setConnections(connections);

        ApplicationConfig application = new ApplicationConfig();
        application.setName("benchmark_client");
        referenceConfig.setApplication(application);

        StringBuilder url = new StringBuilder();
        url.append("dubbo://").append(targetIP).append(":").append(targetPort).append("/").append(targetInterface.getName());
        referenceConfig.setUrl(url.toString());

        referenceConfig.setTimeout(timeout);

        return referenceConfig.get();
    }

}