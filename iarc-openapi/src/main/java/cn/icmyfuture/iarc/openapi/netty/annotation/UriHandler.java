package cn.icmyfuture.iarc.openapi.netty.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UriHandler {
    /**
     * 请求uri
     *
     * @return
     */
    String uri() default "";

    /**
     * 支持的提交方式
     *
     * @return
     */
    String method() default "POST";

    /**
     * 是否需要完全匹配
     *
     * @return
     */
    boolean equal() default true;
}
