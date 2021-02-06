package ru.ofd.lk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.ofd.lk.view_services.ApplicationViewService;

@Controller
@RequestMapping("lk/api/app")
@CrossOrigin(origins = "http://localhost:4200")
public class LkControllers {

    //Access role
    private static final String roles = "hasRole('USER')";

    private final ApplicationViewService lkVView;

    @Autowired
    public LkControllers(@Qualifier("LKViewService") ApplicationViewService lkVView) {
        this.lkVView = lkVView;
    }

    @GetMapping("balance/{Id}")
    @PreAuthorize(roles)
    public ModelAndView lkPage(@PathVariable("Id") String id) {
        try {
            //Returns LK view (userName + balance)
            return lkVView.getView(id);
        } catch (Exception ex){
            return lkVView.getErrorView(ex.getMessage());
        }
    }

    @PostMapping("balance/{Id}")
    @PreAuthorize(roles)
    public ModelAndView lkPage(@PathVariable("Id") String id, @RequestParam(value = "balance") int balance) {
        try {
            return lkVView.modifierView(id, balance);
        } catch (Exception ex){
            return lkVView.getErrorView(ex.getMessage());
        }
    }
}
