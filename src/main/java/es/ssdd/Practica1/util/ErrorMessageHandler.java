package es.ssdd.Practica1.util;

import org.springframework.web.servlet.ModelAndView;

public class ErrorMessageHandler {
    public ModelAndView errorMessage(String message, String redirect){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("message-error");
        modelAndView.addObject("message",message);
        modelAndView.addObject("redirect",redirect);
        return modelAndView;
    }
}
