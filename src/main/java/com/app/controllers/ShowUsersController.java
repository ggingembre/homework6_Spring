package com.app.controllers;

import com.app.util.InitDefaultEntities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.app.entities.*;
import com.app.services.UserService;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping(value = "/user")
public class ShowUsersController {

    private final UserService usersService;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(Product.class);

    @Autowired
    public ShowUsersController(UserService usersService, PasswordEncoder passwordEncoder) {
        this.usersService = usersService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/show")
    public ModelAndView showUsers() {
        List<String> usernames =
                usersService.findAll().stream().map(User::toString).collect(Collectors.toList());
//        usersService.findAll().stream()
//                .map(user->usersService.getOne(user.getUsername()).getExperiences())
//                .map(experience -> experience.toString()).collect(Collectors.toList());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        modelAndView.addObject("users", usernames);
        return modelAndView;
    }

   @PostConstruct
   public void initDefaultUsers() {
        InitDefaultEntities.initDefaultUsers(usersService,passwordEncoder);
   }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout() {
        // http://localhost:8080/login?logout
        return "redirect:/login?logout";
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        logger.error("Request: " + req.getRequestURL() + " raised " + ex);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

}
