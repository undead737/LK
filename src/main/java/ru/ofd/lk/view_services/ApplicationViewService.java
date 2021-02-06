package ru.ofd.lk.view_services;

import org.springframework.web.servlet.ModelAndView;

public interface ApplicationViewService {
    ModelAndView getDefaultView();
    ModelAndView getView(String userId) throws Exception;
    ModelAndView getErrorView(String userId);
    ModelAndView modifierView(Object ... values) throws Exception;
}
