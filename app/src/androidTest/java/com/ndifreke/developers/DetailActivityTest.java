package com.ndifreke.developers;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static org.hamcrest.core.IsNot.*;
import static org.mockito.Mockito.*;
import static android.support.test.espresso.Espresso.*;

import com.ndifreke.developers.activities.DetailActivity;
import com.ndifreke.developers.features.githubusers.GithubUser;
import com.ndifreke.developers.features.githubusers.GithubUserFragment;

import org.junit.After;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    DetailActivity detailActivity;
    @Rule
    public ActivityTestRule<DetailActivity> detailActivityRule =
            new ActivityTestRule<>(DetailActivity.class);

    @Before
    public void doBefore() {
        detailActivity = detailActivityRule.getActivity();
    }

    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.ndifreke.developers", appContext.getPackageName());
    }

    @Test
    public void toggleToolBarOnProfileImageClick() {
        ViewInteraction toolBar = onView(withId(R.id.profileToolbar));
        toolBar.check(matches(isDisplayed()));
        onView(withId(R.id.detailImageView)).perform(click());
        ViewInteraction check = toolBar.check(matches(not(isDisplayed())));
        onView(withId(R.id.detailImageView)).perform(click());
        toolBar.check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void profileImageExist() {
        onView(withId(R.id.detailImageView))
                .check(matches(ViewMatchers.hasBackground(R.drawable.ic_profile)));
    }

    public void shareButtonIsClickable() {

    }

    public ViewInteraction clickDialog(GithubUser user){
        detailActivityRule.getActivity().setGithubUser(user);
        return onView(withId(R.id.shareButton)).perform(click());
    }

    @Test
    public void showShareDialogToggle() {
        GithubUser mockUser = mock(GithubUser.class);
        when(mockUser.getUsername()).thenReturn("John");
        clickDialog(mockUser).check(matches(isDisplayed()));
        verify(mockUser, atLeastOnce()).getUsername();
    }

    @Test
    public void displayGithubDataOnShareDialog(){
        final String username = "checkName";
        final String profileUrl = "https://checkname.com";
        final String name = "Torry";
        GithubUser mockUser = mock(GithubUser.class);
        when(mockUser.getUsername()).thenReturn(username);
        when(mockUser.getProfileURL()).thenReturn(profileUrl);
        clickDialog(mockUser);

        onView(withId(R.id.share_github_name))
                .check(matches(withText("@"+username)));
        onView(withId(R.id.share_github_url))
                .check(matches(withText(profileUrl)));
    }

    private void mockGithubFragmentUpdate(GithubUserFragment mockFragment, GithubUser user, Map<String, String> data){
        when(mockFragment.getCompany()).thenReturn(data.get("company"));
        when(mockFragment.getName()).thenReturn(data.get("name"));
        user.receiveFragmentUpdate(mockFragment);
    }

    @Test
    public void updateOrganization(){
        Map<String, String> data = new HashMap<>();
        data.put("name", "Ndifreke");
        data.put("company", "Andela");
        GithubUser user = new GithubUser();
        GithubUserFragment mockFragment = mock(GithubUserFragment.class);
        mockGithubFragmentUpdate(mockFragment, user, data);
        detailActivityRule.getActivity().setViewContent(user);
        onView(withId(R.id.githubOrganization))
                .check(matches(withText(data.get("company"))));
        verify(mockFragment, atLeastOnce()).getCompany();
    }

    @After
    public void tearDown(){
        detailActivity = null;
    }
}
