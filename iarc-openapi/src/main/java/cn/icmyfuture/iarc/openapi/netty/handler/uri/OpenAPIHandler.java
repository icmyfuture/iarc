package cn.icmyfuture.iarc.openapi.netty.handler.uri;

import cn.icmyfuture.iarc.openapi.netty.entity.OpenAPIType;
import cn.icmyfuture.iarc.openapi.netty.annotation.UriHandler;
import cn.icmyfuture.iarc.openapi.netty.handler.IMethodHandler;
import cn.icmyfuture.iarc.openapi.netty.handler.IUriHandler;

import java.util.HashMap;

@UriHandler(uri = "/open.api")
public class OpenAPIHandler implements IUriHandler {
    private HashMap<String, IMethodHandler> map;

    @Override
    public OpenAPIType getOpenApiType() {
        return OpenAPIType.OPEN_API;
    }

    @Override
    public HashMap<String, IMethodHandler> getMethodHandlerMap() {
        return this.map;
    }

    @Override
    public void setMethodHandlerMap(HashMap<String, IMethodHandler> handlers) {
        this.map = handlers;
    }
}
