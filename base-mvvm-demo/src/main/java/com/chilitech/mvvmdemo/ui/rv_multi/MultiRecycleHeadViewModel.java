package com.chilitech.mvvmdemo.ui.rv_multi;

import androidx.annotation.NonNull;
import com.chilitech.mvvm.base.BaseViewModel;
import com.chilitech.mvvm.base.MultiItemViewModel;
import com.chilitech.mvvm.binding.command.BindingAction;
import com.chilitech.mvvm.binding.command.BindingCommand;
import com.chilitech.mvvm.utils.ToastUtils;

/**
 * Create Author：goldze
 * Create Date：2019/01/25
 * Description：
 */

public class MultiRecycleHeadViewModel extends MultiItemViewModel {

    public MultiRecycleHeadViewModel(@NonNull BaseViewModel viewModel) {
        super(viewModel);
    }

    //条目的点击事件
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ToastUtils.showShort("我是头布局");
        }
    });
}
