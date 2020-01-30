package cn.icmyfuture.iarc.openapi.netty.handler;

import cn.icmyfuture.iarc.openapi.netty.entity.OpenAPIType;

import java.util.Map;

public interface IUriHandler {
    OpenAPIType getOpenApiType();

    Map<String, IMethodHandler> getMethodHandlerMap();

    void setMethodHandlerMap(Map<String, IMethodHandler> handlers);
}
