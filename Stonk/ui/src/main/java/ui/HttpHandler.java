package ui;

import com.google.gson.Gson;
import core.User;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;

/**
 * Class for HTTP-handler.
 */
public class HttpHandler {

  private Gson handler = new Gson();

  /**
   * Getter for User.
   *
   * @param username String.
   * @param password String.
   * @return the user.
   */
  public User getUser(String username, String password) {
    User user = null;
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(new URI("http://localhost:8080/user/" + username + "/" + password))
          .GET()
          .build();
      final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());
      user = handler.fromJson(response.body(), User.class);
    } catch (InterruptedException | IOException | URISyntaxException e) {
      System.out.println(e);
    }
    return user;
  }

  /**
   * Funk for buying or selling with resp return.
   *
   * @param sell boolean.
   * @param username String.
   * @param password String.
   * @param ticker String.
   * @param count Int.
   * @return a response.
   */
  public String buyOrSellStonk(boolean sell, String username, String password,
          String ticker, int count) {
    String resp = "";
    String method = "buy";
    if (sell) {
      method = "sell";
    }
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(
              new URI("http://localhost:8080/" + method + "/" + username + "/" + password + "/" + ticker + "/" + count))
          .POST(BodyPublishers.ofString(""))
          .build();
      final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());
      resp = response.body();
    } catch (InterruptedException | IOException | URISyntaxException e) {
      System.out.println(e);
    }
    return resp;
  }

  /**
   * New user.
   *
   * @param firstName String.
   * @param lastName String.
   * @param username String.
   * @param password String.
   * @param cash Float.
   * @param age int.
   * @return A response.
   */
  public String newUser(String firstName, String lastName, String username,
      String password, Float cash, int age) {
    String resp = "";
    try {
      HttpRequest request = HttpRequest.newBuilder().uri(new URI("http://localhost:8080/new/"
          + firstName + "/" + lastName + "/" + username + "/"
              + password + "/" + cash + "/" + age))
              .POST(BodyPublishers.ofString(""))
              .build();
      final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());
      resp = response.body();
    } catch (InterruptedException | IOException | URISyntaxException e) {
      System.out.println(e);
    }
    return resp;
  }

  /**
   * Removes a user.
   *
   * @param username String.
   * @param password String.
   * @return a response if its okay.
   */
  public String removeUser(String username, String password) {
    String resp = "";
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(new URI("http://localhost:8080/delete/" + username + "/" + password))
          .POST(BodyPublishers.ofString(""))
          .build();
      final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());
      resp = response.body();
    } catch (InterruptedException | IOException | URISyntaxException e) {
      System.out.println(e);
    }
    return resp;
  }

  /**
   * Checks if login is valid for a given username and password.
   *
   * @param username String.
   * @param password String.
   * @return a response if its valid.
   */
  public String isLoginValid(String username, String password) {
    String resp = "";
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(new URI("http://localhost:8080/isLoginValid/" + username + "/" + password)).GET().build();
      final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());
      resp = response.body();
    } catch (InterruptedException | IOException | URISyntaxException e) {
      System.out.println(e);
    }
    return resp;
  }

  /**
   * Adds more value.
   *
   * @param username String.
   * @param password String.
   * @param cash Float.
   * @return response.
   */
  public String addMoreValue(String username, String password, Float cash) {
    String resp = "";
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(new URI("http://localhost:8080/value/" + username + "/" + password + "/" + cash))
          .POST(BodyPublishers.ofString(""))
          .build();
      final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());
      resp = response.body();
    } catch (InterruptedException | IOException | URISyntaxException e) {
      System.out.println(e.getMessage());
    }
    return resp;
  }

  /**
   * Add or removes from WatchList.
   *
   * @param remove Boolean.
   * @param username String.
   * @param password String.
   * @param ticker String.
   * @return a response.
   */
  public String addOrRemoveWatchList(boolean remove,
      String username, String password, String ticker) {
    String resp = "";
    String method = "add";
    if (remove) {
      method = "remove";
    }
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(new URI("http://localhost:8080/" + method + "/" + username + "/" + password + "/" + ticker))
          .POST(BodyPublishers.ofString(""))
          .build();
      final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());
      resp = response.body();
    } catch (InterruptedException | IOException | URISyntaxException e) {
      System.out.println(e);
    }
    return resp;
  }

  /**
   * For saving.
   *
   * @return a response.
   */
  public String save() {
    String resp = "";
    try {
      HttpRequest request = HttpRequest.newBuilder().uri(new URI("http://localhost:8080/save")).GET().build();
      final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());
      resp = response.body();
    } catch (InterruptedException | IOException | URISyntaxException e) {
      System.out.println(e);
    }
    return resp;
  }

  /**
   * For saving.
   *
   * @return a response.
   */
  public String testMode() {
    String resp = "";
    try {
      HttpRequest request = HttpRequest.newBuilder().uri(new URI("http://localhost:8080/test")).GET().build();
      final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());
      resp = response.body();
    } catch (InterruptedException | IOException | URISyntaxException e) {
      System.out.println(e);
    }
    return resp;
  }
}
