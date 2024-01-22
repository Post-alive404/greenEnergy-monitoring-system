package com.dden.greenEnergy.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handError(HttpServletRequest request){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        //Перевірка на статус 404
        if(status != null &&
                Integer.valueOf(status.toString()) == HttpStatus.NOT_FOUND.value()){
            return "404";
        }
        return "error";
    }
}


