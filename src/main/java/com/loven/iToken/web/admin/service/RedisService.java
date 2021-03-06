package com.loven.iToken.web.admin.service;

import com.loven.iToken.web.admin.service.fallback.RedisServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by loven on 2020/5/6.
 */
@Service
@FeignClient(value = "iToken-service-redis", fallback = RedisServiceFallback.class)
public interface RedisService {
    @PostMapping("put")
    String put(@RequestParam String key, @RequestParam String value, @RequestParam long seconds);

    @GetMapping("get")
    String get(@RequestParam String key);
}