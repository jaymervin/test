package com.example.test.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@RestController
public class RoutesErrorController implements ErrorController  {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "404 Resource cannot be found";
            }else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "500 Internal Server Error";
            }else if(statusCode == HttpStatus.BAD_REQUEST.value()) {
                return "400 Bad Request";
            }
        }
        return status.toString();
    }
    @Override
    public String getErrorPath() {
        return "/error";
    }
}