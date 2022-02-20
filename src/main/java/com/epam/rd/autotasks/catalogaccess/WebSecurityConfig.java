package com.epam.rd.autotasks.catalogaccess;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static com.epam.rd.autotasks.catalogaccess.domain.Role.EMPLOYEE;
import static com.epam.rd.autotasks.catalogaccess.domain.Role.MANAGER;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/employees", "/employees/*").hasAnyRole(MANAGER.name(), EMPLOYEE.name())
                .antMatchers(HttpMethod.POST, "/employees").hasRole(MANAGER.name())
                .antMatchers("/salaries").hasRole(MANAGER.name())
                .antMatchers("/salaries/my").hasAnyRole(MANAGER.name(), EMPLOYEE.name())
                .antMatchers("/catalog").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
}