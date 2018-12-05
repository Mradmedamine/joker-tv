package org.bsshare.tv.controller.web;

import java.util.Locale;

import org.bsshare.tv.common.validator.UserValidator;
import org.bsshare.tv.model.entity.User;
import org.bsshare.tv.service.SecurityService;
import org.bsshare.tv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private MessageSource messageSource;

	@GetMapping("/userProfile")
	public String userProfile(String changePassword, Model model) {
		model.addAttribute("userProfile", SecurityService.getUserTenantDetails().get());
		if (changePassword != null) {
			model.addAttribute("message", "Password changed successfully");
		}
		return "userProfile";
	}

	@PostMapping("/userProfile/changePassword")
	public String changePassword(String password, Model model) {
		userService.changePassword(password);
		return "redirect:/userProfile?changePassword";
	}

	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("userForm", new User());
		return "signup";
	}

	@PostMapping("/signup")
	public String signup(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		userValidator.validate(userForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "signup";
		}
		userService.saveUser(userForm);
		return "redirect:/";
	}

	@GetMapping("/login")
	public String login(Model model, String error, String logout, Locale locale) {
		Object[] args = new Object[] {};
		if (error != null) {
			String loginFailureMessage = messageSource.getMessage("common.login.failure", args, locale);
			model.addAttribute("error", loginFailureMessage);
		}
		if (logout != null) {
			String logoutSuccessMessage = messageSource.getMessage("common.logout.success", args, locale);
			model.addAttribute("message", logoutSuccessMessage);
		}
		return "login";
	}

}
