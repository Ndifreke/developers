package com.ndifreke.developers;

import com.ndifreke.developers.model.GithubUsers;

import org.json.*;

import java.util.Map;
import java.util.HashMap;

public class GithubUsersFactory {

    private Map<String, GithubUsers> developers = new HashMap<String, GithubUsers>();

    private GithubUsersFactory(String json) {
        try {
            JSONObject developer = new JSONObject(json);
            buildDevelopers(developer.getJSONArray("items"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void buildDevelopers(JSONArray data) {
        for (int i = 0; i < data.length(); i++) {
            try {
                final JSONObject developer = (JSONObject) data.get(i);
                HashMap<String, String> info = new HashMap<>();
                info.put("avatar", (String) developer.get("avatar_url"));
                info.put("name", (String) developer.get("login"));
                GithubUsers dev = new GithubUsers(info);
                developers.put(dev.getName(), dev);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static GithubUsersFactory createDevelopers(String json) {
        return new GithubUsersFactory(json);
    }

    public Map<String, GithubUsers> getDevelopers() {
        return this.developers;
    }
}

