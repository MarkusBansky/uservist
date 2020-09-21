package com.markiian.benovskyi.auth.security;

import com.markiian.benovskyi.auth.properties.UservistProperties;
import com.markiian.benovskyi.auth.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserTokenService userTokenService;
    private final UservistProperties uservistProperties;

    private final UservistAuthenticationEntryPoint unauthorizedHandler;

    public WebSecurityConfig(UservistAuthenticationEntryPoint unauthorizedHandler,
                             UserTokenService userTokenService,
                             UservistProperties uservistProperties) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.userTokenService = userTokenService;
        this.uservistProperties = uservistProperties;
    }

    @Bean
    public UservistAuthenticationFilter authenticationTokenFilterBean() {
        return new UservistAuthenticationFilter(userTokenService, uservistProperties);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
