package cn.icmyfuture.iarc.openapi.netty.handler.uri;

import cn.icmyfuture.iarc.openapi.netty.OpenAPIType;
import cn.icmyfuture.iarc.openapi.netty.handler.method.IMethodHandler;

import java.util.HashMap;

public interface IUriHandler {
    OpenAPIType getOpenApiType();

    HashMap<String, IMethodHandler> getMethodHandlerMap();

    void setMethodHandlerMap(HashMap<String, IMethodHandler> handlers);
}
