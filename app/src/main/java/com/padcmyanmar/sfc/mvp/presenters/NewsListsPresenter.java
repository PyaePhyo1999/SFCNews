package com.padcmyanmar.sfc.mvp.presenters;

import android.content.Context;
import android.util.Log;

import com.padcmyanmar.sfc.SFCNewsApp;
import com.padcmyanmar.sfc.data.models.NewsModel;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.delegates.NewsItemDelegate;
import com.padcmyanmar.sfc.events.RestApiEvents;
import com.padcmyanmar.sfc.mvp.views.NewsListsView;
import com.padcmyanmar.sfc.network.MMNewsAPI;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Acer on 6/17/2018.
 */

public class NewsListsPresenter extends BasePresenter<NewsListsView> implements NewsItemDelegate  {


    public NewsListsPresenter(NewsListsView views){
        super(views);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        NewsModel.getInstance().startLoadingMMNews();

    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewsDataLoaded(RestApiEvents.NewsDataLoadedEvent event){
        if (event.getLoadNews() == null){
            mView.displayErrorMsg("Empty Data");
        }
        else {
            Log.d(SFCNewsApp.LOG_TAG,"loadedData"+event.getLoadNews().size());
            mView.displayNewsList(event.getLoadNews());
        }


    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorInvokingApi(RestApiEvents.ErrorInvokingAPIEvent event){
        mView.displayErrorMsg(event.getErrorMsg());
    }


    @Override
    public void onTapComment() {

    }

    @Override
    public void onTapSendTo() {

    }

    @Override
    public void onTapFavorite() {

    }

    @Override
    public void onTapStatistics() {

    }

    @Override
    public void onTapNews(NewsVO news) {
           mView.launchDetailScreen(news.getNewsId());
    }
}
