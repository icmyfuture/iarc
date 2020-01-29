package cn.icmyfuture.iarc.openapi.netty.handler;

import cn.icmyfuture.iarc.openapi.netty.entity.OpenAPIType;

import java.util.HashMap;

public interface IUriHandler {
    OpenAPIType getOpenApiType();

    HashMap<String, IMethodHandler> getMethodHandlerMap();

    void setMethodHandlerMap(HashMap<String, IMethodHandler> handlers);
}
