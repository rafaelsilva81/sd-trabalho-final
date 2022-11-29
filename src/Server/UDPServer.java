package Server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import org.json.JSONObject;

import Server.dispatchers.Dispatcher;

public class UDPServer {
  // Simple UDP Socket Server

  public static void main(String[] args) {
    Dispatcher dispatcher = new Dispatcher();
    try {
      DatagramSocket server = new DatagramSocket(3001);
      System.out.println("Servidor iniciado na porta 3001");

      while (true) {
        byte[] buffer = new byte[2048];
        DatagramPacket client = new DatagramPacket(buffer, buffer.length);
        server.receive(client);

        // Create a new thread to handle the request
        new Thread() {
          public void run() {
            try {
              String message = new String(client.getData());
              System.out.println("Recebido: " + message);

              JSONObject request = new JSONObject(message);

              // Dispatch the request
              String response = dispatcher.invoke(request).toString();
              System.out.println("Enviado: " + response);

              // Send the response
              DatagramPacket reply = new DatagramPacket(response.getBytes(), response.getBytes().length,
                  client.getAddress(), client.getPort());
              server.send(reply);

            } catch (Exception e) {
              System.out.println("Erro: " + e.getMessage());
            }
          }
        }.start();

      }

    } catch (Exception e) {
      System.out.println("Erro: " + e.getMessage());
    }
  }

}
