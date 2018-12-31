package com.isanuric.web.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Project: oauth2
 * @author ehsan.salmani@ic-consult.de on 28/12/2018.
 */


@RestController
public class BaseController {

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/user-info")
    public Object userInfo(OAuth2AuthenticationToken auth2AuthenticationToken) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("id", auth2AuthenticationToken.getAuthorizedClientRegistrationId());
        return objectNode;
    }

}
