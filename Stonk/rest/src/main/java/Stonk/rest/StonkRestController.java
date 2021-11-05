package Stonk.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class StonkRestController {

    StonkRestHandler handler = new StonkRestHandler();
    JSONArray users = null;

    @GetMapping(path="/users")
    public ArrayList<String> userArray(){
        this.users = users;
        ArrayList<String> s = new ArrayList<String>();
        s.add("asdasd");
        s.add("asdasd");
        return s;
    }
    @GetMapping(path = "/user/{username}/{password}")
	public JSONObject user(@PathVariable String username, @PathVariable String password) { 
        // Request format: url + ?username=X&password=X
		return handler.isLoginValid(username, password, users);
	}
    @PostMapping("/user")
    public String postUser(){
        return "asd";
    }
}
