package com.example.android.newsapp;

import android.app.Application;
import android.content.Context;

import com.example.android.newsapp.di.component.ApplicationComponent;
import com.example.android.newsapp.di.component.DaggerApplicationComponent;
import com.example.android.newsapp.di.module.ApplicationModule;


/**
 * Created by kevinsun on 11/13/17.
 */

public class App extends Application {

    private static Context context;

    private ApplicationComponent applicationComponent;

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        initApplicationComponent();
    }

    public static Context getAppContext(){

        return context;
    }


    private void initApplicationComponent(){

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }
}
