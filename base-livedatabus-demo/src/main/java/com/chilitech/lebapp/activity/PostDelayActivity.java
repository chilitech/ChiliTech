package com.chilitech.lebapp.activity;

import androidx.lifecycle.Observer;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.chilitech.lebapp.LiveEventBusDemo;
import com.chilitech.lebapp.R;
import com.chilitech.lebapp.databinding.ActivityPostDelayBinding;
import com.chilitech.livedatabus.LiveDataBus;

public class PostDelayActivity extends AppCompatActivity {

    ActivityPostDelayBinding binding;
    private int sendCount = 1000;
    private int receiveCount = 0;
    public static final String KEY_TEST_DELAY_LIFE_LONG = "key_test_delay_life_long";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_delay);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_delay);
        binding.setLifecycleOwner(this);
        binding.setHandler(this);

        LiveDataBus
                .get(KEY_TEST_DELAY_LIFE_LONG, String.class)
                .observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        Toast.makeText(PostDelayActivity.this,
                                "receiveCount: " + receiveCount, Toast.LENGTH_SHORT).show();
                        receiveCount++;
                    }
                });
    }

    public void  testDelayNoLife(View view){
        LiveDataBus
                .get(LiveEventBusDemo.KEY_TEST_DELAY_LIFE)
                .postDelay("Send Msg To Observer Delay 2s",2000);
        finish();
    }


    public void testDelayWithLife(View view){
        LiveDataBus
                .get(LiveEventBusDemo.KEY_TEST_DELAY_LIFE)
                .postDelay(this,"Send Msg To Observer Delay 2s",2000);
        finish();
    }

    public void testDelayWithLifeLast(View view){
        for(int i= 0;i< sendCount;i++){
            LiveDataBus
                    .get(KEY_TEST_DELAY_LIFE_LONG)
                    .postDelay(this,"Send " + i + " Msg To Observer Delay 2s",2000);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(PostDelayActivity.this, "sendCount: " + sendCount +
                        " | receiveCount: " + receiveCount, Toast.LENGTH_LONG).show();
            }
        }, 3000);
    }

}
