package com.chilitech.base;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public abstract class BaseFragment extends Fragment {

    public String TAG = this.getClass().getSimpleName();

    private Toolbar toolbar;

    protected abstract int getLayoutId();

    protected abstract void onFragmentCreate();

    protected abstract void setupArguments(Bundle args);

    protected abstract void onFragmentViewCreated(View view, Bundle savedInstanceState);

    protected abstract void setupView(View view, Bundle savedInstanceState);

    protected abstract void initData();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onFragmentCreate();
        if (getArguments() != null) {
            setupArguments(getArguments());
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onFragmentViewCreated(view,savedInstanceState);
        setupToolbar(view);
        setupView(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 初始化Toolbar
     */
    private void setupToolbar(View view) {
        if (toolbar == null) {
            toolbar = (Toolbar) view.findViewById(R.id.toolbar);
            if (toolbar != null) {
                toolbar.setTitle("");
                ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            }
        }
    }

    public Toolbar getToolbar() {
        return toolbar;
    }
}
