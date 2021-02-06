package ru.ofd.lk.view_services;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import ru.ofd.lk.security.auth.UserService;
import ru.ofd.lk.security.models.lkAuth.AuthUser;
import ru.ofd.lk.services.BalanceService;

@Service
public class LKViewService implements ApplicationViewService {

    private final UserService<AuthUser> userService;
    private final BalanceService balanceService;

    @Data
    private static class AuthRequestModel {
        private int balance;
        private String userId;
    }

    @Autowired
    public LKViewService(UserService<AuthUser> service, BalanceService balanceService) {
        this.userService = service;
        this.balanceService = balanceService;
    }

    @Override
    public ModelAndView getDefaultView() {
        ModelAndView returnValue = new ModelAndView("lkPage");
        returnValue.addObject("error", "");
        return returnValue;
    }

    @Override
    public ModelAndView getView(String userId) throws Exception {
        ModelAndView returnValue = new ModelAndView("lkPage");
        returnValue.addObject("user", userService.getUserById(userId));
        returnValue.addObject("balance", balanceService.getBalanceByUserId(userId));
        returnValue.addObject("error", "");
        return returnValue;
    }

    @Override
    public ModelAndView getErrorView(String message){
        ModelAndView returnValue = new ModelAndView("lkPage");
        returnValue.addObject("error", message);
        return returnValue;
    }

    @Override
    public ModelAndView modifierView(Object... values) throws Exception {
        balanceService.editBalance((String) values[0], (Integer) values[1]);
        ModelAndView returnValue = new ModelAndView("lkPage");
        returnValue.addObject("user", userService.getUserById((String) values[0]));
        returnValue.addObject("balance", balanceService.getBalanceByUserId((String) values[0]));
        returnValue.addObject("error", "");
        return returnValue;
    }
}
