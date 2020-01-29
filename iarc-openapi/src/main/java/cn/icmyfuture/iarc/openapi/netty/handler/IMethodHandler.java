package cn.icmyfuture.iarc.openapi.netty.handler;

import cn.icmyfuture.iarc.openapi.dto.Request;

public interface IMethodHandler<T, R> {
    R execute(Request<T> request);
}
