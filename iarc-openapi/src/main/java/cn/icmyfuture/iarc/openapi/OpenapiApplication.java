package cn.icmyfuture.iarc.openapi;

import cn.icmyfuture.iarc.openapi.netty.NettyHttpServer;
import cn.icmyfuture.iarc.openapi.netty.annotation.NettyHttpHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
/**
 * 扫描NettyHttpHandler上的注解
 */
@ComponentScan(includeFilters = @ComponentScan.Filter(NettyHttpHandler.class))
public class OpenapiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(OpenapiApplication.class, args);
//        若不使用配置则使用以下代码，实现无tomcat启动
//        SpringApplication app = new SpringApplication(OpenapiApplication.class);
//        app.setWebEnvironment(false);
//        app.run(args);
    }

    @Autowired
    NettyHttpServer nettyHttpServer;

    @Override
    public void run(String... args) {
        nettyHttpServer.start();
//        ChannelFuture start = nettyServer.start();
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> nettyServer.destroy()));
//        start.channel().closeFuture().syncUninterruptibly();
    }
}
