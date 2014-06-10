package daniel.sheppard.firefly.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import daniel.sheppard.firefly.domain.User;
import daniel.sheppard.firefly.service.UserService;

@Controller
public class IndexController {

	@Autowired
	UserService userService;

	@Value("${hello}")
	private String hello;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/user")
	@ResponseBody
	public Page<User> user() {
		return userService.findAllUser();
	}

	@RequestMapping("/user/save")
	@ResponseBody
	public User saveUser(String name) {
		User user = new User();
		user.setName(name);
		user.setLoginName("firefly");
		user.setLoginPassWord("firefly");
		userService.saveUser(user);
		return user;
	}
}
