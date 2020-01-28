package cn.icmyfuture.iarc.openapi.netty.handler.method;

import cn.icmyfuture.iarc.openapi.dto.Response;
import cn.icmyfuture.iarc.openapi.netty.OpenAPIType;
import cn.icmyfuture.iarc.openapi.netty.annotation.MethodHandler;
import cn.icmyfuture.iarc.openapi.netty.http.NettyHttpRequest;

@MethodHandler(type= OpenAPIType.DEVICE_API, name="Common.GetDeviceList")
public class GetDeviceListHandler implements IMethodHandler {
    @Override
    public Response execute(NettyHttpRequest request) {
        return null;
    }
}
