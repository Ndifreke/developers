package com.ndifreke.developers.model.githubusers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.ndifreke.developers.GlobalContext;
import com.ndifreke.developers.R;
import com.ndifreke.developers.adapter.viewholder.DeveloperViewHolder;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;

/**
 * Writes and Read a Github user data from the disk,
 */
public class GithubCacheHelper /* extends GlobalContext */{

    public static Map<String, GithubUser> cachedGithubUsers  = new HashMap<>();
    public static Adapter cachedGithubUserAdapter = null;
    private GithubUser user;

    private HttpURLConnection httpURLConnection;

    public GithubCacheHelper(GithubUser user) {
        this.user = user;
    }

    protected void bindInstance(GithubUser user){
        this.user = user;
    }

    public GithubCacheHelper(){
    }

    /**
     * checks if the github user has cached avater on the users
     * devices
     *
     * @return true if the avater has changed since last request
     */
    private boolean hasCacheAvater() {
        return true;
    }

    /**
     * checks if the github user has uploaded a avater
     * since the last time his avater image was fetched
     *
     * @return true if the avater has changed since last request
     */

    private boolean hasConnected() {
        return httpURLConnection != null;
    }

    private synchronized boolean hasNewAvater() {
        if (hasConnected() || this.requestAvatar() != null)
            return httpURLConnection.getLastModified() > 0;
        return false;
    }

    public Bitmap requestAvatar() {
        Bitmap bitmap = null;
        try {
            Log.i("avaterURL", user.getImageURL()+" avater for " + user.getUsername());
            httpURLConnection = (HttpURLConnection) new URL(this.user.getImageURL())
                    .openConnection();
            bitmap = BitmapFactory.decodeStream(httpURLConnection.getInputStream());
        } catch (MalformedURLException ignored) {
        } catch (IOException ignored) {
        }
        httpURLConnection.disconnect();
        return bitmap;
    }

    public boolean saveAvatar() {
      //  File f = globalContext.getCacheDir();
        //Log.i("cachex", f.getAbsolutePath());
        return false;
    }

    public Bitmap loadAvater() {
        return BitmapFactory.decodeResource(null, R.drawable.ic_small_profile);
    }
}
