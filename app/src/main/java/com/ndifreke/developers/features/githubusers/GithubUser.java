package com.ndifreke.developers.features.githubusers;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;
import com.ndifreke.developers.api.ApiExecutor;

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

    private List<GithubUserObserver> githubListeners = new ArrayList<>();
    private volatile AtomicBoolean avatarRequestInFlight = new AtomicBoolean(false);

    private Bitmap image = null;

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
            avatarRequestInFlight.set(true);
            ApiExecutor.execute(getGithubImageRunnable());
        }
    }

    /**
     * Adds a @GithubUserObserver who subscibes to changes
     * on this Github user. The GithubListener will receive updates
     * from notifyObserver() method defined by this class
     * notifyObserver() is called on first time this method is called
     * and every subsequent time the Github user changes
     *
     * @param listener A class who implements @GithubUserObserver
     */
    public void setListener(GithubUserObserver listener) {
        this.githubListeners.add(listener);
    }

    private void notifyListenerOnUpdate() {
        if (this.githubListeners != null)
            for(GithubUserObserver listener : githubListeners)
                listener.notifyObserver(this);
    }

    public Runnable getGithubImageRunnable(){
        return  new Runnable(){
            @Override
            public void run(){
                Bitmap bitmap =  new  GithubCacheHelper(GithubUser.this).requestAvatar();
                image = bitmap;
                avatarRequestInFlight.set(false);
                notifyListenerOnUpdate();
            }
        };
    }

}