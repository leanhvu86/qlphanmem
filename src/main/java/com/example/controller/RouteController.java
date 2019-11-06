package com.example.controller;

import com.example.model.Constants;
import com.example.model.Employee;
import com.example.model.RouteRail;
import com.example.service.RouteRailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class RouteController {

    @Autowired
    RouteRailService routeRailService;

    @Autowired
    HttpSession httpSession;

    @RequestMapping(value = "/routes", method = RequestMethod.GET)
    public String getAllEmployees(Model model, Pageable pageable) {
        if (httpSession.getAttribute(Constants.USER_LOGGED_KEY) != null) {
            Page<RouteRail> productPage = routeRailService.findAll(pageable);
            PageWrapper<RouteRail> page = new PageWrapper<>(productPage, "/routes");
            model.addAttribute("routes", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("message", "");
            return "route";
        } else {
            model.addAttribute("message", "Tài khoản không hợp lệ");
            return "login";
        }
    }
}
