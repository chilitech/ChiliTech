package com.chilitech.lebapp.activity;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.chilitech.lebapp.LiveEventBusDemo;
import com.chilitech.lebapp.R;
import com.chilitech.lebapp.databinding.ActivityObserverActiveLevelDemoBinding;
import com.chilitech.livedatabus.LiveDataBus;


public class ObserverActiveLevelActivity extends AppCompatActivity {

    private ActivityObserverActiveLevelDemoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_observer_active_level_demo);
        binding.setLifecycleOwner(this);
        binding.setHandler(this);
        LiveDataBus.config(LiveEventBusDemo.KEY_TEST_ACTIVE_LEVEL_SINGLE)
                .lifecycleObserverAlwaysActive(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void sendMsgToPrevent() {
        LiveDataBus
                .get(LiveEventBusDemo.KEY_TEST_ACTIVE_LEVEL)
                .post("Send Msg To Observer Stopped");
    }

    public void sendMsgToPreventSingle() {
        LiveDataBus
                .get(LiveEventBusDemo.KEY_TEST_ACTIVE_LEVEL_SINGLE)
                .post("Send Msg To Observer Stopped");
    }
}
