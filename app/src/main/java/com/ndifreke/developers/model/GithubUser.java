
package com.ndifreke.developers.model;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.ndifreke.developers.R;
import com.ndifreke.developers.adapter.viewholder.DeveloperViewHolder;

import java.util.Map;

public class GithubUser implements Parcelable {

    private String name = "developerName", avatar = "image URL", profileURL;

    public GithubUser(Map<String, String> developerInformation) {
        try {
            name = developerInformation.get("name");
            avatar = developerInformation.get("avatar");
            profileURL = developerInformation.get("profileURL");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    protected GithubUser(Parcel in) {
        name = in.readString();
        avatar = in.readString();
        profileURL = in.readString();
    }

    public static final Creator<GithubUser> CREATOR = new Creator<GithubUser>() {
        @Override
        public GithubUser createFromParcel(Parcel in) {
            return new GithubUser(in);
        }

        @Override
        public GithubUser[] newArray(int size) {
            return new GithubUser[size];
        }
    };

    public String getName() {
        return this.name;
    }

    public String getProfileURL(){
        return this.profileURL;
    }

    public String getAvatarURL() {
        return this.avatar;
    }

    /**
     * Loads the image resource of this developer from
     * the network in background if it does not exist as a cache
     *
     * @return Drawable
     */

    public int getImageResource() {
        /*look in cache, if image does not exist, load a new one
        update(viewHolder);
         * This is a stub
         * */
        return R.drawable.ic_small_profile;
    }

    public void update(DeveloperViewHolder viewHolder){
        viewHolder.setDeveloper(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.getName());
        dest.writeString(this.getAvatarURL());
        dest.writeString(this.getProfileURL());
    }
}