package com.wimdeblauwe.springissue29120;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Locale;

@ControllerAdvice(annotations = RestController.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class MyExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<?> handleException(Throwable exception,
                                             WebRequest webRequest,
                                             Locale locale) {
        LOGGER.debug("webRequest: {}", webRequest);
        LOGGER.debug("locale: {}", locale);
        LOGGER.error(exception.getMessage(), exception);

        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                             .body("""
                                           {
                                           "code":"UNSUPPORTED_MEDIA_TYPE",
                                           "message":"%s"
                                           }""".formatted(exception.getMessage()));
    }
}
