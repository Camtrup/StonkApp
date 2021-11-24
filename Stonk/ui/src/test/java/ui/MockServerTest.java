package ui;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.integration.ClientAndServer;

import core.Stonk;
import core.User;

public class MockServerTest {
    private ClientAndServer mockServer;
    User user = new User("test", "test","test","test",20000000,10000, new ArrayList<Stonk>(), new ArrayList<Stonk>(), false);
    Gson handler = new Gson();

    @BeforeClass
    public void startServer() {
        mockServer = ClientAndServer.startClientAndServer(8080);
    }

    private void returnValidLogin() {
        new MockServerClient(null, 8080)
          .when(
            request()
              .withMethod("GET")
              .withPath("/validate/" + user.getUsername() + "/" + user.getPassword())
              .withBody()
                .respond(
                  response()
                    .withStatusCode(401)
                    .withBody(handler.toJson(user))
                    .withDelay(TimeUnit.SECONDS,1)
                ));
    }


 
    @AfterClass 
    public void stopServer() { 
        mockServer.stop();
    }
 
}