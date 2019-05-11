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

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    //DetailActivity detailActivity = mock(DetailActivity.class);
    @Before
    public void doBefore() {

    }

    @Rule
    public ActivityTestRule detailActivityRule =
            new ActivityTestRule<>(DetailActivity.class, true, true);

    @Test
    public void useAppContext() {
        // Context of the app under test.DJUE
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

//    @Test
//    public void profileImageExist() {
//        onView(withId(R.id.detailImageView))
//                .check(matches(ViewMatchers.hasBackground(R.id.detailImageView)));
//    }

    public void shareButtonIsClickable() {

    }

//    @Test
//    public void shareDialogToggle() {
//        onView(withId(R.id.shareButton)).perform(click());
//        onView(withId(R.id.shareDialog)).check(matches(isDisplayed()));
//    }
}
