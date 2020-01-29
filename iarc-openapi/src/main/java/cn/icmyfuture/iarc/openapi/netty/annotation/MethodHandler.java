package cn.icmyfuture.iarc.openapi.netty.annotation;

import cn.icmyfuture.iarc.openapi.netty.entity.OpenAPIType;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodHandler {
    /**
     * 类型
     *
     * @return
     */
    OpenAPIType type() default OpenAPIType.OPEN_API;

    /**
     * 名称
     *
     * @return
     */
    String name() default "";

    /**
     * 是否需要完全匹配
     *
     * @return
     */
    boolean equal() default true;
}
