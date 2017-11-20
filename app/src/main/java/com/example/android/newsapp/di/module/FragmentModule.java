package com.example.android.newsapp.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kevinsun on 11/14/17.
 */

@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment){
        this.fragment = fragment;
    }

    @Provides
    public Fragment provideFragment(){
        return  fragment;
    }

    @Provides
    public Activity provideFragmentActivity(){
        return  fragment.getActivity();
    }
}
