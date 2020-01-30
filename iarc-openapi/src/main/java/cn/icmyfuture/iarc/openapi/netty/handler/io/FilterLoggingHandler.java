package cn.icmyfuture.iarc.openapi.netty.handler.io;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Component;

import java.net.SocketAddress;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;

@ChannelHandler.Sharable
@Component
public class FilterLoggingHandler extends LoggingHandler {
    public FilterLoggingHandler() {
        super(LogLevel.INFO);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        ctx.fireChannelRegistered();
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) {
        ctx.fireChannelUnregistered();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        ctx.fireChannelInactive();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        ctx.fireUserEventTriggered(evt);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
//        if (this.logger.isEnabled(this.internalLevel)) {
//            this.logger.log(this.internalLevel, ctx.channel().toString() + " WRITE \n" + msg.toString());
//        }

        ctx.write(msg, promise);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (this.logger.isEnabled(this.internalLevel)) {
            FullHttpRequest request = (FullHttpRequest) msg;
            Integer length = Integer.parseInt(request.headers().get(CONTENT_LENGTH));
            String bodyStr = length > 0 ? String.format("BODY: %s \n", request.content().toString(CharsetUtil.UTF_8)) : "";
            String logStr = String.format("%s READ \n %s %s %s \n %s: %s \n %s: %s \n %s",
                    ctx.channel().toString(),
                    request.method(), request.uri(), request.protocolVersion(),
                    CONTENT_TYPE, request.headers().get(CONTENT_TYPE),
                    CONTENT_LENGTH, length,
                    bodyStr
            );
            this.logger.log(this.internalLevel, logStr);
        }
        /**
         * 请求转发给下个处理器
         */
        ctx.fireChannelRead(msg);
    }

    @Override
    public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) {
        ctx.bind(localAddress, promise);
    }

    @Override
    public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) {
        ctx.connect(remoteAddress, localAddress, promise);
    }

    @Override
    public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) {
        ctx.disconnect(promise);
    }

    @Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) {
        ctx.close(promise);
    }

    @Override
    public void deregister(ChannelHandlerContext ctx, ChannelPromise promise) {
        ctx.deregister(promise);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.fireChannelReadComplete();
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) {
        ctx.fireChannelWritabilityChanged();
    }

    @Override
    public void flush(ChannelHandlerContext ctx) {
        ctx.flush();
    }
}
