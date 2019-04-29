package com.ndifreke.developers;

import android.util.Log;

import com.ndifreke.developers.model.GithubUser;

import org.json.*;

import java.util.Map;
import java.util.HashMap;

public class GithubUsersFactory {

    private Map<String, GithubUser> developers = new HashMap<String, GithubUser>();

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
                final JSONObject json = (JSONObject) data.get(i);
                HashMap<String, String> info = new HashMap<>();
                info.put("avatar", (String) json.get("avatar_url"));
                info.put("name", (String) json.get("login"));
                info.put("profileURL", (String) json.get("url"));
                GithubUser developer = new GithubUser(info);
                developers.put(developer.getName(), developer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static GithubUsersFactory createDevelopers(String json) {
        return new GithubUsersFactory(json);
    }

    public Map<String, GithubUser> getDevelopers() {
        return this.developers;
    }
}

