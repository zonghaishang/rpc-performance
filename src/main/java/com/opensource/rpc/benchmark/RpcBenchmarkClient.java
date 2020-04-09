/**
 * Apache License
 * <p>
 * http://code.google.com/p/nfs-rpc (c) 2011
 */
package com.opensource.rpc.benchmark;

/**
 * @author yiji
 * @version : RpcBenchmarkClient.java, v 0.1 2020年04月09日 7:58 下午 yiji Exp $
 */
public class RpcBenchmarkClient extends AbstractBenchmarkClient {

    @Override
    public ClientRunnable getClientRunnable(InvokeOption option)
            throws Exception {
        Class[] parameterTypes = new Class[] {InvokeOption.class};
        Object[] parameters = new Object[] {option};
        return (ClientRunnable) Class.forName(option.targetInterface).getConstructor(parameterTypes).newInstance(parameters);
    }

    public static void main(String[] args) throws Exception {
        new RpcBenchmarkClient().run(args);
    }
}