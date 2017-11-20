package com.example.android.newsapp.mvp.ui.activity.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

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

    private DrawerLayout drawerLayout;

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

        // need to check if the activity has navigation view
        initDrawerLayout();

    }

    private void initActivityComponenet(){
        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((App)getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();

    }

    private void initToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_abpout, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.action_about:
                // do something here
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initDrawerLayout(){

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(navigationView != null){
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.nav_top:

                            break;
                        case R.id.nav_lastest:

                            break;

                        case R.id.nav_popular:

                            break;
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return  false;
                }
            });
        }











    }










}
