package com.annarbortees.ru_in.com.annarbortees.ru_in.server;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by nigel on 7/13/15.
 */
public class User {
    public interface Service {
        @FormUrlEncoded
        @POST("/users")
        void register(
            @Field("user[email]") String email,
            @Field("user[password]") String pass,
            @Field("user[password_confirmation]") String pass_conf,
            Callback<User> cb
        );
    }

    public class Errors {
        public final String[] email;
        public final String[] firstName;
        public final String[] lastName;
        public final String[] password;
        public final String[] passwordConfirmation;

        public Errors(
                String[] email,
                String[] firstName,
                String[] lastName,
                String[] password,
                String[] passwordConfirmation
        ) {
            if (email == null)
                this.email = new String[0];
            else
                this.email = email;

            if (firstName == null)
                this.firstName = new String[0];
            else
                this.firstName = firstName;

            if (lastName == null)
                this.lastName = new String[0];
            else
                this.lastName = lastName;

            if (password == null)
                this.password = new String[0];
            else
                this.password  = password;

            if (passwordConfirmation == null)
                this.passwordConfirmation = new String[0];
            else
                this.passwordConfirmation = passwordConfirmation;
        }
    }

    public final String email;
    public final String firstName;
    public final String lastName;
    public final Errors errors;

    public User(String email, String firstName, String lastName, Errors errors) {
        this.email     = email;
        this.firstName = firstName;
        this.lastName  = lastName;
        this.errors    = errors;
    }
}
