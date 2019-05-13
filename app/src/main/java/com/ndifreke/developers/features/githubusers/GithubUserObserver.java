package com.ndifreke.developers.features.githubusers;

/**
 * GithubUserObserver provides an Interface for
 * implementation to attach callback on a GithubUser
 * through  GithubUser.setListener(GitHubUserListerner)
 * when a behaviour of a GithuUser is changed, the GithubUserObserver
 * will be notified through notifyObserver
 */
public interface GithubUserObserver <T> {

    /**
     * Notify any subscriber who has subscribed to changes on a
     * Github user
     * @param user the @GithubUser that is updated
     */
    void notifyObserver(T change);

}
