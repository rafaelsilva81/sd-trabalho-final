package Client.proxy;

import org.json.JSONObject;

import Client.UDPClient;
import Client.exceptions.RequestFailedException;

public class AuthProxy extends Proxy {

  public String login(String username, String password) {
    // (1) Empacota argumentos de entrada (ex: nomeAgenda)
    JSONObject args = new JSONObject();
    args.put("username", username);
    args.put("password", password);

    // (2) Chama doOperation
    String response;
    try {
      response = doOperation("Auth", "login", args);
      return response;
    } catch (RequestFailedException e) {
      System.out.println(e.getMessage());
      return null;
    }

  }

}
