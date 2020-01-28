package cn.icmyfuture.iarc.openapi.netty.handler;

import cn.icmyfuture.iarc.openapi.dto.Response;
import cn.icmyfuture.iarc.openapi.netty.annotation.NettyHttpHandler;
import cn.icmyfuture.iarc.openapi.netty.http.NettyHttpRequest;

@NettyHttpHandler(path = "/hello/world")
public class HelloWorldHandler implements IFunctionHandler<String> {
    @Override
    public Response<String> execute(NettyHttpRequest request) {
        return Response.ok("Hello World");
    }
}
