package Server.skeletons;

import org.json.JSONObject;

import Server.services.VoteService;

/* 
  * Classe que representa o esqueleto do serviço de votação
*/
public class VoteSkeleton {

  VoteService service;

  public VoteSkeleton() {
    service = new VoteService();
  }

  // Método remoto 01 - Adiciona um voto com as opções passadas
  public JSONObject vote(JSONObject args) {
    String username = args.getString("username");
    String option = args.getString("option");
    return service.vote(username, option);
  }

  // Método remoto 02 - Lista os votos
  public JSONObject list(JSONObject args) {
    return service.list();
  }
}
