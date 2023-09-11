package com.zly.common.controller;

import com.zly.common.dto.LoginDto;
import com.zly.common.dto.TokenPayload;
import com.zly.common.enums.ErrorCode;
import com.zly.common.service.OrganizationService;
import com.zly.common.service.RedisService;
import com.zly.common.service.UserService;
import com.zly.common.utils.TokenUtils;
import com.zly.common.vo.Restful;
import com.zly.common.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zly
 * @since 2023-02-17
 */
@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private OrganizationService organizationService;

    @RequestMapping("/login")
    public Restful login(@RequestBody LoginDto loginDto, HttpServletRequest request){

        log.info("login");

        UserVo login = userService.login(loginDto.getLoginName(), loginDto.getUserPass());

        if (ObjectUtils.isEmpty(login)){
            return Restful.error(ErrorCode.LOGIN_NOT_FOUND);
        }

        String token = null;

        Object o = redisService.get(login.getId());
        if (ObjectUtils.isEmpty(o)){
            token = TokenUtils.getToken(login);
            //写入redis数据库
            redisService.set(login.getId(),token,30);
        }else {
            token = o.toString();
            redisService.expire(login.getId(),30);
        }

        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        map.put("oid",login.getOid());
        String byid = organizationService.getByid(login.getOid());
        map.put("oidname",byid);
        map.put("ifManage",login.getIfManage());
        map.put("userName",login.getUserName());

        return Restful.ok(map);
    }

    @RequestMapping("/logout")
    public Restful logout(HttpServletRequest request){

        log.info("logout");

        String token = request.getHeader("token");

        TokenPayload tokenPayload = TokenUtils.getTokenPayload(token);

        redisService.deleteByKey(tokenPayload.getUserId());

        return Restful.ok();
    }

}
