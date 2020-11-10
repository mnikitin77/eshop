package com.mvnikitin.eshop.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
@ControllerAdvice
public class CustomErrorController implements ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(CustomErrorController.class);

    @RequestMapping("/error")
    public String handleError(HttpServletRequest req, Exception ex, Model model) throws Exception {
        logger.error("Request: " + req.getRequestURL() + " raised " + ex);

        Object status = req.getAttribute(
                RequestDispatcher.ERROR_STATUS_CODE);
        model.addAttribute("code", status.toString());

        model.addAttribute("uri", req.getAttribute(
                RequestDispatcher.ERROR_REQUEST_URI));

        model.addAttribute("message", req.getAttribute(
                RequestDispatcher.ERROR_MESSAGE));

        return "error";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
