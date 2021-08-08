package com.upgradeinc.extremecamp.customer.validation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@PropertySource("classpath:message.properties")
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private Environment env;
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> env.getProperty(x.getDefaultMessage().replace("{", "").replace("}","")) != null ? env.getProperty(x.getDefaultMessage().replace("{", "").replace("}","")) : x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);

    }
	
	@ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
	ResponseEntity<Object> exceptionHandler(ConstraintViolationException e) {
		Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        
        Iterator<ConstraintViolation<?>> it = e.getConstraintViolations().iterator();
        List<String> errors = new ArrayList<String>();
        while(it.hasNext()) {
        	ConstraintViolation<?> val = it.next();
        	errors.add(env.getProperty(val.getMessage().replace("{", "").replace("}","")) != null ? env.getProperty(val.getMessage().replace("{", "").replace("}","")) : val.getMessage());
        	
        }
        
        body.put("errors", errors);
        
        return  new ResponseEntity<>(body, null, HttpStatus.BAD_REQUEST);
    }

}