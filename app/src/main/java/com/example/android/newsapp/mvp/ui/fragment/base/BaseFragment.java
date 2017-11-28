package com.example.android.newsapp.mvp.ui.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.newsapp.App;
import com.example.android.newsapp.di.component.DaggerActivityComponent;
import com.example.android.newsapp.di.component.DaggerFragmentComponent;
import com.example.android.newsapp.di.component.FragmentComponent;
import com.example.android.newsapp.di.module.FragmentModule;
import com.example.android.newsapp.mvp.presenter.base.BasePresenter;

import butterknife.ButterKnife;
import rx.Subscription;

/**
 * Created by kevinsun on 11/14/17.
 */

public abstract class BaseFragment <T extends BasePresenter> extends Fragment{

    protected FragmentComponent fragmentComponent;

    protected T mPresenter;

    protected View fragmentView;

    protected Subscription mSubscription;


    public abstract void initInjector();

    public abstract void initViews(View view);

    public abstract int getLayoutID();

    public FragmentComponent getFragmentComponent() {
        return fragmentComponent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentComponent = DaggerFragmentComponent.builder()
                .applicationComponent(((App) getActivity().getApplication()).getApplicationComponent())
                .fragmentModule(new FragmentModule(this))
                .build();

        initInjector();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(fragmentView == null){
            fragmentView = inflater.inflate(getLayoutID(), container, false);
            ButterKnife.bind(this, fragmentView);
            initViews(fragmentView);
        }
        return fragmentView;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mPresenter != null){
            mPresenter.onDestory();
        }

        if(mSubscription != null && mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
    }

}
