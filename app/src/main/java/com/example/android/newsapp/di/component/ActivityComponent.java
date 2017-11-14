package com.example.android.newsapp.di.component;

import android.app.Activity;

import com.example.android.newsapp.di.module.ActivityModule;
import com.example.android.newsapp.mvp.ui.activity.MainActivity;

import dagger.Component;

/**
 * Created by kevinsun on 11/13/17.
 */
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(MainActivity mainActivity);


}
