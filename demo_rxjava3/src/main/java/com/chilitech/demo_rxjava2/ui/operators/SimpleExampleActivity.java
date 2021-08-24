package com.chilitech.demo_rxjava2.ui.operators;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.chilitech.demo_rxjava2.R;
import com.chilitech.demo_rxjava2.utils.AppConstant;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class SimpleExampleActivity extends AppCompatActivity {

    private static final String TAG = SimpleExampleActivity.class.getSimpleName();
    Button btn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        btn = findViewById(R.id.btn);
        textView = findViewById(R.id.textView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //将一个或多个对象转换成发射这个或这些对象的一个Observable。如果是单个对象，内部创建的是ScalarSynchronousObservable对象。如果是多个对象，则是调用了from方法创建。
                Observable.just("王小琦", "马良芹", "王颀栎", "郭万英")
                        //指定Observable执行任务的调度器
                        .subscribeOn(Schedulers.io())
                        //指定观察者观察Observable的调度器
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<String>() {

                            @Override
                            public void onSubscribe(Disposable d) {
                                Log.d(TAG, " onSubscribe : Disposable:" + d.isDisposed());
                            }

                            @Override
                            public void onNext(String value) {
                                textView.append(" onNext : value : " + value);
                                textView.append(AppConstant.LINE_SEPARATOR);
                                Log.d(TAG, " onNext : value : " + value);
                            }

                            @Override
                            public void onError(Throwable e) {
                                textView.append(" onError : " + e.getMessage());
                                textView.append(AppConstant.LINE_SEPARATOR);
                                Log.d(TAG, " onError : " + e.getMessage());
                            }

                            @Override
                            public void onComplete() {
                                textView.append(" onComplete");
                                textView.append(AppConstant.LINE_SEPARATOR);
                                Log.d(TAG, " onComplete");
                            }
                        });

            }
        });
    }

    /*
     * simple example to emit two value one by one
     */
    private void doSomeWork() {
        getObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private Observable<String> getObservable() {
        return Observable.just("Cricket", "Football");
    }

    private Observer<String> getObserver() {
        return new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(String value) {
                textView.append(" onNext : value : " + value);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onNext : value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                textView.append(" onError : " + e.getMessage());
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                textView.append(" onComplete");
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onComplete");
            }
        };
    }


}