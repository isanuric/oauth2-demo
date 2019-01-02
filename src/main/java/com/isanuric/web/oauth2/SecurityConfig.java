package com.isanuric.web.oauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;

/*
 * Project: oauth2
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Value("${spring.security.oauth2.client.registration.google.clientId}")
//    private String clientId;
//
//    @Value("${spring.security.oauth2.client.registration.google.clientSecret}")
//    private String clientSecret;
//
//    @Value("${spring.security.oauth2.client.registration.google.redirectUriTemplate}")
//    private String redirectUriTemplate;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login", "/error").permitAll()
                .anyRequest().authenticated()

                .and()
                .oauth2Login();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(this.googleClientRegistry());
    }

    private ClientRegistration googleClientRegistry() {
        return ClientRegistration.withRegistrationId("google")
                .clientId("YOUR_CONF")
                .clientSecret("YOUR_CONF")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
                .authorizationUri("https://accounts.google.com/o/oauth2/auth")
                .tokenUri("https://www.googleapis.com/oauth2/v4/token")
                .scope("openid", "profile", "email")
                .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
                .userNameAttributeName(IdTokenClaimNames.SUB)
                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                .clientName("Google")
                .build();
    }
}
