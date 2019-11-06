package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.model.*;
import com.example.service.EmployeeService;
import com.example.service.NewsService;
import com.example.service.RoleService;
import com.example.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;
	
	@Autowired
	EmployeeService employeeService;

	@Autowired
	NewsService newsService;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String goHome(ModelMap modelMap, Pageable pageable) {
//		Page<News> productPage =   newsService.findAll(pageable);
//		PageWrapper<News> page = new PageWrapper<>(productPage, "/lstnews");
//		modelMap.addAttribute("lstnews", page.getContent());
//		modelMap.addAttribute("page", page);
//		modelMap.addAttribute("routeVO",new RouteVO());
//		return "index";
		return "login";
	}

	@RequestMapping("/login")
	public String defectDetails() {
		return "login";
	}

	@RequestMapping("/admin")
	public String login(HttpServletRequest httpServletRequest, Model model, Pageable pageable,
			HttpSession httpSession) {
		String username = httpServletRequest.getParameter("username");
		String password = httpServletRequest.getParameter("password");

		if (httpSession.getAttribute(Constants.USER_LOGGED_KEY) != null) {
			Page<Employee> productPage = employeeService.findAll(pageable);
			PageWrapper<Employee> page = new PageWrapper<>(productPage, "/employees");
			page.isFirstPage();
			LoginUser loginUser = new LoginUser();
			loginUser.setUsername(username);
			loginUser.setPassword(password);
			httpSession.setAttribute(Constants.USER_LOGGED_KEY, loginUser);
			model.addAttribute("employees", page.getContent());
			model.addAttribute("page", page);
			return "admin";
		} else {
			if (username != null && password != null) {
				User user = userService.findByUsername(username);
				if (user != null) {
					if (user.getPassword().equals(password)) {
//						Page<Employee> productPage = employeeService.findAll(pageable);
//						PageWrapper<Employee> page = new PageWrapper<>(productPage, "/employees");
//						page.isFirstPage();
						User userlogged = new User();
						userlogged.setUsername(username);
						userlogged.setPassword(password);
						Role role= roleService.findByRoleId(((Role) user.getRoles()).getRoleId());
						if (role.getRoleName().equals("ADMIN")) {
							httpSession.setAttribute(Constants.ROLE_ADMIN, role.getRoleName());
						}else {
							httpSession.setAttribute(Constants.ROLE_USER, role.getRoleName());
						}
						httpSession.setAttribute(Constants.USER_LOGGED_KEY, userlogged);
//						model.addAttribute("employees", page.getContent());
//						model.addAttribute("page", page);
						return "admin";
					} else {
						model.addAttribute("message", "Mật khẩu không hợp lệ");
						return "login";
					}
				} else {
					model.addAttribute("message", "Tài khoản không tồn tại");
					return "login";
				}

			}

		}
		model.addAttribute("message", "Tài khoản không tồn tại");
		return "login";
	}

}
