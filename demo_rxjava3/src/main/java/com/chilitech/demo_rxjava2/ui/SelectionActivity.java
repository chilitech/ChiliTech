package com.chilitech.demo_rxjava2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.chilitech.demo_rxjava2.MyApplication;
import com.chilitech.demo_rxjava2.R;
import com.chilitech.demo_rxjava2.ui.cache.CacheExampleActivity;
import com.chilitech.demo_rxjava2.ui.compose.ComposeOperatorExampleActivity;
import com.chilitech.demo_rxjava2.ui.networking.NetworkingActivity;
import com.chilitech.demo_rxjava2.ui.pagination.PaginationActivity;
import com.chilitech.demo_rxjava2.ui.rxbus.RxBusActivity;
import com.chilitech.demo_rxjava2.ui.search.SearchActivity;


public class SelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
    }

    public void startOperatorsActivity(View view) {
        startActivity(new Intent(SelectionActivity.this, OperatorsActivity.class));
    }

    public void startNetworkingActivity(View view) {
        startActivity(new Intent(SelectionActivity.this, NetworkingActivity.class));
    }

    public void startCacheActivity(View view) {
        startActivity(new Intent(SelectionActivity.this, CacheExampleActivity.class));
    }

    public void startRxBusActivity(View view) {
        ((MyApplication) getApplication()).sendAutoEvent();
        startActivity(new Intent(SelectionActivity.this, RxBusActivity.class));
    }

    public void startPaginationActivity(View view) {
        startActivity(new Intent(SelectionActivity.this, PaginationActivity.class));
    }

    public void startComposeOperator(View view) {
        startActivity(new Intent(SelectionActivity.this, ComposeOperatorExampleActivity.class));
    }

    public void startSearchActivity(View view) {
        startActivity(new Intent(SelectionActivity.this, SearchActivity.class));
    }

}
