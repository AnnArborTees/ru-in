package com.annarbortees.ru_in;

import android.content.SharedPreferences;

import com.annarbortees.ru_in.RuInApplication;
import com.annarbortees.ru_in.com.annarbortees.ru_in.server.Server;

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
    @Before
    public void setRestAdapter() {
        RuInApplication app = (RuInApplication)activity.getApplication();
        Executor exec = Executors.newSingleThreadExecutor();
        app.server.restAdapter = new RestAdapter.Builder()
            .setClient(new UrlConnectionClient())
            .setExecutors(exec, exec)
            .setEndpoint(Server.ENDPOINT)
            .setConverter(new GsonConverter(app.server.gson))
            .build();
    }

    @Test
    public void trueShouldBeTrue() {
        assertTrue(true);
    }

    @Test
    public void registerShouldAddTokenAndEmailToSharedPreferencesOnSuccess() {
        Robolectric.addPendingHttpResponse(
            200, "{ \"email\": \"test@test.com\", \"authenticationToken\": \"abc123\" }"
        );
        activity.register("test@test.com", "inComingNewUs3r", "inComingNewUs3r");
        SharedPreferences prefs = activity.getSharedPreferences(RuInApplication.PREFERENCES_NAME, 0);
        assertEquals(prefs.getString("email", "NULL"), "test@test.com");
        assertEquals(prefs.getString("authenticationToken", "NULL"), "abc123");
    }
}
