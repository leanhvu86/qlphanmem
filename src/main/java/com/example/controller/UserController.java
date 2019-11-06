package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Passenger;
import com.example.service.PassengerService;
import com.example.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	PassengerService passengerService;

	@RequestMapping("/register")
	public String defectDetails(Model model) {
		model.addAttribute("passenger", new Passenger());
		return "register";
	}

	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public String validate2(ModelMap model, @Validated @ModelAttribute("passenger") Passenger passenger,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "register";
		} else {
			if (passengerService.savePassenger(passenger)) {
				model.addAttribute("message", "Lưu thành công");
				model.addAttribute("passenger", passenger);
				return "success";
			} else {
				model.addAttribute("message", "Lưu không thành công");
				model.addAttribute("passenger", passenger);
				return "register";
			}
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String validate(ModelMap model, @Validated @ModelAttribute("passenger") Passenger passenger,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "register";
		} else {
			if (passengerService.savePassenger(passenger)) {
				model.addAttribute("message", "Cập nhật thành công");
				model.addAttribute("passenger", passenger);
				return "success";
			} else {
				model.addAttribute("message", "Cập nhật không thành công");
				model.addAttribute("passenger", passenger);
				return "register";
			}
		}
	}
}
