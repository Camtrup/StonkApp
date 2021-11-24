package ui;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URISyntaxException;
import java.util.ArrayList;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.google.gson.Gson;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.Stonk;
import core.User;

public class MockServerTest {

  private WireMockConfiguration config;
  private WireMockServer wireMockServer;

  Gson gson = new Gson();

  private HttpHandler handler;
  User temp = new User("test", "test","test","test",20000000,10000, new ArrayList<Stonk>(), new ArrayList<Stonk>(), false);
  
  @BeforeEach
  public void startWireMockServerAndSetup() throws URISyntaxException {
    config = WireMockConfiguration.wireMockConfig().port(8080);
    wireMockServer = new WireMockServer(config.portNumber());
    wireMockServer.start();
    WireMock.configureFor("localhost", config.portNumber());
    handler = new HttpHandler(wireMockServer.port());
  }

  @Test
  public void testGetTodoListNames() {
    stubFor(get(urlEqualTo("/user/" + temp.getUsername() + "/" + temp.getPassword()))
        .willReturn(aResponse()
            .withBody(gson.toJson(temp))
        )
    );
    User user = handler.getUser(temp.getUsername(), temp.getPassword());
    assertEquals(user.getPassword(), temp.getPassword());
  }

  @AfterEach
  public void stopWireMockServer() {
    wireMockServer.stop();
  }
}
