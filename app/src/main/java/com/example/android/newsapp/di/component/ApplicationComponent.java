package com.example.android.newsapp.di.component;

import android.content.Context;

import com.example.android.newsapp.di.module.ApplicationModule;

import dagger.Component;

/**
 * Created by kevinsun on 11/13/17.
 */

@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context getApplicationContext();

}
