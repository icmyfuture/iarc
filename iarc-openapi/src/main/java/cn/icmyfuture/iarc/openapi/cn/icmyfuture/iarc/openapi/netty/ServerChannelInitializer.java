package cn.icmyfuture.iarc.openapi.cn.icmyfuture.iarc.openapi.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * description: 通道初始化，主要用于设置各种Handler
 * author:
 * date: 2018-11-28 14:55
 **/
@Component
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    ServerChannelHandler serverChannelHandler;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //编码解码
        ch.pipeline().addLast(new HttpRequestDecoder());
        ch.pipeline().addLast(new HttpResponseEncoder());

        ch.pipeline().addLast(serverChannelHandler);//ChannelHandler
    }
}