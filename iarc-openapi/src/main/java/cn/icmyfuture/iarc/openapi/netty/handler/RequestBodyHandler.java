package cn.icmyfuture.iarc.openapi.netty.handler;

import cn.icmyfuture.iarc.openapi.dto.Response;
import cn.icmyfuture.iarc.openapi.netty.annotation.NettyHttpHandler;
import cn.icmyfuture.iarc.openapi.netty.http.NettyHttpRequest;

@NettyHttpHandler(path = "/request/body", method = "POST")
public class RequestBodyHandler implements IFunctionHandler<String> {
    @Override
    public Response<String> execute(NettyHttpRequest request) {
        /**
         * 可以在此拿到json转成业务需要的对象
         */
        String json = request.contentText();
        return Response.ok(json);
    }
}
