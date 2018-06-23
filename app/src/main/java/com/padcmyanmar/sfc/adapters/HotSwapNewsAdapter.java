package com.padcmyanmar.sfc.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.sfc.R;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.delegates.NewsItemDelegate;
import com.padcmyanmar.sfc.viewholders.HotSwapNewsViewHolder;

/**
 * Created by Acer on 6/22/2018.
 */

public class HotSwapNewsAdapter extends BaseRecyclerAdapter<HotSwapNewsViewHolder,NewsVO> {
    private NewsItemDelegate mDelegate;
    public HotSwapNewsAdapter(Context context,NewsItemDelegate delegate) {
        super(context);
        mDelegate = delegate;
    }

    @NonNull
    @Override
    public HotSwapNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflator.inflate(R.layout.view_item_hot_swap_news,parent,false);
        return new HotSwapNewsViewHolder(view,mDelegate);
    }





}
