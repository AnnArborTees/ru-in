package com.annarbortees.ru_in;

import android.app.Application;

import com.annarbortees.ru_in.com.annarbortees.ru_in.server.Server;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

/**
 * Created by nigel on 7/2/15.
 */
@ReportsCrashes(logcatArguments = {"-t", "250", "-v", "time"})
public class RuInApplication extends Application {
    public Server server;

    @Override
    public void onCreate() {
        ACRA.init(this);
        ACRA.getErrorReporter().setReportSender(new HockeySender());

        server = new Server();
    }
}
