package Client.proxy;

import org.json.JSONObject;

import Client.UDPClient;
import Client.exceptions.RequestFailedException;

/* 
  * Classe que representa o proxy do serviço de autenticação
  * Extende a classe Proxy
*/
public class AuthProxy extends Proxy {

  // Método remoto 01 - Login
  public String login(String username, String password) {
    JSONObject args = new JSONObject();
    args.put("username", username);
    args.put("password", password);

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
