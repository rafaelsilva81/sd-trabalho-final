package Server.services;

import java.util.List;

import org.json.JSONObject;

import Server.util.CSVUtil;

/* 
  * Classe que representa o serviço de autenticação
*/
public class VoteService {
  /* OPTIMIZE: Usar um banco de dados real ao invés de um CSV */
  private CSVUtil csvUtil = new CSVUtil("src\\Server\\data\\votes.csv"); // Pega o CSV de votos

  public JSONObject vote(String username, String option) {
    JSONObject response = new JSONObject();
    // checa se o usuário já votou
    List<String[]> votes = csvUtil.read();
    for (String[] vote : votes) {
      if (vote[0].equals(username)) {
        response.put("status", 403);
        response.put("message", "Usuario ja votou!");
        return response;
      }
    }

    // adiciona o voto no CSV
    csvUtil.addLine(username, option);

    // monta a resposta
    response.put("status", 200);
    response.put("message", "Voto computado com sucesso!");
    return response;
  }

  public JSONObject list() {
    JSONObject response = new JSONObject();
    // Ler o CSV de votos
    List<String[]> votes = csvUtil.read();
    // Contar os votos
    int javaCount = 0;
    int pythonCount = 0;
    int cplusplusCount = 0;
    int javascriptCount = 0;
    for (String[] vote : votes) {
      switch (vote[1]) {
        case "java":
          javaCount++;
          break;
        case "python":
          pythonCount++;
          break;
        case "c++":
          cplusplusCount++;
          break;
        case "javascript":
          javascriptCount++;
          break;
      }
    }
    // Montar o JSON de resposta
    response.put("status", 200);

    JSONObject votesData = new JSONObject();
    votesData.put("java", javaCount);
    votesData.put("python", pythonCount);
    votesData.put("c++", cplusplusCount);
    votesData.put("javascript", javascriptCount);

    response.put("message", votesData.toString(2));
    return response;
  }

}
