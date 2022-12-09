package Client;

import java.util.Scanner;

import Client.proxy.AuthProxy;
import Client.proxy.VoteProxy;

public class Client {

  /*
   * Essa classe vai, exclusivamente, gerenciar a interação com o usuário.
   */
  public static void main(String[] args) {
    // Instanciando classes e definindo variáveis
    AuthProxy authProxy = new AuthProxy();
    VoteProxy voteProxy = new VoteProxy();
    boolean isLoggedIn = false;
    boolean isRunning = true;
    Scanner reader = new Scanner(System.in);
    String username = "";
    String password = "";

    // Loop principal
    while (isRunning) {
      isLoggedIn = false;

      // Menu de opções
      System.out.println("\n \n---- Sistema Votação ----");
      System.out.println("1. Votar");
      System.out.println("2. Listar votos");
      System.out.println("3. Sair \n");
      System.out.print("Opção: ");

      int option = reader.nextInt();

      // Opção 1 - Votar (requer login)
      if (option == 1) {

        // Checa se status de login
        while (!isLoggedIn) {
          System.out.println("\n \nPor favor, faça login para continuar.");
          System.out.println("Username:");
          username = reader.next();
          System.out.println("Password:");
          password = reader.next();

          String auth = authProxy.login(username, password);
          if (auth != null) {
            isLoggedIn = true;
            System.out.println("Login realizado com sucesso!");
          } else {
            System.out.println("Login falhou.");
          }
        }

        // Menu de votação
        System.out.println("\n \nQual linguagem você prefere?");
        System.out.println("1. Java");
        System.out.println("2. Python");
        System.out.println("3. C++");
        System.out.println("4. Javascript");
        int voteOption = reader.nextInt();
        String vote = voteProxy.vote(username, voteOption);
        if (vote != null) {
          System.out.println("Voto realizado com sucesso!");
        } else {
          System.out.println("Voto falhou.");
        }

      }
      // Opção 2 - Listar votos (não requer login)
      else if (option == 2) {
        String list = voteProxy.list();
        if (list != null) {
          System.out.println("Contagem de votos:");
          System.out.println(list);
        } else {
          System.out.println("Listagem falhou.");
        }
      }
      // Opção 3 - Sair
      else if (option == 3) {
        isRunning = false;
        reader.close();
      } else {
        System.out.println("Opção inválida.");
      }
    }
  }
}
