package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import org.json.JSONObject;

import Server.dispatchers.Dispatcher;

public class UDPServer {
  public static void main(String[] args) {
    Dispatcher dispatcher = new Dispatcher();
    try {
      DatagramSocket server = new DatagramSocket(3001);
      System.out.println("Servidor iniciado na porta 3001");

      while (true) {
        byte[] buffer = new byte[2048];
        DatagramPacket client = new DatagramPacket(buffer, buffer.length);
        server.receive(client);

        // Cria novo thread para tratar o pedido
        new Thread() {
          public void run() {

            String message = new String(client.getData());
            System.out.println("Recebido: " + message);

            JSONObject request = new JSONObject(message);

            // Despacha o request
            String response = dispatcher.invoke(request).toString();
            System.out.println("Enviado: " + response);

            // Envia a response
            DatagramPacket reply = new DatagramPacket(response.getBytes(), response.getBytes().length,
                client.getAddress(), client.getPort());
            try {
              server.send(reply);
            } catch (IOException e) {
              e.printStackTrace();
            }

          }
        }.start();

      }

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
