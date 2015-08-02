package com.cetur.platinum;

import android.app.Application;

import com.cetur.model.Person;

/**
 * Created by Fatih on 30.7.2015.
 */
public class AppController extends Application {

    private static AppController mInstance;
    private static String accesToken;
    private static Person user;

    public static String getAccesToken() {
        return accesToken;
    }

    public static void setAccesToken(String accesToken) {
        AppController.accesToken = accesToken;
    }

    public static Person getUser() {
        return user;
    }

    public static void setUser(Person user) {
        AppController.user = user;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance  = this;
    }
    public static synchronized AppController getInstance(){
        return mInstance;
    }
}
