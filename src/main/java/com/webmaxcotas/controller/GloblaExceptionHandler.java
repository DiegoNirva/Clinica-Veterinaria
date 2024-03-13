package com.webmaxcotas.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GloblaExceptionHandler {


    @ExceptionHandler(Exception.class)
    public String HadlerExeption(Exception e, Model model){
        model.addAttribute("error", e.getMessage());
        return "error";
    }

}
