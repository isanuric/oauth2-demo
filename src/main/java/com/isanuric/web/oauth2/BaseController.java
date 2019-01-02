package com.isanuric.web.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Project: oauth2
 */


@RestController
public class BaseController {

    @Lazy
    @Autowired
    private OAuth2AuthorizedClientService oAuth2AuthorizedClientService;


    @GetMapping("/user-info")
    public Object userInfo(OAuth2AuthenticationToken auth2AuthenticationToken) {
        return this.oAuth2AuthorizedClientService.loadAuthorizedClient(
                auth2AuthenticationToken.getAuthorizedClientRegistrationId(),
                auth2AuthenticationToken.getName());
    }
}
