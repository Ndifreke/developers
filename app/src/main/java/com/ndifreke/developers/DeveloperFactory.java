package com.ndifreke.developers;

import org.json.*;

import java.util.Map;
import java.util.HashMap;

public class DeveloperFactory {

    private Map<String, Developer> developers = new HashMap<String, Developer>();

    private DeveloperFactory(String json) {
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
                Developer dev = new Developer(info);
                developers.put(dev.getName(), dev);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static DeveloperFactory createDevelopers(String json) {
        return new DeveloperFactory(json);
    }

    public Map<String, Developer> getDevelopers() {
        return this.developers;
    }

}

