package com.yaolong.government.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yaoLong
 * @date 2019-12-03  10:03
 */
@SpringBootApplication
@MapperScan("com.yaolong.government.admin.dao")
public class GovernmentAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(GovernmentAdminApplication.class,args);
    }

}
