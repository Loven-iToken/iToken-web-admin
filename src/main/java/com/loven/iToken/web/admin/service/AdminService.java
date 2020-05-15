package com.loven.iToken.web.admin.service;

import com.loven.iToken.common.domain.TbSysUser;
import com.loven.iToken.common.web.service.BaseService;
import com.loven.iToken.commons.dto.BaseResult;
import com.loven.iToken.web.admin.service.fallback.AdminServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Service
@FeignClient(value = "iToken-service-admin", fallback = AdminServiceFallback.class)
public interface AdminService extends BaseService<TbSysUser>{
    @PostMapping("user/saveUser")
    BaseResult save(@RequestBody Map<String, Object> params);

    @GetMapping("user/userList")
    BaseResult getAll();

    @GetMapping("user/getUser/{key}")
    BaseResult getOne(@PathVariable String key);

    @PostMapping("user/delete")
    BaseResult delete(@RequestParam String key);

    @PostMapping("user/update")
    BaseResult update(@RequestBody Map<String, Object> params);

    @GetMapping("user/page/{pagePos}/{pageSize}")
    BaseResult page(@PathVariable int pagePos,
                    @PathVariable int pageSize,
                    @RequestParam  Map<String, String> entity);
}
