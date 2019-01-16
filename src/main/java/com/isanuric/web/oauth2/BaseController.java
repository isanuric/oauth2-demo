package com.isanuric.web.oauth2;

import java.util.Arrays;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Project: oauth2
 */


@RestController
public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Lazy
    @Autowired
    DefaultCookieSerializer defaultCookieSerializer;

    @Lazy
    @Autowired
    private OAuth2AuthorizedClientService oAuth2AuthorizedClientService;


    @GetMapping("/user-info")
    public Object userInfo(OAuth2AuthenticationToken auth2AuthenticationToken, HttpServletRequest request) {

        logger.debug("request1: {}", request.getRequestURI());
        logger.debug("BBB: session: {}", defaultCookieSerializer.readCookieValues(request));
//        HttpSession session = request.getSession(false);
//        logger.debug("session: {}", session.getAttributeNames());
//        logger.debug("session: {}", session.getId());
//        logger.debug("session: {}", session.getServletContext().getAttributeNames());

        Cookie[] allCookies = request.getCookies();
        if (allCookies != null) {
            Arrays.stream(allCookies).forEach(x -> {
                 logger.debug("cookie: {}", x.getName());
            });
        }

        return this.oAuth2AuthorizedClientService.loadAuthorizedClient(
                auth2AuthenticationToken.getAuthorizedClientRegistrationId(),
                auth2AuthenticationToken.getName());
    }
}
