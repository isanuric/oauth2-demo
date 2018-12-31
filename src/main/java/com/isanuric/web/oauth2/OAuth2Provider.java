package com.isanuric.web.oauth2;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistration.Builder;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

/*
 * Project: oauth2
 * @author Ehsan Salmani
 */
public enum OAuth2Provider {

    GOOGLE {
        public Builder getBuilder(String  registrationId) {
            ClientRegistration.Builder builder = getBuilder(registrationId);
            builder.clientAuthenticationMethod(ClientAuthenticationMethod.BASIC);
            builder.redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}");
            builder.scope("openid", "profile", "email");
            builder.clientName("Google");
            return builder;
        }
    },
    GITHUB {}
}
