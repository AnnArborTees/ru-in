package com.annarbortees.ru_in;

import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
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


    @Test
    public void testActivityShouldHaveRegisterButton() throws InterruptedException {
        onView(withId(R.id.email_sign_in_button)).check(matches(withText("Sign in or register")));
    }
}
