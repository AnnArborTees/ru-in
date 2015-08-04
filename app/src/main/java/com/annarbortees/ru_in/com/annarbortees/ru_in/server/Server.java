package com.annarbortees.ru_in.com.annarbortees.ru_in.server;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.util.Date;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by nigel on 7/13/15.
 */
public class Server {
    public static final String ENDPOINT = "http://10.0.2.2:2998";

    public RestAdapter restAdapter;
    public Gson gson;
    public User.Service users;

    public Server() {
        gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(Date.class, new DateTypeAdapter())
            .create();

        restAdapter = new RestAdapter.Builder()
            .setEndpoint(ENDPOINT)
            .setConverter(new GsonConverter(gson))
            .build();

        users = restAdapter.create(User.Service.class);
    }
}
