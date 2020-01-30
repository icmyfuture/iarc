package cn.icmyfuture.iarc.openapi;

import cn.icmyfuture.iarc.openapi.netty.NettyHttpServer;
import cn.icmyfuture.iarc.openapi.netty.annotation.MethodHandler;
import cn.icmyfuture.iarc.openapi.netty.annotation.UriHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
/**
 * 扫描Handler上的注解
 */
@ComponentScan(includeFilters = @ComponentScan.Filter(UriHandler.class))
@ComponentScan(includeFilters = @ComponentScan.Filter(MethodHandler.class))
public class OpenAPIApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(OpenAPIApplication.class, args);
    }

    @Autowired
    NettyHttpServer nettyHttpServer;

    @Override
    public void run(String... args) {
        nettyHttpServer.start();
    }
}
