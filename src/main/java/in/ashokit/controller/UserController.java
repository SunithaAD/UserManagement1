package in.ashokit.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import in.ashokit.dto.LoginDto;
import in.ashokit.dto.RegisterDto;
import in.ashokit.dto.ResetPwdDto;
import in.ashokit.dto.UserDto;
import in.ashokit.service.UserService;

@Controller
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
	public String registerPage(Model model)
	{
		model.addAttribute("registerDto", new RegisterDto());
		Map<Integer,String> countriesMap = userService.getCountries();
		model.addAttribute("countries",countriesMap);
		return "registerView";
	}
	
	@GetMapping("/states/{cid}")
	@ResponseBody
	public Map<Integer,String> getStates(@PathVariable("cid")Integer cid)
	{
		return userService.getStates(cid);
	}
	
	@GetMapping("/cities/{sid}")
	@ResponseBody
	public Map<Integer,String> getCities(@PathVariable("sid")Integer sid)
	{
		return userService.getCities(sid);
	}
	
	@PostMapping("/register")
	public String register(RegisterDto regDto,Model model)
	{
		UserDto user = userService.getUser(regDto.getEmail());
		
		if(user!=null)
		{
			model.addAttribute("emsg","Duplicate Email");
			
			return "registerView";
			
		}
		boolean registerUser = userService.registerUser(regDto);
		if(registerUser)
		{
			model.addAttribute("smsg","User Registered");
		}
		else
		{
			model.addAttribute("emsg","User Failed");
		}
		model.addAttribute("countries",userService.getCountries());
		
		return "registerView";
	}
	
	@GetMapping("/")
	public String loginPage(Model model)
	{
		model.addAttribute("loginDto", new LoginDto());
		return "index";
	}
	
	@PostMapping("/login")
	public String login(LoginDto loginDto,Model model)
	{
		UserDto user = userService.getUser(loginDto);
		if(user==null)
		{
			model.addAttribute("emsg", "INVALID CREDENTIALS");
			return "index";
		}
		if("YES".equals(user.getPwdUpdated()))
		{
			//pwd already updated:go to dashboard
			return "redirect:dashboard";
		}
		else
		{
			//pwd not updated: go to reset password page
			ResetPwdDto resetPwdDto = new ResetPwdDto();
			resetPwdDto.setEmail(user.getEmail());
			model.addAttribute("resetPwdDto", resetPwdDto);
			
			return "resetPwdView";
		}
		
	}
	
	@PostMapping("/resetPwd")
	public String resetPwd(ResetPwdDto pwdDto,Model model)
	{
		if(!(pwdDto.getNewPwd().equals(pwdDto.getConfirmPwd())))
		{
			model.addAttribute("emsg", "New Password and Confirm Password shd be same");
			return "resetPwdView";
		}
		UserDto user = userService.getUser(pwdDto.getEmail());
		if(user.getPwd().equals(pwdDto.getOldPwd()))
		{
			boolean resetPwd = userService.resetPwd(pwdDto);
			if(resetPwd)
			{
				return "redirect:dashboard";
			}
			else
			{
				model.addAttribute("emsg", "pwd Update failed");
				return "resetPwdView";
			}
		}
		else
		{
			model.addAttribute("emsg", "Given old Pwd is wrong");
			return "resetPwdView";
		}
		
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model)
	{
		String quote = userService.getQuote();
		model.addAttribute("quote",quote);
		return "dashboardView";
	}
	
	@GetMapping("/logout")
	public String logout()
	{
		return "redirect:/";
	}

}
