package com.annarbortees.ru_in;

import android.content.SharedPreferences;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.LargeTest;

import com.annarbortees.ru_in.com.annarbortees.ru_in.server.Server;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by nigel on 7/2/15.
 */
@LargeTest
public class LoginActivityEspressoTest {
    @Rule
    public ActivityTestRule<LoginActivity> activityRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void stubServer() throws IOException {
        MockWebServer server = new MockWebServer();
        server.enqueue(
            new MockResponse()
                .setBody("{ \"email\": \"test@test.com\", \"authentication_token\": \"abc123\" }")
                .setResponseCode(200)
        );
        server.play(2997);
        URL url = server.getUrl("/");
        Server.endpoint = url.toString();

        ((RuInApplication)activityRule.getActivity().getApplication()).server.reloadRestAdapter();
    }

    @Test
    public void testUserShouldBeAbleToRegister() throws InterruptedException {
        onView(withId(R.id.email)).perform(ViewActions.typeText("test@test.com"));
        onView(withId(R.id.password)).perform(ViewActions.typeText("thesupersecurepass"));
        onView(withId(R.id.email_register)).perform(ViewActions.click());
        onView(withId(R.id.password_confirmation)).perform(
                ViewActions.typeText("thesupersecurepass"),
                ViewActions.closeSoftKeyboard());
        Thread.sleep(2000);
        onView(withId(R.id.email_register)).perform(ViewActions.scrollTo(), ViewActions.click());
        Thread.sleep(2000);
        SharedPreferences prefs =
            activityRule.getActivity().getSharedPreferences(RuInApplication.PREFERENCES_NAME, 0);

        assertThat(prefs.getString("email", "EMPTY"), Matchers.equalTo("test@test.com"));
        assertThat(prefs.getString("authenticationToken", "EMPTY"), Matchers.equalTo("abc123"));
    }
}
