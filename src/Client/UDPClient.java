package Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import org.json.JSONObject;

public class UDPClient {
  private DatagramSocket socket;
  private int port;
  private String host;

  public UDPClient(String host, int port) {
    this.host = host;
    this.port = port;
    try {
      socket = new DatagramSocket();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  // Send request
  public void send(JSONObject request) {
    try {
      byte[] buffer = request.toString().getBytes();
      DatagramPacket packet = new DatagramPacket(buffer, buffer.length, java.net.InetAddress.getByName(host), port);
      socket.send(packet);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Get response
  public JSONObject receive() throws SocketTimeoutException {
    try {
      byte[] buffer = new byte[2048];
      DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

      try {
        this.socket.setSoTimeout(1000);
        this.socket.receive(packet);
      } catch (SocketException e) {
        e.printStackTrace();
      } catch (SocketTimeoutException e) {
        throw new SocketTimeoutException(); // Isso será tratado no proxy
      }

      return new JSONObject(new String(packet.getData()));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  // Close
  public void close() {
    socket.close();
  }

}
