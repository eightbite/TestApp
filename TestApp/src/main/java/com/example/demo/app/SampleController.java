package com.example.demo.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SampleController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRegistrationService userRegistrationService;
	
	@Autowired
	private userFindAllService userFindAllService;
	
	@GetMapping("/list")
	public String root(Model model) {
		
		List<AuthenticatedUser> allUser = userFindAllService.userFindAll();
		
		model.addAttribute("users", allUser);
		
		return "html/list";
	}
	
	@GetMapping("/login")
    public String login() {
        return "html/login";
    }
	
	@GetMapping("/signup")
	public String signup(Model model) {
		
//		BindingResult result = (BindingResult) model.getAttribute("result");
		model.addAttribute("userRegistrationForm", new userRegistrationForm());
		return "html/signup";
	}
	
	@PostMapping("/signup")
	public String registration(@Validated @ModelAttribute userRegistrationForm userRegistrationForm,
								BindingResult result,
								Model model,
								RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			return "html/signup";
		}
		AuthenticatedUser user = new AuthenticatedUser();
		user.setEmail(userRegistrationForm.getEmail());
		user.setUsername(userRegistrationForm.getName());
		user.setPassword(passwordEncoder.encode(userRegistrationForm.getPassword()));
		
		userRegistrationService.registerUser(user);
		
		return "redirect:/login";
	}
}
