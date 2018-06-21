package com.padcmyanmar.sfc.mvp.views;

import com.padcmyanmar.sfc.data.vo.NewsVO;

/**
 * Created by Acer on 6/17/2018.
 */

public interface NewsDetailsView extends BaseView {
    void displayNewsDetails(NewsVO news);
}
