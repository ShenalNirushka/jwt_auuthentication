package com.nirushka.jwt.configuration;


import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.nirushka.jwt.service.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final RsaKeyProperties rsaKeys;


//    public SecurityConfig(RsaKeyProperties rsaKeys) {
//        this.rsaKeys = rsaKeys;
//    }

    private final JpaUserDetailsService jpaUserDetailsService;

    public SecurityConfig(RsaKeyProperties rsaKeys, JpaUserDetailsService jpaUserDetailsService) {
        this.rsaKeys = rsaKeys;
        this.jpaUserDetailsService = jpaUserDetailsService;
    }


//    @Bean
//    public InMemoryUserDetailsManager user(){
//        return new InMemoryUserDetailsManager(
//                User.withUsername("nirushka")
//                        .password("{noop}pass1234")
//                        .roles("ADMIN")
//                        .build()
//        );
//    }

//    @Bean
//    EmbeddedDatabase datasourse(){
//        return new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.HSQL)
//    }

//    @Bean
//    public JdbcUserDetailsManager user(DataSource dataSource){
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        return jdbcUserDetailsManager;
//
//    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
//                .csrf(csrf -> csrf.disable())
                .csrf(csrf -> csrf.ignoringAntMatchers("/**"))
                .authorizeRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .userDetailsService(jpaUserDetailsService)
//                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(headers -> headers.frameOptions().sameOrigin())
                .httpBasic(withDefaults())
//                .formLogin(withDefaults())
                .build();
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    //*********************************************************************************************************

//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select user_name,password, TRUE as enabled "
//                        + "from user "
//                        + "where user_name = ?")
//                .authoritiesByUsernameQuery("select user_id,'ROLE_US' as role_id "
//                        + "from user_role "
//                        + "where user_id = ?");
//    }
    //************************************************************************************************************

//    @Bean
//    JwtDecoder jwtDecoder(){
//        return NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey()).build();
//    }
//
//    @Bean
//    JwtEncoder jwtEncoder(){
//        JWK jwk = new RSAKey.Builder(rsaKeys.publicKey()).privateKey(rsaKeys.privateKey()).build();
//        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
//        return new NimbusJwtEncoder(jwks);
//    }

}
