package com.example.android.newsapp.di.module;

import android.content.Context;

import com.example.android.newsapp.App;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kevinsun on 11/13/17.
 */

@Module
public class ApplicationModule {

    private App application;

    public ApplicationModule(App app){
       application = app;
    }

    @Provides
    public Context provideApplicationContext(){

        return  application;
    }

}
