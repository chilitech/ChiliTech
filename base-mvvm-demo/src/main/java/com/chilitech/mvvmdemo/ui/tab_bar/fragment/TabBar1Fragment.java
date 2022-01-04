package com.chilitech.mvvmdemo.ui.tab_bar.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.chilitech.mvvmdemo.BR;
import com.chilitech.mvvmdemo.R;

import androidx.annotation.Nullable;
import com.chilitech.mvvm.base.BaseFragment;

/**
 * Created by goldze on 2018/7/18.
 */

public class TabBar1Fragment extends BaseFragment {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_tab_bar_1;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}
