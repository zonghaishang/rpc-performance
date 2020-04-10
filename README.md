一. fork压测模板工程

```shell
git clone https://github.com/zonghaishang/rpc-performance-test.git
```

按照压测模板工程指引，开发待压测的接口。

二、执行压测

```shell
cd rpc.benchmark
./benchmark.sh
usage: benchmark.sh [options]
 -b,--target <arg>         target benchmark runnable class
 -c,--threads <arg>        The number of concurrent
 -d,--duration <arg>       benchmark running duration(seconds)
 -h,--host <arg>           server host or address
 -p,--port <arg>           server port
 -t,--timeout <arg>        rpc request timeout(milliseconds)
 -tc,--connections <arg>   The number of tcp connection
 -w,--warm-up <arg>        warm up duration(seconds)

# 开始压测, 为了测试结果更准确，建议warm up 30秒.
# 压测工具默认会不统计最后调用10秒
# eg:
./benchmark.sh -b BenchmarkRandom_1k -h 127.0.0.1 -p 20880 -d 300 -w 30
```

压测执行完后，输出：

```
----------Benchmark Statistics--------------
 concurrents: 1
 connections: 1
 Running: 300 seconds
 Benchmark Time: 261
 Requests: 1645754 Success: 100% (1645754) Error: 0% (0)
 Avg TPS: 6086 Max TPS: 7481 Min TPS: 3383
 Avg RT: 0.164ms
 RT <= 0: 99% 1644860/1645754
 RT (0,1]: 0% 384/1645754
 RT (1,5]: 0% 503/1645754
 RT (5,10]: 0% 3/1645754
 RT (10,50]: 0% 3/1645754
 RT (50,100]: 0% 0/1645754
 RT (100,500]: 0% 1/1645754
 RT (500,1000]: 0% 0/1645754
 RT > 1000: 0% 0/1645754
```

