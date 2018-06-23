package com.padcmyanmar.sfc.mvp.presenters;

import android.content.Context;

import com.padcmyanmar.sfc.data.models.NewsModel;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.mvp.views.NewsDetailsView;

/**
 * Created by Acer on 6/17/2018.
 */

public class NewsDetailPresenter extends BasePresenter<NewsDetailsView> {
    private NewsDetailsView mView;

    public NewsDetailPresenter(NewsDetailsView view){
        super(view);
        mView = view;
    }



    public void onFinishUIComponent(String newsId){
        NewsVO news=NewsModel.getInstance().geNewsById(newsId);
        mView.displayNewsDetails(news);
    }

}
