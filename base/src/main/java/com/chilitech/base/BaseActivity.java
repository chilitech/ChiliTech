package com.chilitech.base;

import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.chilitech.base.utils.ToolBarUtil;



public abstract class BaseActivity extends AppCompatActivity {

    public String TAG = this.getClass().getSimpleName();

    private Toolbar toolbar;

    protected abstract int getLayoutId();

    protected abstract void onSetContentView();

    protected abstract void setupView(Bundle savedInstanceState);

    protected abstract void initData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        onSetContentView();
        setupToolbar();
        setupView(savedInstanceState);
        initData();
    }

    /**
     * 初始化Toolbar
     */
    private void setupToolbar() {
        if (toolbar == null) {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            if (toolbar != null) {
                toolbar.setTitle("");
                setSupportActionBar(toolbar);
            }
        }
    }

    public Toolbar getToolbar() {
        return toolbar;
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        ToolBarUtil.setStatusBarImmersiveWindowFocusChanged(this, hasFocus);
    }

    /**
     * 添加fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()) {
            fragmentTransaction.add(containerViewId, fragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * 显示fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void showFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()) {
            fragmentTransaction.add(containerViewId, fragment);
        }
        fragmentTransaction.show(fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        }
        super.onDestroy();
    }
}
