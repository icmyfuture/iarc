package cn.icmyfuture.iarc.openapi;

import cn.icmyfuture.iarc.openapi.netty.NettyServer;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OpenapiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(OpenapiApplication.class, args);
//        若不使用配置则使用以下代码，实现无tomcat启动
//        SpringApplication app = new SpringApplication(OpenapiApplication.class);
//        app.setWebEnvironment(false);
//        app.run(args);
    }

    @Autowired
    NettyServer nettyServer;

    @Override
    public void run(String... args) {
        ChannelFuture start = nettyServer.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> nettyServer.destroy()));
        start.channel().closeFuture().syncUninterruptibly();
    }
}
