package com.chilitech.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FocusActivity extends Activity {

    private RecyclerView rvLeft;
    private FocusAdapter adapter;

    public static  boolean hasFocused = false;

    //TODO Kael 有用
    private int mCurrentIndex = -1;

    public static void goActivity(Context context) {
        Intent intent = new Intent(context, FocusActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus);
        rvLeft = (RecyclerView) findViewById(R.id.rv_left);
        initData();
    }


    protected void initData() {
        Bean bean = new Bean();
        List<String> list = new ArrayList<>();
        List<Boolean> booleanList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("这是第" + i + "个");
            booleanList.add(false);
        }
        bean.setList(list);
        bean.setBooleanList(booleanList);

        adapter = new FocusAdapter(this, bean);
        adapter.setOnFocusListener(new FocusAdapter.OnFocusListener() {
            @Override
            public void onFocusChange(int position) {
                //TODO Kael 有用
                mCurrentIndex = position;

                rvLeft.getChildAt(mCurrentIndex).setOnFocusChangeListener(new View.OnFocusChangeListener() {
                            @Override
                            public void onFocusChange(View view, boolean b) {
                                if (b) {
                                    Log.e("wangxiaoqi", "getChildAt-hasFocus: " + mCurrentIndex);
                                } else {
                                    Log.e("wangxiaoqi", "getChildAt-no: " + mCurrentIndex);
                                }
                            }
                        });


//                ((TextView) (rvLeft.getChildAt(mCurrentIndex).findViewById(R.id.tv))).setTextColor(ContextCompat.getColor(FocusActivity.this, R.color.design_default_color_error));
            }
        });
        rvLeft.setLayoutManager(new LinearLayoutManager(this));
        rvLeft.setAdapter(adapter);
        rvLeft.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    Log.e("wangxiaoqi", "rv获得焦点了");
                    hasFocused = true;
                    //TODO Kael 有用
                    View menuView;
                    RecyclerView.LayoutManager layoutManager = rvLeft.getLayoutManager();

                    if (mCurrentIndex > 0) {
                        menuView = layoutManager.findViewByPosition(mCurrentIndex);
                    } else {
                        menuView = layoutManager.findViewByPosition(0);
                    }
                    if (menuView != null) {
                        menuView.requestFocus();
                    }
                } else {
                    Log.e("wangxiaoqi", "rv失去焦点了");
                }
//                else {
//                    if (hasFocused) {
//                        if (mCurrentIndex > 0) {
//                            RecyclerView.LayoutManager layoutManager = rvLeft.getLayoutManager();
//                            View viewByPosition = layoutManager.findViewByPosition(mCurrentIndex);
//                            ((TextView) (viewByPosition.findViewById(R.id.tv))).setTextColor(ContextCompat.getColor(FocusActivity.this, R.color.design_default_color_error));
//                            hasFocused = false;
//                        }
//                    }
//                }
            }
        });


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rvLeft.requestFocus();
            }
        }, 3_000);

    }
}
