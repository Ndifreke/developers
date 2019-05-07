package com.ndifreke.developers.model.githubusers;

/**
 * GithubUserListener provides an Interface for
 * implementation to attach callback on a GithubUser
 * through  GithubUser.setListener(GitHubUserListerner)
 * when a behaviour of a GithuUser is changed, the GithubUserListener
 * will be notified through notifyUpdate
 */
public interface GithubUserListener {

    /**
     * Notify any subscriber who has subscribed to changes on a
     * Github user
     * @param user the @GithubUser that is updated
     */
    void notifyUpdate(GithubUser user);

}
