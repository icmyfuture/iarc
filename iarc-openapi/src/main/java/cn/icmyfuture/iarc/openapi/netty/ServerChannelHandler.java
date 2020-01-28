package cn.icmyfuture.iarc.openapi.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class ServerChannelHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Netty Server receive msg : " + msg);
        if (msg instanceof HttpRequest) {
            //要返回的内容, Channel可以理解为连接，而连接中传输的信息要为ByteBuf
            ByteBuf content = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);
            //构造响应
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);

            //设置头信息的的MIME类型
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");  //内容类型
            //设置要返回的内容长度
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes()); //内容长度
            //将响应对象返回
            ctx.channel().writeAndFlush(response);
            ctx.close();
        } else {
            System.out.println("the request is not HttpRequest");
        }
    }
}
