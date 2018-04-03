package com.bordamax.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * Created by fire on 28/03/18.
 */


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustonUsuarioDetailService custonUsuarioDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http
//                .authorizeRequests().
//                antMatchers("/login").hasAnyRole("USER")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login").
//                defaultSuccessUrl("/home").
//                permitAll().
//                and()
//                .logout()
//                .permitAll();

        http
                .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/webjars/**","/resources/**").permitAll()
                .antMatchers("/","/inicio").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .permitAll()
                .and()
                .logout()
                .permitAll();

//        http
//                .authorizeRequests()
//                .antMatchers("/resources/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();



    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(custonUsuarioDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }
}



