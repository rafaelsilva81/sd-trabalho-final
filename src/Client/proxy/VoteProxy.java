package Client.proxy;

import org.json.JSONObject;

import Client.exceptions.RequestFailedException;

public class VoteProxy extends Proxy {

  public String vote(String username, int option) {
    JSONObject args = new JSONObject();
    args.put("username", username);

    String optionString;
    switch (option) {
      case 1:
        optionString = "java";
        break;
      case 2:
        optionString = "python";
        break;
      case 3:
        optionString = "c++";
        break;
      case 4:
        optionString = "javascript";
        break;
      default:
        optionString = "java";
        break;
    }
    args.put("option", optionString);

    String response;
    try {
      response = doOperation("Vote", "vote", args);
      return response;
    } catch (RequestFailedException e) {
      System.out.println(e.getMessage());
      return null;
    }

  }

  public String list() {

    String response;
    try {
      // Não passa argumentos
      response = doOperation("Vote", "list", new JSONObject());
      return response;
    } catch (RequestFailedException e) {
      System.out.println(e.getMessage());
      return null;
    }

  }

}
