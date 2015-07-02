package com.annarbortees.ru_in;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;
import org.acra.ReportField;
import org.acra.collector.CrashReportData;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderException;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//Template copied from https://gist.github.com/antoche/5123357 w/ custom reporting fields
public class HockeySender implements ReportSender {
    private static final String TAG = HockeySender.class.getName();
    private static final String BASE_URL = "https://rink.hockeyapp.net/api/2/apps/";
    private static final String hockeyappAppIdToSend = "918fb660d6d070d2a21c93537d5c457e";
    private static final String CRASHES_PATH = "/crashes";

    public HockeySender() {
    }

    @Override
    public void send(Context context, CrashReportData report) throws ReportSenderException {
        String log = createCrashLog(report);
        String url = BASE_URL.concat(hockeyappAppIdToSend).concat(CRASHES_PATH);

        try {
            HttpPost httpPost = new HttpPost(url);

            List<NameValuePair> parameters = new ArrayList<>();
            parameters.add(new BasicNameValuePair("raw", log));
            parameters.add(new BasicNameValuePair("userID", getIMEI(context)));
            parameters.add(new BasicNameValuePair("contact", ""));
            parameters.add(new BasicNameValuePair("description", report.get(ReportField.LOGCAT)));

            httpPost.setEntity(new UrlEncodedFormEntity(parameters, HTTP.UTF_8));

            new DefaultHttpClient().execute(httpPost);
        }
        catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
            throw new ReportSenderException(e.getLocalizedMessage(), e);
        }
    }

    protected String getIMEI(Context context) {
        return ((TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
    }

    private String createCrashLog(CrashReportData report) {
        String log = "Package: " + report.get(ReportField.PACKAGE_NAME) + "\n";
        log = log.concat("Version: " + report.get(ReportField.APP_VERSION_NAME) + "\n");
        log = log.concat("OS: " + report.get(ReportField.ANDROID_VERSION) + "\n");
        log = log.concat("Manufacturer: " + report.get(ReportField.BRAND) + "\n");
        log = log.concat("Model: " + report.get(ReportField.PHONE_MODEL) + "\n");
        log = log.concat("Date: " + getHockeyAppDateString(report.get(ReportField.USER_CRASH_DATE)) + "\n");
        log = log.concat("\n");
        log = log.concat(report.get(ReportField.STACK_TRACE) + "\n");

        return log;
    }

    //From ACRA:    2015-06-29T09:57:25.000-05:00
    //To HockeyApp: Sun Nov 27 17:35:08 GMT+01:00 2011
    public String getHockeyAppDateString(String acraReportDateString) {
        String hockeyAppDateString;
        SimpleDateFormat fromACRADateString =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZ");
        SimpleDateFormat toHockeyAppDateString = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT'ZZZZ yyyy");
        try {
            Date date = fromACRADateString.parse(acraReportDateString);
            hockeyAppDateString = toHockeyAppDateString.format(date);
        } catch (ParseException e) {
            Log.e(TAG, "Failed to parse date: " + e.getLocalizedMessage(), e);
            hockeyAppDateString = acraReportDateString;
        }
        return hockeyAppDateString;
    }
}
