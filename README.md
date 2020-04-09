### 1.新创建压测工程

```shell
mvn archetype:generate -DgroupId=com.demo.rpc.benchmark -DartifactId=rpc-test -DarchetypeArtifactId=maven-archetype-quickstart
```

### 2.导入压测maven依赖

```xml
<dependency>
    <groupId>com.opensource.rpc.benchmark</groupId>
    <artifactId>rpc-performance</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### 3.开发客户端调用实现类

```java
public class BenchmarkRandom_1k extends AbstractExchangeRunnable {

    public BenchmarkRandom_1k(InvokeOption option) {
        super(option);
    }

    @Override
    public Object doInvoke() {
        BenchmarkService benchmarkService = (BenchmarkService) serviceFactory.getReference(BenchmarkService.class);
        return benchmarkService.send_1k(randomString(1024));
    }
}
```

4、将工程打包，比如打包后名为`rpc-test.jar`，解压缩rpc.benchmark-xx.tar.gz, 放入解压目录`rpc.benchmark/lib`中

5、运行run.bat(windows)或run.sh(linux)执行压测
