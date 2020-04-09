/**
 * Apache License
 * <p>
 * http://code.google.com/p/nfs-rpc (c) 2011
 */
package com.opensource.rpc.benchmark;

import java.util.List;

/**
 * @author yiji
 * @version : ClientRunnable.java, v 0.1 2020年04月09日 3:48 下午 yiji Exp $
 */
public interface ClientRunnable extends Runnable {
    
    List<long[]> getResults();

}