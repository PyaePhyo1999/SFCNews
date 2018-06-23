package com.padcmyanmar.sfc.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padcmyanmar.sfc.R;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.delegates.NewsItemDelegate;
import com.padcmyanmar.sfc.mvp.presenters.NewsListsPresenter;

import org.w3c.dom.Text;

import butterknife.BindView;

/**
 * Created by Acer on 6/22/2018.
 */

public class HotSwapNewsViewHolder extends BaseViewHolder<NewsVO> {
    @BindView(R.id.tv_news_founder)
    TextView tvNewsFounder;

    @BindView(R.id.tv_news_header)
    TextView tvNewsHeader;

    @BindView(R.id.iv_hot_swap_new_image)
    ImageView ivNewsImage;

    private NewsItemDelegate mDelegate;
    private NewsVO news;
    public HotSwapNewsViewHolder(View itemView,NewsItemDelegate newsItemDelegate) {
        super(itemView);
        mDelegate = newsItemDelegate;

    }



    @Override
    public void setData(NewsVO data) {
        news =data;
        tvNewsFounder.setText(data.getPublication().getTitle());

        tvNewsHeader.setText(data.getBrief());

        if (!data.getImages().isEmpty()){
            Glide.with(ivNewsImage.getContext())
                    .load(data.getImages().get(0))
                    .into(ivNewsImage);
        }
        else {
            ivNewsImage.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
     mDelegate.onTapNews(news);
    }
}
