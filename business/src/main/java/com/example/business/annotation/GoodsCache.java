package com.example.business.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;


@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GoodsCache {

    /**
     * 缓存前缀
     * @return
     */
    String prefix() default "";

    /**
     * 缓存超时时间，单位秒
     * @return
     */
    int timeout() default 300;

    /**
     * 防止缓存雪崩的随机值范围，单位秒
     */
    int random() default 300;
}
