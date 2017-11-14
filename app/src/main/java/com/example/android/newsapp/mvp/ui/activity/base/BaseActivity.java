package com.example.android.newsapp.mvp.ui.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.android.newsapp.App;
import com.example.android.newsapp.R;
import com.example.android.newsapp.di.component.ActivityComponent;
import com.example.android.newsapp.di.component.DaggerActivityComponent;
import com.example.android.newsapp.di.module.ActivityModule;
import com.example.android.newsapp.mvp.presenter.base.BasePresenter;

import butterknife.ButterKnife;

/**
 * Created by kevinsun on 11/13/17.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {



    protected T mPresenter;

    protected ActivityComponent activityComponent;

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    public abstract int getLayoutId();

    public abstract void initInjector();

    public abstract void initViews();




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        int layoutId = getLayoutId();
        setContentView(layoutId);

        initActivityComponenet();

        initInjector();

        ButterKnife.bind(this);

        initToolBar();

        initViews();

        if(mPresenter != null){
            mPresenter.onCreate();
        }
    }

    private void initActivityComponenet(){
        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((App)getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();

    }

    private void initToolBar(){

    }










}
