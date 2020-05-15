package com.loven.iToken.web.admin.service.fallback;

import com.loven.iToken.common.domain.TbSysUser;
import com.loven.iToken.commons.dto.BaseResult;
import com.loven.iToken.commons.hystrix.Fallback;
import com.loven.iToken.web.admin.service.AdminService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Component
@RequestMapping("fallback")
public class AdminServiceFallback implements AdminService {
    @Override
    public BaseResult save(Map<String, Object> params) {
        return Fallback.badGateway();
    }

    @Override
    public BaseResult getAll() {
        return Fallback.badGateway();
    }

    @Override
    public BaseResult getOne(String userName) {
        return Fallback.badGateway();
    }

    @Override
    public BaseResult delete(String key) {
        return Fallback.badGateway();
    }

    @Override
    public BaseResult update(Map<String, Object> params) {
        return Fallback.badGateway();
    }

    @Override
    public BaseResult page(int pagePos, int pageSize, Map<String, String> entity) {
        return Fallback.badGateway();
    }
}
