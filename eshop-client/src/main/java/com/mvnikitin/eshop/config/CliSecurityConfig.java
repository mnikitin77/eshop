package com.mvnikitin.eshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class CliSecurityConfig extends WebSecurityConfigurerAdapter {

    DaoAuthenticationProvider authenticationProvider;

    @Autowired
    public void setAuthenticationProvider(DaoAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/checkout/**").hasAnyRole("CLIENT", "ADMIN")
                .antMatchers(
                        "/css/**",
                        "/js/**",
                        "/fonts/**",
                        "/img/**",
                        "/**").permitAll()
                    .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .and()
            .logout()
                .logoutRequestMatcher(
                        new AntPathRequestMatcher("/logout", "GET"))
                .logoutSuccessUrl("/")
            .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
    }
}
