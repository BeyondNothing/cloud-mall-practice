package com.flagship.cloud.mall.practice.zuul.fliter;

import com.flagship.cloud.mall.practice.common.common.Constant;
import com.flagship.cloud.mall.practice.user.model.pojo.User;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author Flagship
 * @Date 2021/4/8 19:57
 * @Description
 */
@Component
public class UserFilter extends ZuulFilter {
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
        if (requestUri.contains("image") || requestUri.contains("pay")) {
            return false;
        }
        if (requestUri.contains("cart") || requestUri.contains("order")) {
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
        }
        return null;
    }
}
