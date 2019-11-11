package com.example.intercepter;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.constants.CurrentUserConstants;
import com.example.entity.data.User;
import com.example.exception.BizException;
import com.example.exception.ErrCode;
import com.example.utils.TokenUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by cicada on 2019/8/9.
 */
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    public final static String ACCESS_TOKEN = "accessToken";

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 判断接口是否需要登录
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
        // 有 @LoginRequired 注解，需要认证
        if (methodAnnotation != null) {
            // 判断是否存在令牌信息，如果存在，则允许登录
            String accessToken = request.getParameter("accessToken");
            if (null == accessToken) {
                throw new BizException(ErrCode.TOKEN_STATUS_NO);
            }
            Claims claims ;
            try{
                claims = TokenUtils.parseJWT(accessToken);
            }catch (Exception e){
                throw new BizException(ErrCode.TOKEN_STATUS_ERROR);
            }
            if(claims.getExpiration().before(new Date())){
                throw new BizException(ErrCode.TOKEN_STATUS_INVALID);
            }
            User user = new User();
            user.setUserId(Long.parseLong(claims.getId()));
            user.setPhoneNum(claims.getIssuer());

            JSONObject jsonObject=(JSONObject) JSON.parse(claims.getSubject());
            user.setUserName(String.valueOf(jsonObject.getString("userName")));
            user.setIdentityCard(String.valueOf(jsonObject.getString("identityCard")));
            // 当前登录用户@CurrentUser

            if(null ==redisTemplate.opsForValue().get(CurrentUserConstants.CHECK_TOKEN_INFO)|| !(redisTemplate.opsForValue().get(CurrentUserConstants.CHECK_TOKEN_INFO).equals(user.getUserId()+user.getPhoneNum()+user.getIdentityCard()))){
                throw new BizException(ErrCode.TOKEN_STATUS_ERROR);
            }
            request.setAttribute(CurrentUserConstants.CURRENT_USER, user);
            return true;
        }else{
            // 没有认证，需要根据请求的ip 判断system_id
            String url = request.getRequestURL().toString() ;
            /*// Test-Start
            // 不需要登录时，暂时通过ACCESS_TOKEN来模拟请求的不同地址
            url = request.getHeader(ACCESS_TOKEN);
            Long companyId = 0L;
            if ("15.14".equals(url)) {
                companyId = 1514L;
            }
            else if ("99.86".equals(url)){
                companyId = 9986L;
            }
            // Test-End
            request.setAttribute(CurrentUserConstants.COMPANY, companyId);*/
            // TODO 默认companyId的配置
//            request.setAttribute(CurrentUserConstants.COMPANY, defaultCompanyId);
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
