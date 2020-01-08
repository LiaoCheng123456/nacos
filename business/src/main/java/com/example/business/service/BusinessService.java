package com.example.business.service;

import com.example.business.redis.RedisConfig;
import com.example.business.redis.RedisPool;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

@Service(value = "businessService")
public class BusinessService {

    @Autowired
    private RedissonClient redissonClient;

//    private ShardedJedis redis = RedisPool.getShardedJedisPool().getResource();

//    @Reference(group = "accountService")
//    private IAccountService accountService;
//
//    @Reference(group = "orderService")
//    private IOrderService orderService;

//    @GlobalTransactional(timeoutMills = 300000, name = "dubbo-gts-seata-example")
//    public String testService(String service) {
//        accountService.updateAccountService("test");
//        orderService.createOrder("test");
//        throw new RuntimeException("测试异常");
////        return null;
//    }

    public void redissonTest() {
        // 枷锁
        RLock lock = redissonClient.getLock("lock");
        lock.lock();


        ShardedJedis redis = RedisPool.getJedisShardInfo();
        String numString = redis.get("num");
        int num= 0;
        if (StringUtils.isEmpty(numString)) {
            num ++;
        } else {
            num = Integer.parseInt(numString) + 1;
        }
        redis.set("num", String.valueOf(num));
        redis.close();

        lock.unlock();
    }

    public String latch() throws InterruptedException {
        RCountDownLatch latch = redissonClient.getCountDownLatch("latch");
        latch.trySetCount(5);
        latch.await();
        return "解锁成功";
    }

    public String countdown() {
        RCountDownLatch latch = redissonClient.getCountDownLatch("latch");
        latch.countDown();
        return "执行了一次";
    }
}
