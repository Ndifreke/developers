package com.ndifreke.developers;

import android.Manifest;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.AndroidJUnitRunner;
import android.view.View;

import com.ndifreke.developers.activities.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Rule;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule
            = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void hideNetworkErrorMessage() {
        onView(withId(R.id.network_error_view)).check(
                matches(not(ViewMatchers.isDisplayed()))
        );
    }

    public boolean switchWifi(boolean enabled){
        WifiManager wifi = (WifiManager) mainActivityRule.getActivity().getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);
       return wifi.setWifiEnabled(enabled);
    }

    @Test
    public void netWorkErrorIsHiddenOnStart(){
        assertEquals(mainActivityRule.getActivity()
                        .findViewById(R.id.network_error_view).getVisibility(),
                View.GONE);
    };

    @Test
    public void showLoadingProgress() throws Exception {
            assertEquals(mainActivityRule.getActivity()
                            .findViewById(R.id.load_progress).getVisibility(),
                    View.VISIBLE);
    }

}