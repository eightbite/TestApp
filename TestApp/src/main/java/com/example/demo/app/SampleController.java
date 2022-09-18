package com.example.demo.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SampleController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRegistrationService userRegistrationService;
	
	@GetMapping("/list")
	public String root(Model model) {
		model.addAttribute("title", "Test Title");
		return "html/list";
	}
	
	@GetMapping("/login")
    public String login() {
        return "html/login";
    }
	
	@GetMapping("/signup")
	public String signup(Model model, userRegistrationForm userRegistrationForm) {
		
//		BindingResult result = (BindingResult) model.getAttribute("result");
		
		return "html/signup";
	}
	
	@PostMapping("/registration")
	public String registration(@Validated userRegistrationForm userRegistrationForm,
								BindingResult result,
								Model model,
								RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute("result", result);
			return "redirect:/signup";
		}
		AuthenticatedUser user = new AuthenticatedUser();
		user.setEmail(userRegistrationForm.getEmail());
		user.setUsername(userRegistrationForm.getName());
		user.setPassword(passwordEncoder.encode(userRegistrationForm.getPassword()));
		
		userRegistrationService.registerUser(user);
		
		return "html/login";
	}
}
