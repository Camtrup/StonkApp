package Stonk.rest;

import core.User;


import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StonkRestController {
    @RequestMapping(path = "/user/{username}/{password}", method = RequestMethod.GET)
	public String user(@RequestParam String username, @RequestParam String password) {
        User user = new User();
        User temp = user.isLoginValid(username, password);
		return temp.getFirstName();
	}
    @PostMapping("/user")
    public String postUser(){
        return "asd";
    }
}
