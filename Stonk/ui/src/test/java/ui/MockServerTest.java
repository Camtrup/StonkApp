
package ui;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.google.gson.Gson;

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
  Gson gson = new Gson();

  @BeforeEach
  public void setup(){
    Stonk stock = new Stonk("aapl", 1, 1, "AAAA", 1);
    ArrayList<Stonk> portfolio = new ArrayList<Stonk>();
    portfolio.add(stock);
    User user = new User("test", "test","test","test",20000000,10000, portfolio, new ArrayList<Stonk>(), true);
    
    
    server.start();
    stubFor(get(urlPathMatching("/user/test/" + user.getPassword()))
      .willReturn(aResponse()
      .withBody(gson.toJson(user))));

    stubFor(get(urlPathMatching("/user/test2/" + user.getPassword()))
      .willReturn(aResponse()
      .withBody(gson.toJson(user))));
    
    stubFor(get(urlPathMatching("/isLoginValid/test/" + user.getPassword()))
      .willReturn(aResponse()
      .withBody("200")));

    stubFor(post(urlPathMatching("/new/test/test/test2/test/10000.0/20"))
      .willReturn(aResponse()
      .withBody("200")));
    
    stubFor(post(urlPathMatching("/value/test/" + user.getPassword() + "/10.0"))
      .willReturn(aResponse()
      .withBody("200")));

    stubFor(post(urlPathMatching("/sell/test/098f6bcd4621d373cade4e832627b4f6/aapl/-1"))
      .willReturn(aResponse()
      .withBody("400: Cant be negative or 0")));

    stubFor(post(urlPathMatching("/buy/test/098f6bcd4621d373cade4e832627b4f6/aapl/-1"))
      .willReturn(aResponse()
      .withBody("400: Cant be negative or 0")));

    stubFor(post(urlPathMatching("/sell/test/098f6bcd4621d373cade4e832627b4f6/aapl/2"))
      .willReturn(aResponse()
      .withBody("400: Not enough Stocks")));
    
    stubFor(post(urlPathMatching("/sell/test/098f6bcd4621d373cade4e832627b4f6/aapl/1"))
      .willReturn(aResponse()
      .withBody("200")));
      
    stubFor(post(urlPathMatching("/buy/test/098f6bcd4621d373cade4e832627b4f6/aapl/2"))
      .willReturn(aResponse()
      .withBody("200")));
    
  }

@AfterEach
public void stop(){
  server.stop();
}

}