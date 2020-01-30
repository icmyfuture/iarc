package cn.icmyfuture.iarc.openapi.biz.handler;

import cn.icmyfuture.iarc.openapi.biz.entity.Area;
import cn.icmyfuture.iarc.openapi.dto.Request;
import cn.icmyfuture.iarc.openapi.netty.entity.OpenAPIType;
import cn.icmyfuture.iarc.openapi.netty.annotation.MethodHandler;
import cn.icmyfuture.iarc.openapi.netty.handler.IMethodHandler;

import java.util.ArrayList;
import java.util.List;

@MethodHandler(type = OpenAPIType.OPEN_API, name = "Common.GetAreaList")
public class GetAreaListHandler implements IMethodHandler<Area, List<Area>> {
    @Override
    public List<Area> execute(Request<Area> request) {
        List<Area> areaList = new ArrayList<>();
        int count = 1000;
        for (int i = 0; i < count; i++) {
            areaList.add(new Area(String.format("Area: %s", i)));
        }
        return areaList;
    }
}
