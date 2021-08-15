package com.chilitech.app.architecture.ui.adapter;

import androidx.annotation.NonNull;

import com.chilitech.base.adapter.recyclerview.BaseViewHolder;
import com.chilitech.base.adapter.recyclerview.RecyclerBaseAdapter;

import java.util.List;



public class SimpleAdapter extends RecyclerBaseAdapter<String> {

    public SimpleAdapter(@NonNull List<String> dataList, int itemLayoutId) {
        super(dataList, itemLayoutId);
    }

    @Override
    public void convert(BaseViewHolder holder, String s, int position) {

    }
}
