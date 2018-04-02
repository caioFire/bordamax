//package com.bordamax.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//
///**
// * Created by fire on 28/03/18.
// */
//
//@Configuration
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.
//            authorizeRequests()
////                .antMatchers("/home").hasAnyRole("PG_REL_HOME")
//                .anyRequest()
//                .authenticated()
//            .and()
//            .formLogin()
//                .loginPage("/login")
//                .permitAll()
//            .and()
//            .logout()
//                .logoutSuccessUrl("/login?logout")
//                .permitAll();
//    }
//}
