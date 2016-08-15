package com.yisi.back.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.yisi.back.bean.LoginBean;

@SessionAttributes(value="loginBean")
@Controller
public class LoginController {
	
	@RequestMapping(value="login",method = RequestMethod.POST)
	public String login(String username,String pwd,Model model){
		if("123".equals(username) && "123".equals(pwd)) {
			model.addAttribute("loginBean", new LoginBean());
			return "success";
		}
		return "forward:/login.jsp";
	}
}
