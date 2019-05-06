
package com.ndifreke.developers.model;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.ndifreke.developers.util.GithubCacheHelper;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class GithubUser  {

    private volatile AtomicBoolean avatarRequestInFlight = new AtomicBoolean(false);
    public static final String USER = "com.ndifreke.developers.model.GithubUser";
    private String name, avatarURL, profileURL;
    private Bitmap avatar;
    private GithubUserListener githubListener;

    public GithubUser(Map<String, String> developerInformation) {

        try {
            name = developerInformation.get("name");
            avatarURL = developerInformation.get("avatar");
            profileURL = developerInformation.get("profileURL");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public GithubUser getDeveloper() {
        return this;
    }

    public String getName() {
        return this.name;
    }

    public String getProfileURL() {
        return this.profileURL;
    }

    public String getAvatarURL() {
        return this.avatarURL;
    }

    /**
     * Make a network request to fetch the this users image
     * if there is a request in progress to fetch an image,
     * this method will ignore subsequent call until the request is completed
     * before it can accept calls to fetch image
     */
    private void fetchImage() {
        if (avatar == null && !avatarRequestInFlight.get()) {
            new AsyncLoadImage().execute(this.getAvatarURL());
        }
    }

    /**
     * Loads the image resource of this developer from
     * the network in background if it does not exist as a cache
     *
     * @return Drawable
     */

    public Bitmap getImageResource() {
        /*look in cache, if image does not exist, load a new one
        setListener(viewHolder);
         * This is a stub
         * */
        fetchImage();
        return this.avatar;
    }

    /**
     * Adds a @GithubUserListener who subscibes to changes
     * on this Github user. The GithubListener will receive updates
     * from notifyUpdate() method defined by this class
     * notifyUpdate() is called on first time this method is called
     * and every subsequent time the Github user changes
     *
     * @param listener A class who implements @GithubUserListener
     */
    public void setListener(GithubUserListener listener) {
        this.githubListener = listener;
        notifyListenerOnUpdate();
    }

    private void notifyListenerOnUpdate() {
        if (this.githubListener != null)
            this.githubListener.notifyUpdate(this);
    }

    public class AsyncLoadImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected synchronized Bitmap doInBackground(String... avaUrls) {
            avatarRequestInFlight.set(true);
            return new GithubCacheHelper(GithubUser.this).requestAvatar();
        }

        @Override
        protected synchronized void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                avatar = bitmap;
                notifyListenerOnUpdate();
            }
            avatarRequestInFlight.set(false);
        }
    }
}