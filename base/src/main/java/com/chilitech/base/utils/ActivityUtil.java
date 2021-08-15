package com.chilitech.base.utils;

import android.content.Intent;
import android.os.Build;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;



public class ActivityUtil {

    /**
     * startActivity兼容处理
     *
     * @param intent
     * @param pairs
     */
    @SafeVarargs
    public static void startAwesomeActivity(AppCompatActivity activity, Intent intent, Pair<View, String>... pairs) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityCompat.startActivity(activity, intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                    pairs).toBundle());
        } else {
            activity.startActivity(intent);
        }
    }
}
