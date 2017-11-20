package com.example.android.newsapp.di.component;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.example.android.newsapp.di.module.FragmentModule;
import com.example.android.newsapp.mvp.ui.fragment.NewsListFragment;

import dagger.Component;

/**
 * Created by kevinsun on 11/14/17.
 */

@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {


    Fragment getFragment();


    Activity getActivity();

    void inject(NewsListFragment newsListFragment);

}
