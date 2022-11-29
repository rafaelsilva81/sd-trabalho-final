package Server.skeletons;

import org.json.JSONObject;

import Server.services.AuthService;

public class AuthSkeleton {
  private AuthService service = new AuthService();

  public JSONObject login(JSONObject arguments) {
    String username = arguments.getString("username");
    String password = arguments.getString("password");
    return service.login(username, password);
  }

}
