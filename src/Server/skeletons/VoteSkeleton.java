package Server.skeletons;

import org.json.JSONObject;

import Server.services.VoteService;

public class VoteSkeleton {

  VoteService service;

  public VoteSkeleton() {
    service = new VoteService();
  }

  public JSONObject vote(JSONObject args) {
    String username = args.getString("username");
    String option = args.getString("option");
    return service.vote(username, option);
  }

  public JSONObject list(JSONObject args) {
    return service.list();
  }
}
