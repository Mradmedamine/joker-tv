package org.bsshare.tv.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class BaseController {

	@Autowired
	private BuildProperties buildProperties;
	
	
	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("buildProperties", buildProperties);
	}
	
}
