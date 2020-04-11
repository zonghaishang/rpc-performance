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

```shell
----------Benchmark Statistics--------------
 concurrents: 1
 connections: 1
 Running: 300s
 Benchmark Time: 261s
 Requests: 1365162 Success: 100% (1365162) Error: 0% (0)
 Avg TPS: 5052 Max TPS: 5734 Min TPS: 2172
 Avg RT: 0.197ms
 RT <= 0: 0% 0/1365162
 RT (0,1]: 99% 1362740/1365162
 RT (1,5]: 0% 2351/1365162
 RT (5,10]: 0% 49/1365162
 RT (10,50]: 0% 17/1365162
 RT (50,100]: 0% 2/1365162
 RT (100,500]: 0% 3/1365162
 RT (500,1000]: 0% 0/1365162
 RT > 1000: 0% 0/1365162
```

