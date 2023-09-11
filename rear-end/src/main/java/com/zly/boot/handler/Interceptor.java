package com.zly.boot.handler;

import com.alibaba.fastjson.JSON;
import com.zly.common.dto.TokenPayload;
import com.zly.common.enums.ErrorCode;
import com.zly.common.service.RedisService;
import com.zly.common.utils.TokenUtils;
import com.zly.common.vo.Restful;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 */
public class Interceptor implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        response.setContentType("application/json; charset=utf-8");
        //允许跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许自定义请求头token(允许head跨域)
        response.setHeader("Access-Control-Allow-Headers", "token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");
        response.setHeader("Content-type", "application/json;charset=UTF-8");

        //从请求头拿token
        String token= request.getHeader("token");
        //token为空，返回请求者未登录信息
        if (token == null){
            response.getWriter().print(JSON.toJSONString(Restful.error(ErrorCode.LOGIN_EXIT)));
            return false;
        }
        //token不为空，从token中获取用户信息
        TokenPayload tokenPayload = TokenUtils.getTokenPayload(token);
        //根据从token中获取用户信息去redis数据库查询该用户token是否存在
        Object o = redisService.get(tokenPayload.getUserId());
        //redis数据库拿到token，若和用户token相等则是登录用户，放行，拿不到或者不相等，则该用户未登录
        if(!ObjectUtils.isEmpty(o)&&token.equals(o.toString())){
            redisService.expire(tokenPayload.getUserId(),30);
            return true;
        }else {
            response.getWriter().print(JSON.toJSONString(Restful.error(ErrorCode.LOGIN_EXIT)));
            return false;
        }
    }
}
