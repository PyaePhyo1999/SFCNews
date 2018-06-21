package com.padcmyanmar.sfc.mvp.views;

import com.padcmyanmar.sfc.data.vo.NewsVO;

import java.util.List;

/**
 * Created by Acer on 6/17/2018.
 */

public interface NewsListsView extends BaseView{

    void displayNewsList(List<NewsVO> news);

    void launchDetailScreen(String newsId);
}
