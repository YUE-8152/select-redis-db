package com.select.redis.db.controller;

import com.select.redis.db.common.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ProjectName: select-redis-db
 * @Package: com.select.redis.db
 * @ClassName: SelectRedisDbController
 * @Author: YUE
 * @Description:
 * @Date: 2020/9/25 9:55
 * @Version: 1.0
 */
@RestController
@RequestMapping("/redis")
public class SelectRedisDbController {
    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/select")
    public String selectRedisDB(){
        redisUtils.set("1","测试1");
        redisUtils.set("2","测试2",2);
        redisUtils.set("3","测试3",3);
        redisUtils.set("4","测试4",4);
        System.out.println(redisUtils.getStrValue("1"));
        System.out.println(redisUtils.getStrValue("2",2));
        System.out.println(redisUtils.getStrValue("3",3));
        System.out.println(redisUtils.getStrValue("4",4));
        redisUtils.del(1,"1");
        redisUtils.del(2,"2");
        redisUtils.del(3,"3");
        redisUtils.del(4,"4");
        return "SUCESS";
    }
}
