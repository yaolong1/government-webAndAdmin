package com.yaolong.government.admin.controller;

import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yaoLong
 * @date 2019-11-23  15:24
 */
@RestController
@CrossOrigin
public class MyLoginController {

   private RequestCache cache = new HttpSessionRequestCache();

   private RedirectStrategy strategy =new DefaultRedirectStrategy();

    @RequestMapping("/myLogin")
    public R requireAuthentication(HttpServletRequest request , HttpServletResponse response) throws IOException {

        SavedRequest savedRequest = cache.getRequest(request,response);
        if (savedRequest !=null){
            String redirectUrl = savedRequest.getRedirectUrl();

            if (StringUtils.endsWithIgnoreCase(redirectUrl,".html")){
                strategy.sendRedirect(request,response,"/test.html");
            }
        }
        return R.failed("需要身份认证");
    }
}

