package com.ndifreke.developers;

import android.content.Context;
import android.net.wifi.WifiManager;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import static  org.hamcrest.Matchers.*;
import android.view.View;
import com.ndifreke.developers.activities.MainActivity;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Rule;
import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;

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

//    @Test
//    public void hideNetworkErrorMessage() {
//        onView(withId(R.id.network_error_view)).check(
//                matches(not(ViewMatchers.isDisplayed()))
//        );
//    }

    public boolean switchWifi(boolean enabled){
        WifiManager wifi = (WifiManager) mainActivityRule.getActivity().getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);
       return wifi.setWifiEnabled(enabled);
    }

//    @Test
//    public void netWorkErrorIsHiddenOnStart(){
//        assertEquals(mainActivityRule.getActivity()
//                        .findViewById(R.id.network_error_view).getVisibility(),
//                View.GONE);
//    };

    @Test
    public void showLoadingProgress() throws Exception {
            assertEquals(mainActivityRule.getActivity()
                            .findViewById(R.id.load_progress).getVisibility(),
                    View.VISIBLE);
    }

}