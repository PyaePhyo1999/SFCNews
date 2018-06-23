package com.padcmyanmar.sfc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;

import com.padcmyanmar.sfc.R;
import com.padcmyanmar.sfc.adapters.HotSwapNewsAdapter;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.mvp.presenters.NewsListsPresenter;
import com.padcmyanmar.sfc.mvp.views.NewsListsView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Acer on 6/22/2018.
 */

public class HotSwapNewsActivity extends BaseActivity implements NewsListsView{

    @BindView(R.id.rv_hot_swap_news)
    RecyclerView rvHotSwapNews;
    private HotSwapNewsAdapter mHotSwapNewsAdapter;
    private NewsListsPresenter mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_swap_new_list);
        ButterKnife.bind(this,this);
        mPresenter = new NewsListsPresenter(this);
        mPresenter.onCreate();
        mHotSwapNewsAdapter = new HotSwapNewsAdapter(getApplicationContext(),mPresenter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        rvHotSwapNews.setLayoutManager(gridLayoutManager);
        rvHotSwapNews.setAdapter(mHotSwapNewsAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    public void displayErrorMsg(String errorMsg) {

    }

    @Override
    public void displayNewsList(List<NewsVO> news) {
             mHotSwapNewsAdapter.appendNewData(news);
    }

    @Override
    public void launchDetailScreen(String newsId) {
        Intent intent = NewsDetailsActivity.newIntent(getApplicationContext(),newsId);
        startActivity(intent);
    }
}
