package com.kevin.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kevin.android.R;

/**
 * Class: RecyclerViewHolder </br>
 * Description: viewholder in main page </br>
 * Creator: Kevin </br>
 * Date: 2016/8/20 17:13 </br>
 * Update: 2016/8/20 2016/8/20 </br>
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public TextView mTitleTextView;
    public RecyclerViewHolder(View itemView) {
        super(itemView);
        mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_text);
    }
}
