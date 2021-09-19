package com.sty.component;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.server.ResponseStatusException;

@Component
public class RestTemplateResponseErrorHandler extends DefaultResponseErrorHandler  {

    private static Logger logger = LoggerFactory.getLogger(RestTemplateResponseErrorHandler.class);

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {

        if (httpResponse.getStatusCode().series() != HttpStatus.Series.SUCCESSFUL) {
            logger.error("error happened when using restTemplate. code: {}", httpResponse.getStatusCode());

            // if token is invalid, CMC will return "401 UNAUTHORIZED" error
            if (httpResponse.getStatusCode().is4xxClientError()) {
                throw new ResponseStatusException( HttpStatus.UNAUTHORIZED, "Token is invalid");
            }
        }
    }
}
