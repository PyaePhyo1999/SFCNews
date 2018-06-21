package com.padcmyanmar.sfc.mvp.presenters;

import android.content.Context;

import com.padcmyanmar.sfc.mvp.views.BaseView;

/**
 * Created by Acer on 6/17/2018.
 */

public abstract class BasePresenter<T extends BaseView> {
    protected T mView;


    public void onCreate(Context context){}
    public void onStart(){}
    public void onStop(){}
    public void onResume(){}
    public void onPause(){}
    public void onDestroy(){}

    public BasePresenter(T mView) {
        this.mView = mView;
    }
}
