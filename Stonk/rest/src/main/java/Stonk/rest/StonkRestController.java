package Stonk.rest;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import core.User;



@RestController
public class StonkRestController {
    
    @Autowired
    private StonkRestService stonkRestService;

    @RequestMapping("/user")
    public ArrayList<User> userArray(){
        return stonkRestService.getAllUsers();
    }
    @RequestMapping(value = "/user/{username}/{password}")
	public User isLoginValid(@PathVariable String username,@PathVariable String password) { 
		return stonkRestService.isLoginValid(username, password);
	}

    @RequestMapping(value = "/buy/{username}/{password}/{ticker}/{count}")
    public String buyStonks(@PathVariable String username, @PathVariable String password, @PathVariable String ticker, @PathVariable int count){
        return stonkRestService.buyStonks(username, password, ticker, count);
    }
    @RequestMapping(value = "/sell/{username}/{password}/{ticker}/{count}")
    public String sellStonks(@PathVariable String username, @PathVariable String password, @PathVariable String ticker, @PathVariable int count){
        return stonkRestService.sellStonks(username, password, ticker, count);
    }
    @RequestMapping(value = "/new/{firstname}/{lastname}/{username}/{password}/{cash}/{age}")
    public String newUser(@PathVariable String firstname, @PathVariable String lastname, @PathVariable String username, @PathVariable String password, @PathVariable Float cash, @PathVariable int age){
        return stonkRestService.newUser(firstname, lastname, username, password, cash, age);
    }
    
    @RequestMapping("/save")
    public String save(){
        return stonkRestService.saveJSON();
    }

    
}
