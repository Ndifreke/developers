package com.ndifreke.developers.api;


import android.os.AsyncTask;

import com.ndifreke.developers.util.GithubUserFactory;
import com.ndifreke.developers.adapter.GithubUserAdapter;
import com.ndifreke.developers.model.GithubUser;
import com.ndifreke.developers.view.MainActivity;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class GithubAPIs extends AsyncTask<Void, Void, Map<String, GithubUser>> {

    private static final String API_URL
            = "https://api.github.com/search/users?q=type:User+location:Nigeria+language:javascript";

    private static WeakReference<MainActivity> weakMainActivity;

    public GithubAPIs(MainActivity m) {
        weakMainActivity = new WeakReference<>(m);
    }

    private String json = null;

    public String getJSON(String api) {
        String result = null;
        BufferedReader reader;
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(api).openConnection();
            urlConnection.connect();
            try {
                reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                while ((result = reader.readLine()) != null) {
                    sb.append(result);
                }
                reader.close();
                result = sb.toString();
            } catch (FileNotFoundException e) {
                urlConnection.disconnect();
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected Map<String, GithubUser> doInBackground(Void[] v) {
        if(GithubUserFactory.getUsers().size() < 1) {
            String jsonResult = this.getJSON(API_URL);
            GithubUserFactory.createUsers(jsonResult);
        }
        return GithubUserFactory.getUsers();
    }

    @Override
    protected void onPostExecute(Map<String, GithubUser> users){
        GithubUserAdapter adapter = new GithubUserAdapter();
        weakMainActivity.get().initRecycler(adapter);
        adapter.setDataSet(users);
    }

}
