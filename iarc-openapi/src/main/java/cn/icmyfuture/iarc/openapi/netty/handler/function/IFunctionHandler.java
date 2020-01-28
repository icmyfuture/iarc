package cn.icmyfuture.iarc.openapi.netty.handler.function;

import cn.icmyfuture.iarc.openapi.dto.Response;
import cn.icmyfuture.iarc.openapi.netty.http.NettyHttpRequest;

public interface IFunctionHandler<T> {
    Response<T> execute(NettyHttpRequest request);
}
