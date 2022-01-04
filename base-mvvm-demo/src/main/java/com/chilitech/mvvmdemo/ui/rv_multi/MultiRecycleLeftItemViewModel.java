package com.chilitech.mvvmdemo.ui.rv_multi;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.chilitech.mvvm.base.MultiItemViewModel;

import com.chilitech.mvvm.binding.command.BindingAction;
import com.chilitech.mvvm.binding.command.BindingCommand;
import com.chilitech.mvvm.utils.ToastUtils;

/**
 * Create Author：goldze
 * Create Date：2019/01/25
 * Description：
 */

public class MultiRecycleLeftItemViewModel extends MultiItemViewModel<MultiRecycleViewModel> {
    public ObservableField<String> text = new ObservableField<>("");

    public MultiRecycleLeftItemViewModel(@NonNull MultiRecycleViewModel viewModel, String text) {
        super(viewModel);
        this.text.set(text);
    }

    //条目的点击事件
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //拿到position
            int position = viewModel.observableList.indexOf(MultiRecycleLeftItemViewModel.this);
            ToastUtils.showShort("position：" + position);
        }
    });
}
