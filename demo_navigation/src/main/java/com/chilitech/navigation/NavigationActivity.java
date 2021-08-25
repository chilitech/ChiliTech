package com.chilitech.navigation;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.List;

public class NavigationActivity extends AppCompatActivity {

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;

    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity_main);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();


        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Bundle bundle = new MainFragmentArgs.Builder().setUserName("Michael").setAge(30).build().toBundle();
//


                List<String> list = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    list.add(i + "");
                }


String[] str = new String[]{"1", "2", "3"};

                Bundle bundle = new ContentFragmentArgs.Builder(str).build().toBundle();
                navController.navigate(R.id.action_global_contentFragment, bundle);
//                Navigation.createNavigateOnClickListener(R.id.action_categoryDetailFragment_to_contentFragment);

//                Navigation.findNavController(view).navigate(R.id.action_categoryDetailFragment_to_contentFragment);
            }
        });

        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navController.navigate(R.id.action_global_categoryDetailFragment);




            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}