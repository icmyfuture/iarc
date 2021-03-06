package cn.icmyfuture.iarc.openapi.netty.handler.uri;

import cn.icmyfuture.iarc.openapi.netty.entity.OpenAPIType;
import cn.icmyfuture.iarc.openapi.netty.annotation.UriHandler;
import cn.icmyfuture.iarc.openapi.netty.handler.IMethodHandler;
import cn.icmyfuture.iarc.openapi.netty.handler.IUriHandler;

import java.util.Map;

@UriHandler(uri = "/device.api")
public class DeviceAPIHandler implements IUriHandler {
    private Map<String, IMethodHandler> map;

    @Override
    public OpenAPIType getOpenApiType() {
        return OpenAPIType.DEVICE_API;
    }

    @Override
    public Map<String, IMethodHandler> getMethodHandlerMap() {
        return this.map;
    }

    @Override
    public void setMethodHandlerMap(Map<String, IMethodHandler> handlers) {
        this.map = handlers;
    }
}
