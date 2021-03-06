package cn.icmyfuture.iarc.openapi.netty;

import cn.icmyfuture.iarc.openapi.netty.handler.io.FilterLoggingHandler;
import cn.icmyfuture.iarc.openapi.netty.handler.io.HttpServerHandler;
import cn.icmyfuture.iarc.openapi.netty.handler.io.InterceptorHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioChannelOption;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class NettyHttpServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyHttpServer.class);

    @Value("${server.port}")
    private int port;

    @Resource
    private FilterLoggingHandler filterLoggingHandler;

    @Resource
    private InterceptorHandler interceptorHandler;

    @Resource
    private HttpServerHandler httpServerHandler;

    public void start() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        //boss事件轮询线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //worker事件轮询线程组
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        //channel的属性配置
        bootstrap.childOption(NioChannelOption.TCP_NODELAY, true);//是否使用fullrequest fullresponse发送数据
        bootstrap.childOption(NioChannelOption.SO_REUSEADDR, true);  //是否允许端口占用
        bootstrap.childOption(NioChannelOption.SO_KEEPALIVE, false);//是否设置长连接
        bootstrap.childOption(NioChannelOption.SO_RCVBUF, 2048); //设置接收数据大小
        bootstrap.childOption(NioChannelOption.SO_SNDBUF, 2048);//设置发送数据大小
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) {
                ch.pipeline().addLast("codec", new HttpServerCodec());//http编解码器
                //对httpmsg进行聚合 转化为fullHttpRequest 或者fullHttpResponse并设置最大数据长度
                ch.pipeline().addLast("aggregator", new HttpObjectAggregator(512 * 1024));
                ch.pipeline().addLast("logging", filterLoggingHandler);//日志
                ch.pipeline().addLast("interceptor", interceptorHandler);//拦截器配置
                ch.pipeline().addLast("bizHandler", httpServerHandler); //请求匹配处理
            }
        })
        ;

        ChannelFuture channelFuture = bootstrap.bind(port).syncUninterruptibly().addListener(future -> {
            String logBanner = "\n\n" +
                    "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n" +
                    "*                                                                                   *\n" +
                    "*                                                                                   *\n" +
                    "*                   Netty Http Server started on port {}                            *\n" +
                    "*                                                                                   *\n" +
                    "*                                                                                   *\n" +
                    "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n";
            LOGGER.info(logBanner, port);
        });
        //通过引入监听器对象监听future状态，当future任务执行完成后会调用-》{}内的方法
        channelFuture.channel().closeFuture().addListener(future -> {
            /**
             * 优雅关闭
             */
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            LOGGER.info("Netty Http Server Start Shutdown ............");
        });
    }
}