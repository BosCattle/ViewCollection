package com.kevin.android.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;

import com.kevin.android.R;
import com.kevin.android.adapter.RecyclerViewAdapter;
import com.kevin.android.ui.viewgroup.DialogActivity;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Class: MainActivity </br>
 * Description: text page </br>
 * Creator: Kevin </br>
 * Date: 2016/8/20 12:24 </br>
 * Update: 2016/8/20 2016/8/20 </br>
 */
public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.onItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    @InjectView(R.id.main_recycler)
    RecyclerView mIndexRecycler;
    @InjectView(R.id.activity_main)
    RelativeLayout activityMain;

    private RecyclerViewAdapter mAdapter;
    private ArrayList<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setUpAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDatas.add("仙女散花");
        mDatas.add("View事件分发");
        mDatas.add("Dialog测试");
    }

    private void setUpAdapter() {
        mDatas = new ArrayList<>();
        mAdapter = new RecyclerViewAdapter(this,mDatas);
        mIndexRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//        mIndexRecycler.addItemDecoration(null);
        mIndexRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onClick(int position, String value) {
        Log.d(TAG, "onClick: "+value);
        switch (position){
            case 0:
                FlowerActivity.startFlower(MainActivity.this);
                break;
            case 1:
                EventDistributionActivity.startEventDistribution(MainActivity.this);
                break;
            case 2:
                DialogActivity.startDialog(MainActivity.this);
                break;
        }
    }
}
