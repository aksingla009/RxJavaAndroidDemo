package com.widgt.rxjavaandroiddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView greetTV;

//    private String[] greeting = {"AMAN","RAMAN","GAGAN","HARPREET","ISHA","LIPSY","MANVI"};
    private String greeting = "Hello from RxJava";

    private Observable<String> myObservable;
//    private DisposableObserver<String> myObserver;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        greetTV = findViewById(R.id.greetTV);

        myObservable = Observable.just(greeting);
//        myObservable.subscribeOn(Schedulers.io());
//        myObservable.observeOn(AndroidSchedulers.mainThread());

//        myObserver = getObserver();
//        compositeDisposable.add(myObserver);
//        myObservable.subscribe(myObserver);

        compositeDisposable.add(
                myObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver())
        );

    }

    private DisposableObserver<String> getObserver(){

        return new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext: "+s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: "+e);
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: ");
            }
        };

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
