package com.zly.boot.config;

import com.zly.boot.handler.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 拦截器的属性配置
 *
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    /**
     * 把Interceptor这个实现类交给spring进行管理
     * @return
     */
    @Bean
    Interceptor getAdminInterceptor(){
        return new Interceptor();
    }

    /**
     * 重写addCorsMappings()解决跨域问题
     * 配置：允许http请求进行跨域访问
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //指哪些接口URL需要增加跨域设置
        registry.addMapping("/**")
                //.allowedOrigins("*")//指的是前端哪些域名被允许跨域
                .allowedOriginPatterns("*")
                //需要带cookie等凭证时，设置为true，就会把cookie的相关信息带上
                .allowCredentials(true)
                //指的是允许哪些方法
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                //cookie的失效时间，单位为秒（s），若设置为-1，则关闭浏览器就失效
                .maxAge(3600);
    }

    /**
     * 重写addInterceptors()实现拦截器
     * 配置：要拦截的路径以及不拦截的路径
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册Interceptor拦截器(Interceptor这个类是我们自己写的拦截器类)
        InterceptorRegistration registration = registry.addInterceptor(getAdminInterceptor());
        //addPathPatterns()方法添加需要拦截的路径
        //所有路径都被拦截
        registration.addPathPatterns("/**");
        //excludePathPatterns()方法添加不拦截的路径
        //添加不拦截路径
        registration.excludePathPatterns(
                //登录
                "/login/login"
        );
    }
}
