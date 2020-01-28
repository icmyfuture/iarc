package cn.icmyfuture.iarc.openapi.netty.handler.function;

import cn.icmyfuture.iarc.openapi.dto.Response;
import cn.icmyfuture.iarc.openapi.netty.annotation.NettyHttpHandler;
import cn.icmyfuture.iarc.openapi.netty.http.NettyHttpRequest;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@NettyHttpHandler(path = "/moment/list/", equal = false)
public class PathVariableHandler implements IFunctionHandler<List<HashMap<String, String>>> {
    @Override
    public Response<List<HashMap<String, String>>> execute(NettyHttpRequest request) {

        /**
         * 通过请求uri获取到path参数
         */
        String id = request.getStringPathValue(3);

        List<HashMap<String, String>> list = new LinkedList<>();
        HashMap<String, String> data = new HashMap<>();
        data.put("id", "1");
        data.put("name", "Bluesky");
        data.put("text", "hello sea!");
        data.put("time", "2018-08-08 08:08:08");
        list.add(data);
        return Response.ok(list);
    }
}