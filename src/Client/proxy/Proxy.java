package Client.proxy;

import java.net.SocketTimeoutException;

import org.json.JSONObject;

import Client.UDPClient;
import Client.exceptions.RequestFailedException;

public class Proxy {
  public UDPClient client = new UDPClient("localhost", 3001);

  public String doOperation(String serviceName, String operationName, JSONObject args) throws RequestFailedException {
    JSONObject request = new JSONObject();

    // get random uuid
    request.put("request_id", java.util.UUID.randomUUID().toString());
    request.put("service", serviceName);
    request.put("operation", operationName);
    request.put("args", args);

    try {
      client.send(request);
      // (5) Recebe resposta
      JSONObject response = client.receive();
      // (6) Deserializa resposta
      int status = response.getInt("status");
      String message = response.getString("message");
      // (7) Retorna resposta
      if (status == 200) {
        return message;
      } else {
        // Exception
        throw new RequestFailedException(status, message);
      }

    } catch (SocketTimeoutException s) {
      // Resend request
      return doOperation(serviceName, operationName, args);
    }
  }
}
