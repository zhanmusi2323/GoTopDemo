package com.real.gotopdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements OnClickListener {

    private List<String> mItems = new ArrayList<String>();
    private int mScreenHeight;
    private ListView mListVi;
    private Button mBtnTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initViews();
        setListeners();
    }

    private void initData() {
        initScreenHeight();
        for (int i = 0; i < 50; i++) {
            mItems.add("item:" + i);
        }
    }

    private void initScreenHeight() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        mScreenHeight = dm.heightPixels;
    }

    private void initViews() {
        mListVi = (ListView) findViewById(R.id.listview);
        mBtnTop = (Button) findViewById(R.id.btn_top);
        mListVi.setAdapter(new MyAdapter());
    }

    private void setListeners() {
        mBtnTop.setOnClickListener(this);
        mListVi.setOnScrollListener(new OnScrollYListener(mListVi) {

            @Override
            protected void onScrollY(int scrolledY) {
                if (scrolledY > mScreenHeight) {
                    mBtnTop.setVisibility(View.VISIBLE);
                } else {
                    mBtnTop.setVisibility(View.GONE);
                }
            }
        });
    }

    class MyAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        public MyAdapter() {
            super();
            this.mInflater = LayoutInflater.from(MainActivity.this);
        }

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_list, parent, false);
            }
            ((TextView) convertView).setText(mItems.get(position));
            return convertView;
        }

    }

    @Override
    public void onClick(View v) {
        if (android.os.Build.VERSION.SDK_INT >= 8) {
            mListVi.smoothScrollToPosition(0);
        } else {
            mListVi.setSelection(0);
        }

    }


}


