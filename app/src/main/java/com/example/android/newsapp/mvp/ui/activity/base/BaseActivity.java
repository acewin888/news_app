package com.example.android.newsapp.mvp.ui.activity.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;

import com.example.android.newsapp.App;
import com.example.android.newsapp.R;
import com.example.android.newsapp.annotation.BindValue;
import com.example.android.newsapp.di.component.ActivityComponent;
import com.example.android.newsapp.di.component.DaggerActivityComponent;
import com.example.android.newsapp.di.module.ActivityModule;
import com.example.android.newsapp.mvp.presenter.base.BasePresenter;
import com.example.android.newsapp.mvp.ui.activity.AboutActivity;
import com.example.android.newsapp.mvp.ui.activity.MainActivity;
import com.example.android.newsapp.util.MyUtil;
import com.example.android.newsapp.util.NetUtil;

import butterknife.ButterKnife;
import rx.Subscription;

/**
 * Created by kevinsun on 11/13/17.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {


    protected T mPresenter;

    protected ActivityComponent activityComponent;

    protected boolean haveNavigationView;

    private DrawerLayout drawerLayout;

    protected Subscription mSubscription;

    protected NavigationView mBaseNavView;

    private WindowManager mWindowManager = null;

    private View mNightView = null;

    private boolean mIsAddedView;

    private boolean mIsChangeTheme;

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    public abstract int getLayoutId();

    public abstract void initInjector();

    public abstract void initViews();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initAnnotaiton();
        NetUtil.isNetWorkErrShowMsg();

        setNightOrDayMode();

        int layoutId = getLayoutId();
        setContentView(layoutId);

        initActivityComponenet();

        initInjector();

        ButterKnife.bind(this);

        initToolBar();

        initViews();

        // need to check if the activity has navigation view
        if (haveNavigationView) {
            initDrawerLayout();
        }

        initNightModeSwitch();
    }

    private void initAnnotaiton() {
        if (getClass().isAnnotationPresent(BindValue.class)) {
            BindValue annotation = getClass().getAnnotation(BindValue.class);
            haveNavigationView = true;
        }
    }

    private void initActivityComponenet() {
        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((App) getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();

    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    private void setNightOrDayMode(){
        if(MyUtil.isNightMode()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            initNightView();
            mNightView.setBackgroundResource(R.color.night_mask);
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void initNightView(){
        if(mIsAddedView){
            return;
        }
        WindowManager.LayoutParams nightViewParam = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSPARENT);
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mNightView = new View(this);
        mWindowManager.addView(mNightView, nightViewParam);
        mIsAddedView = true;
    }

    private void initNightModeSwitch(){
        if(this instanceof MainActivity){
            MenuItem menuItem = mBaseNavView.getMenu().findItem(R.id.nav_night_mode);
            SwitchCompat dayNightSwitch = (SwitchCompat) MenuItemCompat
                    .getActionView(menuItem);
            setCheckedState(dayNightSwitch);
            setCheckedEvent(dayNightSwitch);
        }
    }

    private void setCheckedState(SwitchCompat switchCompat){
        if(MyUtil.isNightMode()){
            switchCompat.setChecked(true);
        }else {
            switchCompat.setChecked(false);
        }
    }

    private void setCheckedEvent(SwitchCompat switchCompat){
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    changeToNight();
                    MyUtil.saveTheme(true);
                }else {
                    changeToDay();
                    MyUtil.saveTheme(false);
                }

                mIsChangeTheme = true;
                drawerLayout.closeDrawer(GravityCompat.START);

            }
        });
    }

    private void changeToDay(){
        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        mNightView.setBackgroundResource(android.R.color.transparent);
    }

    private void changeToNight(){
        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        initNightView();
        mNightView.setBackgroundResource(R.color.night_mask);
    }

    @Override
    public void onBackPressed() {

        if (haveNavigationView && drawerLayout.isDrawerOpen(GravityCompat.START)) {
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
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_about:
                if (haveNavigationView) {
                    Intent intent = new Intent(this, AboutActivity.class);
                    startActivity(intent);
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initDrawerLayout() {

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_top:

                            break;
                        case R.id.nav_lastest:

                            break;

                        case R.id.nav_popular:

                            break;
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return false;
                }
            });
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mSubscription != null && mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }

        removeNightModeMask();

    }

    private void removeNightModeMask(){
        if(mIsAddedView){
            mWindowManager.removeView(mNightView);
            mWindowManager = null;
            mNightView = null;
        }
    }
}
