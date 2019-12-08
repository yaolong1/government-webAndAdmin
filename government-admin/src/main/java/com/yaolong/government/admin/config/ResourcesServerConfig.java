package com.yaolong.government.admin.config;
import com.yaolong.government.admin.config.ajaxResponseHandle.MyAccessDeniedHandler;
import com.yaolong.government.admin.config.ajaxResponseHandle.MyAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author yaoLong
 * @date 2019-11-23  15:36
 */
@Configuration
@EnableResourceServer
public class ResourcesServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore).accessDeniedHandler(new MyAccessDeniedHandler()).authenticationEntryPoint(new MyAuthenticationEntryPoint());

        resources.resourceId("test").stateless(true);

    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/getCardInfoById/{infoId}","/getCardInfoListById/{infoCardId}/{current}/{size}","/get/ByCardId/{card_id}",
                "/logout","/register"
                ).permitAll()
                .anyRequest()
                .authenticated();
    }
}
