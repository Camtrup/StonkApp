package Stonk.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;

import core.User;

public class HttpHandler {

    private Gson handler = new Gson();
    
    public User getUser(String username, String password) {
        User user = null;
        try{
            HttpRequest request = HttpRequest.newBuilder()
                                    .uri(new URI("http://localhost:8080/user/" + username + "/" + password))
                                    .GET()
                                    .build();
            final HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            user = handler.fromJson(response.body(), User.class);
        }
        catch(InterruptedException | IOException | URISyntaxException e){
            System.out.println(e);
        }   
        return user;
    }

    public String buyOrSellStonk(boolean sell, String username, String password, String ticker, int count) {
        String resp = "";
        String method = "buy";
        if (sell){
            method = "sell";
        }
        try{
            HttpRequest request = HttpRequest.newBuilder()
                                    .uri(new URI("http://localhost:8080/" + method + "/" + username + "/" + password + "/" + ticker + "/" + count))
                                    .GET()
                                    .build();
            final HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            resp = response.body();
        }
        catch(InterruptedException | IOException | URISyntaxException e){
            System.out.println(e);
        }   
        return resp;
    }

    public String addOrRemoveStonk(boolean remove, String username, String password, String ticker, int count) {
        String resp = "";
        String method = "add";
        if (remove){
            method = "remove";
        }
        try{
            HttpRequest request = HttpRequest.newBuilder()
                                    .uri(new URI("http://localhost:8080/" + method + "/" + username + "/" + password + "/" + ticker + "/" + count))
                                    .GET()
                                    .build();
            final HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            resp = response.body();
        }
        catch(InterruptedException | IOException | URISyntaxException e){
            System.out.println(e);
        }   
        return resp;
    }

    
    public static void main(String[] args) throws URISyntaxException {
        HttpHandler handler = new HttpHandler();
        User d = handler.getUser("test", "fb469d7ef430b0baf0cab6c436e70375");
        System.out.println(d.getUsername());
        System.out.println(d.getFirstName());
        System.out.println(d.getLastName());
        System.out.println(d.getPassword());
    }

}
