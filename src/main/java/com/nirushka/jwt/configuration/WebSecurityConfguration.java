//package com.nirushka.jwt.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
//import java.net.http.HttpHeaders;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfguration extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManageBean() throws  Exception{
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity){
//        httpSecurity.cors();
//        httpSecurity.csrf().disable()
//                .authorizeRequests().antMatchers("").permitAll()
//                .antMatchers(HttpHeaders.ALLOW).permitAll()
//                .and()
//                .exceptionHandling().authenticationEntryPoint()
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//    }
//
//}
