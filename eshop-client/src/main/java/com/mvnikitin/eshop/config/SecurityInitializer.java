package com.mvnikitin.eshop.config;

import com.hazelcast.config.SecurityConfig;
import org.apache.catalina.util.SessionConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

    public SecurityInitializer() {
        super(SecurityConfig.class, SessionConfig.class);
    }
}
