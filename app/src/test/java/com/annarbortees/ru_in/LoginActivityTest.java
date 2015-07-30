package com.annarbortees.ru_in;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import retrofit.RestAdapter;
import retrofit.http.GET;

import static org.junit.Assert.*;

/**
 * Created by nigel on 7/2/15.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
public class LoginActivityTest {
    private ActivityController<LoginActivity> controller = Robolectric.buildActivity(LoginActivity.class);
    private LoginActivity activity;

    @Before
    public void grabActivity() {
        activity = controller.attach().create().get();
    }

    @Test
    public void trueShouldBeTrue() {
        assertTrue(true);
    }

    @Test
    public void registerShouldSendARequestToTheServer() {
        // TODO
        Robolectric.addPendingHttpResponse(200, "{ \"email\": \"test@test.com\" }");
    }
}
