package rest;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @RequestMapping(value = "/isLoginValid/{username}/{password}")
	public String isLoginValid(@PathVariable String username,@PathVariable String password) { 
		return stonkRestService.isLoginValid(username, password);
	}
    @RequestMapping(value = "/user/{username}/{password}")
	public User getUser(@PathVariable String username,@PathVariable String password) { 
		return stonkRestService.getUser(username, password);
	}
    @RequestMapping(value = "/buy/{username}/{password}/{ticker}/{count}")
    public String buyStonks(@PathVariable String username, @PathVariable String password, @PathVariable String ticker, @PathVariable int count){
        return stonkRestService.buyStonks(username, password, ticker, count);
    }
    @RequestMapping(value = "/sell/{username}/{password}/{ticker}/{count}")
    public String sellStonks(@PathVariable String username, @PathVariable String password, @PathVariable String ticker, @PathVariable int count){
        return stonkRestService.sellStonks(username, password, ticker, count);
    }

    @RequestMapping(value = "/add/{username}/{password}/{ticker}")
    public String addStonksToWatchList(@PathVariable String username, @PathVariable String password, @PathVariable String ticker){
        return stonkRestService.addStonksToWatchList(username, password, ticker);
    }
    @RequestMapping(value = "/remove/{username}/{password}/{ticker}")
    public String removeStonksToWatchList(@PathVariable String username, @PathVariable String password, @PathVariable String ticker){
        return stonkRestService.removeStonksFromWatchList(username, password, ticker);
    }
    @RequestMapping(value = "/new/{firstname}/{lastname}/{username}/{password}/{cash}/{age}")
    public String newUser(@PathVariable String firstname, @PathVariable String lastname, @PathVariable String username, @PathVariable String password, @PathVariable Float cash, @PathVariable int age){
        return stonkRestService.newUser(firstname, lastname, username, password, cash, age);
    }
    @RequestMapping(value = "/delete/{username}/{password}")
    public String removeUser(@PathVariable String username, @PathVariable String password){
        return stonkRestService.removeUser(username, password);
    }
    @RequestMapping(value = "/value/{username}/{password}/{cash}")
    public String addMoreValue(@PathVariable String username, @PathVariable String password,  @PathVariable Float cash){
        return stonkRestService.addMoreCash(username, password, cash);
    }
    
    @RequestMapping("/save")
    public String save(){
        return stonkRestService.saveJSON();
    }

    
}
