package com.ndifreke.developers;


import android.support.v7.widget.RecyclerView;

import com.ndifreke.developers.adapter.viewholder.DeveloperViewHolder;

import java.util.Map;

public class Developer {

    private String name = "developerName", avatar = "image URL";

    public Developer(Map<String, String> developerInformation) {
        try {
            name = developerInformation.get("name");
            avatar = developerInformation.get("avatar");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return this.name;
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
}
