package Stonk.rest;

import core.User;
import data.DataHandler;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class StonkRestController {
    @GetMapping(path="/user")
    public String userArray(){
        DataHandler handler = new DataHandler();
        return handler.getAllUsers().toJSONString();
    }
    @GetMapping(path = "/user/{username}/{password}")
	public String user(@PathVariable String username, @PathVariable String password, @RequestBody String json) { 
        // Request format: url + ?username=X&password=X
        String tempJson = json;
        User user = new User();
        User temp = user.isLoginValid(username, password);
		return temp.getFirstName();
	}
    @PostMapping("/user")
    public String postUser(){
        return "asd";
    }
}
