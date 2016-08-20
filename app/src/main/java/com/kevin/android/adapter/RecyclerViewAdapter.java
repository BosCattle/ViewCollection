package com.kevin.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kevin.android.R;

import java.util.ArrayList;

/**
 * Created by Kevin on 2016/8/20.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private Context mContext;
    private ArrayList<String> mItems;
    private LayoutInflater mLayoutInflater;
    private onItemClickListener mOnItemClickListener;

    public RecyclerViewAdapter(Context context, ArrayList<String> items){
        this.mContext = context;
        this.mItems = items;
        mLayoutInflater = LayoutInflater.from(context);
        mOnItemClickListener = (onItemClickListener) context;
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(mLayoutInflater.inflate(R.layout.list_item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        holder.mTitleTextView.setText(mItems.get(position));
        holder.mTitleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onClick(position,mItems.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public interface onItemClickListener{
        void onClick(int position,String value);
    }
}
