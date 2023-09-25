package com.nhutin.electric_project.security.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public ModelAndView handleIllegalStateException(IllegalStateException ex) {
        ModelAndView modelAndView = new ModelAndView("register"); // Trang giao diện hiển thị lỗi
        modelAndView.addObject("errorMessageFromGEH", ex.getMessage());
        return modelAndView;
    }
}
