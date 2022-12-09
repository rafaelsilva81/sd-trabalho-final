package Server.skeletons;

import org.json.JSONObject;

import Server.services.AuthService;

/* 
  * Classe que representa o esqueleto do serviço de autenticação
*/
public class AuthSkeleton {
  private AuthService service = new AuthService();

  // Método remoto 01 - Faz o login do usuário
  public JSONObject login(JSONObject arguments) {
    String username = arguments.getString("username");
    String password = arguments.getString("password");
    return service.login(username, password);
  }

}
