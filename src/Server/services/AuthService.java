package Server.services;

import java.util.List;

import org.json.JSONObject;

import Server.util.CSVUtil;

/* 
  * Classe que representa o serviço de autenticação
*/
public class AuthService {

  private CSVUtil csvUtil = new CSVUtil("src\\Server\\data\\users.csv");

  public JSONObject login(String username, String password) {
    JSONObject response = new JSONObject();
    // Ler o CSV de usuarios
    List<String[]> users = csvUtil.read();
    // Verificar se o usuario existe
    for (String[] user : users) {
      System.out.println(user[0] + " - " + user[1]);
      if (user[0].equals(username) && user[1].equals(password)) {
        response.put("status", 200);
        response.put("message", "Login realizado com sucesso!");
        return response;
      }
    }

    response.put("status", 401);
    response.put("message", "Usuario ou senha invalidos!");
    return response;
  }

}
