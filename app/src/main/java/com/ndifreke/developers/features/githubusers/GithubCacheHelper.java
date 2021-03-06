package com.ndifreke.developers.features.githubusers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.ndifreke.developers.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import androidx.recyclerview.widget.RecyclerView.Adapter;

/**
 * Writes and Read a Github user data from the disk,
 */
public class GithubCacheHelper /* extends GlobalContext */{

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
            httpURLConnection = (HttpURLConnection) new URL(this.user.getImageURL())
                    .openConnection();
            bitmap = BitmapFactory.decodeStream(httpURLConnection.getInputStream());
            httpURLConnection.disconnect();
        } catch (MalformedURLException ignored) {
        } catch (IOException ignored) {
        }
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
