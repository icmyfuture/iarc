package cn.icmyfuture.iarc.openapi.netty.handler.method;

import cn.icmyfuture.iarc.openapi.dto.Response;
import cn.icmyfuture.iarc.openapi.netty.http.NettyHttpRequest;

public interface IMethodHandler<T> {
    Response<T> execute(NettyHttpRequest request);
}
