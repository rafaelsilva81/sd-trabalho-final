package Client.exceptions;

public class RequestFailedException extends Exception {
  public RequestFailedException(Integer status, String message) {
    super("ERRO " + status + " - " + message);
  }
}
