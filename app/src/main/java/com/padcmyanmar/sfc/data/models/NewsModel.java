package com.padcmyanmar.sfc.data.models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.padcmyanmar.sfc.SFCNewsApp;
import com.padcmyanmar.sfc.data.db.ActedUserDao;
import com.padcmyanmar.sfc.data.db.AppDatabase;
import com.padcmyanmar.sfc.data.db.PublicationDao;
import com.padcmyanmar.sfc.data.vo.ActedUserVO;
import com.padcmyanmar.sfc.data.vo.CommentActionVO;
import com.padcmyanmar.sfc.data.vo.FavoriteActionVO;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.data.vo.PublicationVO;
import com.padcmyanmar.sfc.data.vo.SentToVO;
import com.padcmyanmar.sfc.events.RestApiEvents;
import com.padcmyanmar.sfc.network.MMNewsAPI;
import com.padcmyanmar.sfc.network.MMNewsDataAgent;
import com.padcmyanmar.sfc.network.MMNewsDataAgentImpl;
import com.padcmyanmar.sfc.network.reponses.GetNewsResponse;
import com.padcmyanmar.sfc.utils.AppConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import kotlin.Function;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aung on 12/3/17.
 */

public class NewsModel {
    private static NewsModel objInstance;
    private int mmNewsPageIndex = 1;
    private Map<String, NewsVO> mNewsMap;


    private NewsModel() {
        mNewsMap = new HashMap<>();
        EventBus.getDefault().register(this);

    }


    public static NewsModel getInstance() {
        if (objInstance == null) {
            objInstance = new NewsModel();
        }
        return objInstance;
    }

    public NewsVO geNewsById(String newsId) {
        return mNewsMap.get(newsId);
    }

    public void startLoadingMMNews() {
        MMNewsDataAgentImpl.getInstance().loadMMNews(AppConstants.ACCESS_TOKEN, mmNewsPageIndex);

    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onNewsDataLoaded(RestApiEvents.NewsDataLoadedEvent event) {
            for (NewsVO news : event.getLoadNews()){
                mNewsMap.put(news.getNewsId(),news);
            }
            mmNewsPageIndex = event.getLoadedPageIndex()+1;
    }
}


