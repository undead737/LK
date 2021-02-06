package ru.ofd.lk.controllers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ofd.lk.security.auth.UserService;
import ru.ofd.lk.security.auth.jwt.JwtResponse;
import ru.ofd.lk.security.auth.jwt.JwtTokenFilter;
import ru.ofd.lk.security.auth.jwt.JwtTokenProvider;
import ru.ofd.lk.security.models.lkAuth.AuthRole;
import ru.ofd.lk.security.models.lkAuth.AuthUser;

import java.util.stream.Collectors;

@Controller
@RequestMapping("lk/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider provider;
    private final UserService<AuthUser> userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider provider, UserService<AuthUser> userService) {
        this.authenticationManager = authenticationManager;
        this.provider = provider;
        this.userService = userService;
    }

    @Data
    private static class AuthRequestModel {
        private String loginIdentifier;
        private String loginPassword;
    }

    @GetMapping("login")
    public String authPage(Model model) {
        model.addAttribute("loginForm", new AuthRequestModel());
        return "loginPage";
    }

    @GetMapping("register")
    public String registerPage(Model model) {
        model.addAttribute("regForm", new AuthUser());
        return "registerPage";
    }

    @PostMapping("login")
    public String auth(@ModelAttribute("loginForm") AuthRequestModel authData, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "loginPage";
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authData.getLoginIdentifier(), authData.getLoginPassword()));
            final AuthUser user = userService.getUserByName(authData.getLoginIdentifier());
            //Response on authorization in the app. It's usually placed in local storage and transferred to header for each requests is required.
            //This app only simulates the arrival of JWT. All processing logic in JwtTokenFilter
            JwtResponse temp = new JwtResponse(provider.generateToken(authData.getLoginIdentifier(),
                    user.getRoles()), authData.getLoginIdentifier(), user.getFirstName(),
                    user.getRoles().stream().map(AuthRole::getRoleName).collect(Collectors.toList()));
            //simulating
            JwtTokenFilter.login(temp.getAccessToken());
            return "redirect:/lk/api/app/balance/" + user.getId();
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
            return "loginPage";
        }
    }

    @PostMapping("register")
    public String register(@ModelAttribute("regForm") AuthUser userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registerPage";
        }
        try {
            userService.register(userForm);
            return "redirect:/lk/api/auth/login/";
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
            return "registerPage";
        }
    }

    @GetMapping("logout")
    public String logout(){
        //simulating
        JwtTokenFilter.logout();
        return "redirect:/lk/api/auth/login/";
    }
}