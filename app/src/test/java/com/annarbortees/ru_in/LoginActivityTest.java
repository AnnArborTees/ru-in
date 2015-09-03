package com.annarbortees.ru_in;

import android.content.SharedPreferences;

import com.annarbortees.ru_in.RuInApplication;
import com.annarbortees.ru_in.com.annarbortees.ru_in.server.Server;
import com.annarbortees.ru_in.com.annarbortees.ru_in.server.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit.RestAdapter;
import retrofit.client.UrlConnectionClient;
import retrofit.converter.GsonConverter;
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
    // TODO this is completely nonfunctional
    public void registerUserShouldAddTokenAndEmailToSharedPreferencesOnSuccess() {
        Robolectric.addPendingHttpResponse(
                200, "{ \"email\": \"test@test.com\", \"authenticationToken\": \"abc123\" }"
        );
        activity.registerUser("test@test.com", "inComingNewUs3r", "inComingNewUs3r");
        try { Thread.sleep(500); } catch(InterruptedException e) {  }
        assertTrue(Robolectric.httpRequestWasMade(Server.endpoint));
        SharedPreferences prefs = activity.getSharedPreferences(RuInApplication.PREFERENCES_NAME, 0);
        assertEquals("test@test.com", prefs.getString("email", "NULL"));
        assertEquals("abc123", prefs.getString("authenticationToken", "NULL"));
    }
}
