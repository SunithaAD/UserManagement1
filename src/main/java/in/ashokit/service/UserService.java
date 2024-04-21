package in.ashokit.service;

import java.util.Map;

import in.ashokit.dto.LoginDto;
import in.ashokit.dto.RegisterDto;
import in.ashokit.dto.ResetPwdDto;
import in.ashokit.dto.UserDto;

public interface UserService {
	
	public Map<Integer,String> getCountries();
	public Map<Integer,String> getStates(Integer cid);
	public Map<Integer,String> getCities(Integer sid);
	
	public UserDto getUser(String email);
	public boolean registerUser(RegisterDto regDto);
	public UserDto getUser(LoginDto loginDto);
	public boolean resetPwd(ResetPwdDto pwdDto);
	public String getQuote();//api call

}
