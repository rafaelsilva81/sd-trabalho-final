package Server.dispatchers;

import java.lang.reflect.InvocationTargetException;

import org.json.JSONObject;
import java.lang.reflect.Method;

public class Dispatcher {
  public JSONObject invoke(JSONObject request) {
    JSONObject response = new JSONObject();

    String id = request.getString("request_id");
    String serviceName = request.getString("service");
    String operationName = request.getString("operation");
    JSONObject args = request.getJSONObject("args");

    // Get skeleton by className
    String className = "Server.skeletons." + serviceName + "Skeleton";

    try {
      Class<?> clazz = Class.forName(className);
      Object skeleton = clazz.newInstance();

      // Get method by operationName
      Method method = clazz.getMethod(operationName, JSONObject.class);

      // Invoke method
      response = (JSONObject) method.invoke(skeleton, args);

      // Add request
      response.put("request_id", id);

    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException
        | SecurityException | IllegalArgumentException | InvocationTargetException e) {
      e.printStackTrace();
    }

    return response;

  }
}
