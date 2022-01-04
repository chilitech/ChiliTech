package com.chilitech.lebapp.activity;

import androidx.lifecycle.Observer;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.chilitech.lebapp.LiveEventBusDemo;
import com.chilitech.lebapp.R;
import com.chilitech.lebapp.databinding.ActivityStickyDemoBinding;
import com.chilitech.livedatabus.LiveDataBus;


public class StickyActivity extends AppCompatActivity {

    private ActivityStickyDemoBinding binding;
    private Observer<String> observer = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String s) {
            binding.tvSticky2.setText("observeStickyForever注册的观察者收到消息: " + s);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sticky_demo);
        binding.setLifecycleOwner(this);
        LiveDataBus
                .get(LiveEventBusDemo.KEY_TEST_STICKY, String.class)
                .observeSticky(this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        binding.tvSticky1.setText("observeSticky注册的观察者收到消息: " + s);
                    }
                });
        LiveDataBus
                .get(LiveEventBusDemo.KEY_TEST_STICKY, String.class)
                .observeStickyForever(observer);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LiveDataBus
                .get(LiveEventBusDemo.KEY_TEST_STICKY, String.class)
                .removeObserver(observer);
    }
}
