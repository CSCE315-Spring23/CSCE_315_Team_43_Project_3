package com.team43.project3.smook.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.oauth2.client.endpoint.*;

import com.team43.project3.smook.model.CustomOAuth2User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfiguration {

    @Autowired
    private CustomOAuth2UserService oauthUserService;

    @Autowired
    private EmployeeService userService;
 
    // @Bean
    // public UserDetailsService userDetailsService() {
    //     return new ShopmeUserDetailsService();
    // }
 
    // @Bean
    // public BCryptPasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }
 
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     
        // http.authorizeHttpRequests().requestMatchers("/**").permitAll()
        //         .anyRequest().authenticated()
        //         .and()
        //         .logout().permitAll()
        //         .and()
        //         .oauth2Login()
        //         .loginPage("/api/login")
        //         .userInfoEndpoint()
        //             .userService(oauthUserService)
        //         .and()
        //         .successHandler(new AuthenticationSuccessHandler() {
        //             public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        //                     org.springframework.security.core.Authentication authentication) throws IOException, ServletException {
            
        //                 CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
            
        //                 userService.processOAuthPostLogin(oauthUser.getName());
            
        //                 response.sendRedirect("/list");
        //             }
        //         })
        //         .userInfoEndpoint()
        //             .userService(oauthUserService);
 
        // http.headers().frameOptions().sameOrigin();

        http.authorizeHttpRequests()
                // .requestMatchers("/home", "/login**","/api/", "/webjars/**", "/error**", "/oauth2/authorization/**").fullyAuthenticated()
                .anyRequest().fullyAuthenticated()
                .and()
                .cors().and().csrf().disable()
                .oauth2Login()
                    .defaultSuccessUrl("https://smook-app.web.app");
 
        return http.build();
    }
 
    // @Bean
    // public WebSecurityCustomizer webSecurityCustomizer() {
    //     return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**");
    // }
 
}
