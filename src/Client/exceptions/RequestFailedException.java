package Client.exceptions;

/* 
  * Essa classe representa uma exceção que é lançada quando uma requisição falha.
  * Ela é lançada pelo método doOperation da classe Proxy caso o status da resposta 
  * seja diferente de 200.
*/
public class RequestFailedException extends Exception {
  public RequestFailedException(Integer status, String message) {
    super("ERRO " + status + " - " + message);
  }
}
