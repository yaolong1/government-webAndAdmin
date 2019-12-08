package com.yaolong.government.admin.config;

import com.yaolong.government.admin.config.ajaxResponseHandle.CustomTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

/**
 * @author yaoLong
 * @date 2019-11-22  22:00
 */

@Configuration
@EnableAuthorizationServer
@SuppressWarnings("all")
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private ClientDetailsService detailsService;

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    /**
     * 用来配置令牌端点的安全约束.
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("permitAll()")  // tokenkey这个endpoint当使用JwtToken且使用非对称加密时，资源服务用于获取公钥而开放的，这里指这个 endpoint完全公开。
                .tokenKeyAccess("permitAll()") //checkToken这个endpoint完全公开
                .allowFormAuthenticationForClients(); //允许表单认证
    }


    /**
     * 配置客戶端详情信息
     * <p>
     * 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在 这里进行初始化，
     * 你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client")
                .secret(new BCryptPasswordEncoder().encode("secret"))
                .resourceIds("test")
                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
                .scopes("all")
                .accessTokenValiditySeconds(7200)
                .redirectUris("http://yaolong.top")
                .autoApprove();

    }

    /**
     * 用来配置令牌（token）的访问端点和令牌服务(token services)。
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {



        endpoints
                .authenticationManager(authenticationManager) // 密码模式必须要这个 密码认证管理器
                .authorizationCodeServices(authorizationCodeServices) // 授权码模式必须要用 设置授权码服务主要用于 "authorization_code" 授权码类型模式
                .userDetailsService(userDetailsService) //
                .allowedTokenEndpointRequestMethods()
                .tokenServices(tokenServices()) // 设置管理令牌服务
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);

    }


    /**
     * 发布和存储授权码的服务
     * @return
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();
    }


    /**
     * 管理令牌服务
     *
     * @return
     */
    @Bean
    public AuthorizationServerTokenServices tokenServices() {

        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setSupportRefreshToken(true); // 支持令牌刷新
        defaultTokenServices.setTokenStore(tokenStore); //绑定tokenStore
        defaultTokenServices.setRefreshTokenValiditySeconds(36000);
        defaultTokenServices.setAccessTokenValiditySeconds(7200);
        defaultTokenServices.setClientDetailsService(detailsService);

        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        //将增强的token设置到增强链中
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(customTokenEnhancer(),accessTokenConverter));

        defaultTokenServices.setTokenEnhancer(tokenEnhancerChain);

        return defaultTokenServices;

    }


    /**
     * 注入令牌增强器(自定义token返回)
     * @return
     */
    @Bean
    public TokenEnhancer customTokenEnhancer(){
        return  new CustomTokenEnhancer();
    }

}
