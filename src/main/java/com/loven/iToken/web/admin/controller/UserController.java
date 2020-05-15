package com.loven.iToken.web.admin.controller;

import com.loven.iToken.common.domain.TbSysUser;
import com.loven.iToken.common.web.controller.BaseController;
import com.loven.iToken.commons.dto.BaseResult;
import com.loven.iToken.commons.utils.MapperUtils;
import com.loven.iToken.web.admin.service.AdminService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

@Controller
public class UserController extends BaseController<AdminService, TbSysUser>{

    @ModelAttribute
    public TbSysUser getUser(String id){
        TbSysUser tbUser = new TbSysUser();
        if(StringUtils.isNotBlank(id) && !id.equals("default")){
            BaseResult result = service.getOne(id);
            if(result.getResult() == BaseResult.Status.OK){
                tbUser = MapperUtils.obj2pojo(result.getData(), TbSysUser.class);
                return tbUser;
            }
        }
        return tbUser;
    }

    @PostMapping("register")
    public String userRegister(@RequestBody Map<String, Object> user, RedirectAttributes redirectAttributes){
        BaseResult res = service.save(user);
        if(res.getResult() == BaseResult.Status.OK){
            redirectAttributes.addFlashAttribute("loginCode", user.get("loginCode"));
            return "redirect:http://localhost/v1/user/login";
        }
        else{
            redirectAttributes.addFlashAttribute("msg", "网络异常，请稍后再试!");
            return "redirect:register";
        }
    }

    @GetMapping("detail")
    public String detail(@RequestParam String id, Model model){
        BaseResult result = service.getOne(id);
        if(result != null && result.getResult() == BaseResult.Status.OK){
            TbSysUser user = MapperUtils.obj2pojo(result.getData(), TbSysUser.class);
            model.addAttribute("user",user);
            return "detail";
        }
        else{
            return "error";
        }
    }

    @PostMapping("save")
    public String save(TbSysUser user, HttpServletRequest request, RedirectAttributes redirectAttributes){
        HttpSession session = request.getSession();
        TbSysUser currentUser = (TbSysUser) session.getAttribute("user");
        String stats = null;
        if(StringUtils.isBlank(user.getUserCode()) || user.getUserCode().equals("default")){
            stats = "新增";
            user.setUserType("1");
            user.setMgrType("0");
            user.setStatus("0");
            user.setCreateBy(currentUser.getUserName());
            user.setCreateDate(new Date());
            user.setUpdateBy(currentUser.getUserName());
            user.setUpdateDate(new Date());
            user.setCorpCode("0");
            user.setCorpName("iToken");
            return saveObj(user, redirectAttributes, stats);
        }
        else{
            stats = "更新";
            TbSysUser temp = MapperUtils.obj2pojo(service.getOne(user.getUserCode()).getData(),TbSysUser.class);
            temp.setUserName(user.getUserName());
            temp.setUpdateDate(new Date());
            temp.setLoginCode(user.getLoginCode());
            temp.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            temp.setMobile(user.getMobile());
            return saveObj(temp, redirectAttributes, stats);
        }
    }

    @ResponseBody
    @PostMapping("test")
    public BaseResult test(@RequestBody Map<String, Object> param){
        return service.update(param);
    }
}
