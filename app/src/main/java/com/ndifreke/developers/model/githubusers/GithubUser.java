package com.ndifreke.developers.model.githubusers;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class GithubUser {

    @SerializedName("login")
    private String username;

    @SerializedName("avatar_url")
    private String imageURL;

    @SerializedName("company")
    private String company;

    @SerializedName("html_url")
    private String profileURL;

    @SerializedName("url")
    private String apiURL;

    private boolean hasUpdateFragment = false;

    private String name;

    public static final String ID = GithubUser.class.getName();

    private List<GithubUserListener> githubListeners = new ArrayList<>();
    private volatile AtomicBoolean avatarRequestInFlight = new AtomicBoolean(false);
    private Bitmap image;

    public GithubUser() {
        //call requestUpdate();here if you want
        //the users fragment to be updated immediately the user
        //is created
    }

    /**
     * A callback which GithubUserFragment can use to specify a
     * Fragment update for this GithubUser
     * @param fragment GithubUserFragment which contains update
     *  for this user.
     */
    public void receiveFragmentUpdate(GithubUserFragment fragment) {
        this.name = fragment.getName();
        this.company = fragment.getCompany();
        this.notifyListenerOnUpdate();
        hasUpdateFragment = (name != null && this.company != null);
    }

    public String getApiURL() {
        return apiURL;
    }

    public String getUsername() {
        return username;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getCompany() {
        return company;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public Bitmap getImageResource() {
        fetchImage();
        return image;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Request for the full details of this Github user
     * if this user has requested update before, this
     * request will be ignored
     */
    public void requestUpate() {
        if(!hasUpdateFragment) {
            new GithubUserFragment().registerGithubUser(this);
        }
    }

    /**
     * Make a network request to fetch the this users image
     * if there is a request in progress to fetch an image,
     * this method will ignore subsequent call until the request is completed
     * before it can accept calls to fetch image
     */
    private void fetchImage() {
        if (image == null && !avatarRequestInFlight.get()) {
            new AsyncLoadImage().execute(this.getImageURL());
        }
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
        this.githubListeners.add(listener);
        // notifyListenerOnUpdate();
    }

    private void notifyListenerOnUpdate() {
        if (this.githubListeners != null)
            for(GithubUserListener listener : githubListeners)
            listener.notifyUpdate(this);
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
                image = bitmap;
                notifyListenerOnUpdate();
            }
            avatarRequestInFlight.set(false);
        }
    }

}
