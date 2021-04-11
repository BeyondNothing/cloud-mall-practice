package com.flagship.cloud.mall.practice.zuul.fliter;

import com.flagship.cloud.mall.practice.common.common.Constant;
import com.flagship.cloud.mall.practice.user.model.pojo.User;
import com.flagship.cloud.mall.practice.zuul.feign.UserFeignClient;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author Flagship
 * @Date 2021/4/8 20:41
 * @Description 管理员鉴权过滤器
 */
@Component
public class AdminFilter extends ZuulFilter {
    @Resource
    UserFeignClient userFeignClient;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String requestUri = request.getRequestURI();

        if (requestUri.contains("adminLogin")) {
            return false;
        }

        if (requestUri.contains("admin")) {
            return true;
        }
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute(Constant.FLAGSHIP_MALL_USER);
        if (currentUser == null) {
            context.setSendZuulResponse(false);
            context.setResponseBody("{\n" +
                    "    \"status\": 10007,\n" +
                    "    \"msg\": \"用户未登录\",\n" +
                    "    \"data\": null\n" +
                    "}");
            context.setResponseStatusCode(200);
            return null;
        }

        //校验是否是管理员
        Boolean adminRole = userFeignClient.checkAdminRole(currentUser);
        if (!adminRole) {
            context.setSendZuulResponse(false);
            context.setResponseBody("{\n" +
                    "    \"status\": 10009,\n" +
                    "    \"msg\": \"无管理员权限\",\n" +
                    "    \"data\": null\n" +
                    "}");
            context.setResponseStatusCode(200);
        }
        return null;
    }
}
