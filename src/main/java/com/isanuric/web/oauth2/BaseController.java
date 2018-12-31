package com.isanuric.web.oauth2;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * ----------------------------------------------
 * (c) 2018 Copyright iC Consult GmbH
 * <p/>
 * Project: oauth2
 * @author ehsan.salmani@ic-consult.de on 28/12/2018.
 */


@RestController
public class BaseController {

//    @Autowired
//    private OAuth2AuthorizedClientService authorizedClientService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/user-info")
    public Object userInfo(OAuth2AuthenticationToken auth2AuthenticationToken) {
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("id", auth2AuthenticationToken.getAuthorizedClientRegistrationId());
            objectNode.put("name", auth2AuthenticationToken.getName());
         return objectNode;
    }

}
