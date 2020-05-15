package com.loven.iToken.web.admin.service.fallback;

import com.loven.iToken.web.admin.service.RedisService;
import org.springframework.stereotype.Component;

/**
 * Created by loven on 2020/5/6.
 */
@Component
public class RedisServiceFallback implements RedisService {
    @Override
    public String put(String key, String value, long seconds) {
        return null;
    }

    @Override
    public String get(String key) {
        return null;
    }
}

