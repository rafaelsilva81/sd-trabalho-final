package Server.dispatchers;

import java.lang.reflect.InvocationTargetException;

import org.json.JSONObject;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Dispatcher {
  // Lista de requisições já enviadas
  public static ArrayList<String> requests = new ArrayList<String>();

  public JSONObject invoke(JSONObject request) {
    JSONObject response = new JSONObject();

    String id = request.getString("request_id");
    // Checa se a requisição já foi enviada (TRATAMENTO DE REQUISIÇÕES REPETIDAS)
    if (requests.contains(id)) {
      response.put("request_id", id);
      response.put("status", 409);
      return response;
    } else {
      // Se a requisição não foi enviada, adiciona na lista
      requests.add(id);
    }

    // Outros parametros
    String serviceName = request.getString("service");
    String operationName = request.getString("operation");
    JSONObject args = request.getJSONObject("args");

    // Pega o nome da classe do serviço
    String className = "Server.skeletons." + serviceName + "Skeleton";

    try {
      // Pega a classe do serviço
      Class<?> clazz = Class.forName(className);
      Object skeleton = clazz.newInstance();

      // Pega o método do serviço
      Method method = clazz.getMethod(operationName, JSONObject.class);

      // Invoca o método do serviço
      response = (JSONObject) method.invoke(skeleton, args);

      // Adiciona o id da requisição no response
      response.put("request_id", id);

    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException
        | SecurityException | IllegalArgumentException | InvocationTargetException e) {
      e.printStackTrace();
    }

    return response;

  }
}
