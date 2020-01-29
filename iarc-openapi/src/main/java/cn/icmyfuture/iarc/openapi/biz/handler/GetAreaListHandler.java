package cn.icmyfuture.iarc.openapi.biz.handler;

import cn.icmyfuture.iarc.openapi.biz.entity.Area;
import cn.icmyfuture.iarc.openapi.dto.Request;
import cn.icmyfuture.iarc.openapi.netty.entity.OpenAPIType;
import cn.icmyfuture.iarc.openapi.netty.annotation.MethodHandler;
import cn.icmyfuture.iarc.openapi.netty.handler.IMethodHandler;

@MethodHandler(type= OpenAPIType.OPEN_API, name="Common.GetAreaList")
public class GetAreaListHandler implements IMethodHandler<Area, String> {
    @Override
    public String execute(Request<Area> request) {
        return "OK";
    }
}
