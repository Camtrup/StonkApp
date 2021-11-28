package rest;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import core.User;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.gson.Gson;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

    Gson gson = new Gson();

    User user = new User("test", "test"); //Used for encryptig password, so we can perform requests with the hashed password

	@Test
	public void testEmptyServer() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/user", String.class)).contains("[]");
	}
    
    @Test 
    public void testRequests(){
        //Test if saving is completed
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/save", String.class)).contains("200");
        //Testing that a new user is made
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/new/test/test/test2/test/1000/20", String.class)).contains("200");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/isLoginValid/test2/" + user.getPassword(), String.class).contains("200"));

        //Testing if trasanctions go through
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/buy/test2/" + user.getPassword() +"/gme/1", String.class)).contains("200");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/sell/test2/" + user.getPassword() +"/gme/1", String.class)).contains("200");

        //Testing if watchlist-items are added and removed
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/add/test2/" + user.getPassword() +"/gme", String.class)).contains("200");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/remove/test2/" + user.getPassword() +"/gme", String.class)).contains("200");

        //Testing addmore value
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/value/test2/" + user.getPassword() +"/100", String.class)).contains("200");

        String tem = this.restTemplate.getForObject("http://localhost:" + port + "/user/test2/" + user.getPassword(), String.class);

        User temp = gson.fromJson(tem, User.class);
        assertThat(temp.getPortfolio().size() == 0);
        assertThat(temp.getWatchList().size() == 0);
        assertThat(temp.getCash() > 1000);

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/delete/test2/" + user.getPassword(), String.class)).contains("200");
    }

}
