package org.bsshare.tv.controller.web;

import java.util.Locale;

import org.bsshare.tv.common.validator.UserValidator;
import org.bsshare.tv.configuration.multitenancy.TenantContext;
import org.bsshare.tv.model.entity.User;
import org.bsshare.tv.model.front.web.Tenant;
import org.bsshare.tv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("userForm", new User());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		TenantContext.setCurrentTenant(Tenant.ANONYMOUS.getId());
		userValidator.validate(userForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "signup";
		}
		userService.save(userForm);
		TenantContext.setDefaultTenant();
		return "redirect:/";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
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
