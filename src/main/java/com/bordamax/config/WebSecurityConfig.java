package com.bordamax.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import java.util.Arrays;


/**
 * Created by fire on 28/03/18.
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustonUsuarioDetailService custonUsuarioDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .authorizeRequests()

//                .antMatchers(HttpMethod.GET, "/amostra/**").hasAnyRole("ADMIN", "USER")
//                .antMatchers(HttpMethod.GET, "/cliente/**").hasAnyRole("ROLE_ADMIN", "USER")
//                .antMatchers(HttpMethod.GET, "/localizacao/**").hasAnyRole("ADMIN","USER")
//                .antMatchers(HttpMethod.GET, "/login/**").hasAnyRole("ADMIN", "USER")
//                .antMatchers(HttpMethod.GET, "/home/**").hasAnyRole("ADMIN", "USER")
//
//                .antMatchers(HttpMethod.POST, "/cliente/**").hasAnyRole("ADMIN", "USER")
//                .antMatchers(HttpMethod.POST, "/amostra/**").hasAnyRole("ADMIN", "USER")
//                .antMatchers(HttpMethod.POST, "/localizacao/**").hasAnyRole("ADMIN", "USER")
//                .antMatchers(HttpMethod.POST, "/login/**").hasAnyRole("ADMIN", "USER")
//                .antMatchers(HttpMethod.POST, "/home/**").hasAnyRole("ADMIN", "USER")

//                .antMatchers(HttpMethod.GET, "/usuario/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST, "/usuario/**").hasRole("ADMIN")


                .antMatchers(HttpMethod.GET, "/home/**").permitAll()
                .antMatchers(HttpMethod.GET, "/cliente/**").permitAll()

                .antMatchers(HttpMethod.POST, "/**").permitAll()
                .antMatchers(HttpMethod.GET, "/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                .requestMatchers(CorsUtils::isCorsRequest).permitAll()

                .antMatchers("/css/**", "/js/**", "/webjars/**","/resources/**", "/**").permitAll()
                .anyRequest()
                .authenticated()
            .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home",true)
                .permitAll()
            .and()
                .logout()
                .logoutSuccessUrl("/login")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .and()
                .httpBasic()
            .and()
                .csrf()
                .disable().authorizeRequests();

//        http
//                .headers()
//                .frameOptions()
//                .disable();

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(custonUsuarioDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
//        configuration.setAllowedHeaders(Arrays.asList( "*","text/plain","application/json", "Access-Control-Allow-Headers", "Authorization, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, " +
//                "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }


//    @Bean
//    public FilterRegistrationBean corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//        bean.setOrder(0);
//        return bean;
//    }
}





