package com.yaolong.government.admin.config.token;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author yaoLong
 * @date 2019-11-23  14:11
 */
@Configuration
@SuppressWarnings("all")
public class TokenStoreConfig {
/*

    @Autowired
    private RedisConnectionFactory connectionFactory;

    // 基于redis的TokenStore
    @Bean
    public TokenStore tokenStore(){

        return new MyRedisTokenStore(connectionFactory);
    }
*/

    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("admin123");

        return converter;
    }


}
