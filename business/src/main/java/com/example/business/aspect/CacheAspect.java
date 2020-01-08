package com.example.business.aspect;

import com.alibaba.fastjson.JSON;
import com.example.business.annotation.GoodsCache;
import com.example.business.redis.RedisPool;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.ShardedJedis;

import java.lang.reflect.Method;
import java.util.Arrays;

@Component
@Aspect
public class CacheAspect {


    @Autowired
    private RedissonClient redissonClient;


    @Around("@annotation(com.example.business.annotation.GoodsCache)")
    public Object executeServiceMethod(ProceedingJoinPoint pjp) throws Throwable {

        ShardedJedis redis = RedisPool.getJedisShardInfo();
        
        // 获取前缀
        MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
        Method method = methodSignature.getMethod();

        Class<?> returnType = method.getReturnType();
        GoodsCache annotation = method.getAnnotation(GoodsCache.class);

        // 请求参数
        Object[] args = pjp.getArgs();

        // 前缀
        String prefix = annotation.prefix();

        String key = prefix + Arrays.asList(args).toString();

        String s = redis.get(key);
        
        if (StringUtils.isNotEmpty(s)) {
            return JSON.parseObject(s, returnType);
        }

        // 没有就去数据库中查询，这里需要加分布式锁，防止缓存击穿
        RLock lock = redissonClient.getLock("lock");
        lock.lock();

        // 到缓存去查询，如果有就返回
        String sString = redis.get(key);

        if (StringUtils.isNotEmpty(sString)) {
            return JSON.parseObject(sString, returnType);
        }

        Object proceed = pjp.proceed(args);

        // 将结果放到redis
        redis.set(key, String.valueOf(proceed));

        lock.unlock();

        return proceed;
    }
}
