package cn.icmyfuture.iarc.openapi.netty.handler.io;

import cn.icmyfuture.iarc.openapi.dto.Response;
import cn.icmyfuture.iarc.openapi.dto.Request;
import cn.icmyfuture.iarc.openapi.netty.annotation.MethodHandler;
import cn.icmyfuture.iarc.openapi.netty.annotation.UriHandler;
import cn.icmyfuture.iarc.openapi.netty.entity.NettyHttpRequest;
import cn.icmyfuture.iarc.openapi.netty.entity.NettyHttpResponse;
import cn.icmyfuture.iarc.openapi.netty.exception.IllegalMethodNotAllowedException;
import cn.icmyfuture.iarc.openapi.netty.exception.IllegalPathDuplicatedException;
import cn.icmyfuture.iarc.openapi.netty.exception.IllegalPathNotFoundException;
import cn.icmyfuture.iarc.openapi.netty.handler.IMethodHandler;
import cn.icmyfuture.iarc.openapi.netty.handler.IUriHandler;
import com.google.gson.GsonBuilder;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ChannelHandler.Sharable
@Component
/**
 * 通过实现ApplicationContextAware将setApplicationContext（）函数引入spring初始化内容中去
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> implements ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServerHandler.class);

    private HashMap<String, IUriHandler> uriHandlerMap = new HashMap<>();

    /**
     * 线程工厂
     */
    private ExecutorService executor = Executors.newCachedThreadPool(runnable -> {
        Thread thread = Executors.defaultThreadFactory().newThread(runnable);
        thread.setName("NettyHttpHandler-" + thread.getName());
        return thread;
    });

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) {
        FullHttpRequest copyRequest = request.copy();
        /**
         * 通过内部类的形式传入一个runnable的实现类并重写了run方法 线程池在执行的时候会调用这个方法
         */
        executor.execute(() -> onReceivedRequest(ctx, new NettyHttpRequest(copyRequest)));
    }

    /**
     * @param context
     * @param request
     */
    private void onReceivedRequest(ChannelHandlerContext context, NettyHttpRequest request) {
        /**
         * 处理request请求
         */
        FullHttpResponse response = handleHttpRequest(request);
        /**
         * 通过channel将结果输出 并通过添加监听器的方式关闭channel通道
         */
        context.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        /**
         * 释放bytebuf缓存
         */
        ReferenceCountUtil.release(request);
    }

    /**
     * @param request NettyHttpRequest extends Fullhttpreuqest
     * @return 请求处理结果
     */
    private FullHttpResponse handleHttpRequest(NettyHttpRequest request) {

        IMethodHandler methodHandler = null;
        /**
         * 请求处理并根据不同的结果或者捕获的异常进行状态码转换并返回
         */
        try {
            methodHandler = matchMethodHandler(request);
            ParameterizedType parameterizedType = (ParameterizedType)methodHandler.getClass().getGenericInterfaces()[0];
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            String requestStr = "{}";
            Object obj = new GsonBuilder().create().fromJson(requestStr, actualTypeArguments[0]);
            Object result = methodHandler.execute(new Request(obj, request));
            return NettyHttpResponse.ok(Response.ok(result).toPlainJSONString());
        } catch (IllegalMethodNotAllowedException error) {
            return NettyHttpResponse.make(HttpResponseStatus.METHOD_NOT_ALLOWED);
        } catch (IllegalPathNotFoundException error) {
            return NettyHttpResponse.make(HttpResponseStatus.NOT_FOUND);
        } catch (Exception error) {
            LOGGER.error(methodHandler.getClass().getSimpleName() + " Error", error);
            return NettyHttpResponse.makeError(error);
        }
    }

    /**
     * spring初始化加载此函数
     *
     * @param applicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        setUriHandlerMap(applicationContext);
    }

    private void setUriHandlerMap(ApplicationContext applicationContext) {
        Map<String, Object> handlers = applicationContext.getBeansWithAnnotation(UriHandler.class);
        Map<String, Object> methodHandlers = applicationContext.getBeansWithAnnotation(MethodHandler.class);
        for (Map.Entry<String, Object> entry : handlers.entrySet()) {
            Object handler = entry.getValue();
            UriHandler h = handler.getClass().getAnnotation(UriHandler.class);
            String uri = h.uri();
            /**
             * 查询是否当前处理器的注解是否已经存在（类似于SSM中controler的注解不能重复）
             * 1.存在则抛出异常
             * 2. 不存在则存入Map集合中 在SSM中是通过对类方法注解的扫描 存入内部类mapperRegistry中
             */
            if (uriHandlerMap.containsKey(uri)) {
                LOGGER.error("IUriHandler has duplicated :" + uri, new IllegalPathDuplicatedException());
                System.exit(0);
            }
            IUriHandler hi = (IUriHandler) handler;
            uriHandlerMap.put(uri, hi);
            setMethodHandlerMapForUriHandler(hi, methodHandlers);
        }
    }

    private void setMethodHandlerMapForUriHandler(IUriHandler handler, Map<String, Object> handlers) {
        HashMap<String, IMethodHandler> map = new HashMap<>();
        for (Map.Entry<String, Object> entry : handlers.entrySet()) {
            Object h = entry.getValue();
            MethodHandler m = h.getClass().getAnnotation(MethodHandler.class);
            if (handler.getOpenApiType() == m.type()) {
                String key = m.name();
                if (!map.containsKey(key)) {
                    map.put(key, (IMethodHandler) h);
                }
            }
        }
        handler.setMethodHandlerMap(map);
    }

    private IMethodHandler matchMethodHandler(NettyHttpRequest request) throws IllegalPathNotFoundException, IllegalMethodNotAllowedException {
        String uri = request.getUri();
        String method = "Common.GetAreaList";

        if(uriHandlerMap.containsKey(uri)){
            IUriHandler uriHandler = uriHandlerMap.get(uri);
            Map<String, IMethodHandler> methodHandlerMap = uriHandler.getMethodHandlerMap();
            if(methodHandlerMap.containsKey(method)){
                return methodHandlerMap.get(method);
            }
            throw new IllegalMethodNotAllowedException();
        }
        throw new IllegalPathNotFoundException();
    }
}
