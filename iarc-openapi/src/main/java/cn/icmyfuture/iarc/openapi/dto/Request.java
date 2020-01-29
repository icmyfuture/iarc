package cn.icmyfuture.iarc.openapi.dto;

import cn.icmyfuture.iarc.openapi.netty.entity.NettyHttpRequest;

public final class Request<T> {
    private T t;
    private NettyHttpRequest httpRequest;

    public Request(T t, NettyHttpRequest httpRequest){
        this.t = t;
        this.httpRequest = httpRequest;
    }

    public T getT() {
        return t;
    }

    public NettyHttpRequest getHttpRequest() {
        return httpRequest;
    }
}
