
package ui;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.google.gson.Gson;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import core.Stonk;
import core.User;


public class MockServerTest {

  WireMockServer server = new WireMockServer(8080);

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(8080);

  HttpHandler handler = new HttpHandler();
  User user = new User("test", "test","test","test",20000000,10000, new ArrayList<Stonk>(), new ArrayList<Stonk>(), false);
  Gson gson = new Gson();

  @BeforeEach
  public void setup(){
    server.start();
    stubFor(get(urlPathMatching("/user/test/" + user.getPassword()))
      .willReturn(aResponse()
      .withBody(gson.toJson(user))));
  }

  @Test
  public void exampleTest() {
    stubFor(get(urlPathMatching("/user/test/" + user.getPassword()))
      .willReturn(aResponse()
      .withBody(gson.toJson(user))));

      User s = handler.getUser("test", user.getPassword());
      System.out.println(s.getAge());
      assertEquals(s.getUsername(), user.getUsername());
}


@AfterEach
public void stop(){
  server.stop();
}

}