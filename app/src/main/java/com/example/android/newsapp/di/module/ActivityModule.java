package com.example.android.newsapp.di.module;

import android.app.Activity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kevinsun on 11/13/17.
 */

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity){

        this.activity = activity;
    }

    @Provides
    public Activity provideActivity(){

        return  activity;
    }
}
