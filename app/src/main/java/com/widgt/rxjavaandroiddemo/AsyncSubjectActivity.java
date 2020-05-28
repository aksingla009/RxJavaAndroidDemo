package com.widgt.rxjavaandroiddemo;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.AsyncSubject;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;

public class AsyncSubjectActivity extends AppCompatActivity {
    private static final String TAG = "AsyncSubjectActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        asyncSubjectDemo1();
//        asyncSubjectDemo2();
//        behaviorSubjectDemo1();
//        behaviorSubjectDemo2();
//        publishSubjectDemo1();
//        publishSubjectDemo2();
//        replaySubjectDemo1();
        replaySubjectDemo2();
    }

    private void asyncSubjectDemo2() {

        //Another way of creating Observables in RxJava
        //Since rxJava Subjects can directly invoke onNext method of observer

        AsyncSubject<String> asyncSubject = AsyncSubject.create();


        //Subscribe three Observers to the asyncSubject
        asyncSubject.subscribe(getFirstObserver());

        asyncSubject.onNext("JAVA");
        asyncSubject.onNext("KOTLIN");
        asyncSubject.onNext("XML");

        asyncSubject.subscribe(getSecondObserver());

        asyncSubject.onNext("JSON");
        asyncSubject.onComplete();

        asyncSubject.subscribe(getThirdObserver());
    }

    private void asyncSubjectDemo1() {
        Observable<String> observable = Observable.just("JAVA", "KOTLIN", "XML", "JSON").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        AsyncSubject<String> asyncSubject = AsyncSubject.create();

        //Now we have Observable, AsyncSubject and three Observers
        //RxJava Subjects always act as a pipe between Observable and Observers

        //Subscribe AsyncSubject to the Observable
        observable.subscribe(asyncSubject);

        //Subscribe three Observers to the asyncSubject
        asyncSubject.subscribe(getFirstObserver());
        asyncSubject.subscribe(getSecondObserver());
        asyncSubject.subscribe(getThirdObserver());

        //Async Subject only emits the last value of the Observable
        //i.e. from JAVA, KOTLIN, XML and JSON only JSON should be emitted
    }

    private void behaviorSubjectDemo1() {
        Observable<String> observable = Observable.just("JAVA", "KOTLIN", "XML", "JSON").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();

        observable.subscribe(behaviorSubject);

        behaviorSubject.subscribe(getFirstObserver());
        behaviorSubject.subscribe(getSecondObserver());
        behaviorSubject.subscribe(getThirdObserver());

    }

    private void behaviorSubjectDemo2() {

        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();


        //Subscribe three Observers to the behaviorSubject
        behaviorSubject.subscribe(getFirstObserver());

        behaviorSubject.onNext("JAVA");
        behaviorSubject.onNext("KOTLIN");
        behaviorSubject.onNext("XML");

        behaviorSubject.subscribe(getSecondObserver());

        behaviorSubject.onNext("JSON");
        behaviorSubject.onComplete();

        behaviorSubject.subscribe(getThirdObserver());
    }

    private void publishSubjectDemo1() {
        Observable<String> observable = Observable.just("JAVA", "KOTLIN", "XML", "JSON").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        PublishSubject<String> publishSubject = PublishSubject.create();

        observable.subscribe(publishSubject);

        publishSubject.subscribe(getFirstObserver());
        publishSubject.subscribe(getSecondObserver());
        publishSubject.subscribe(getThirdObserver());

    }

    private void publishSubjectDemo2() {

        PublishSubject<String> publishSubject = PublishSubject.create();


        //Subscribe three Observers to the publish Subject
        publishSubject.subscribe(getFirstObserver());

        publishSubject.onNext("JAVA");
        publishSubject.onNext("KOTLIN");
        publishSubject.onNext("XML");

        publishSubject.subscribe(getSecondObserver());

        publishSubject.onNext("JSON");
        publishSubject.onComplete();

        publishSubject.subscribe(getThirdObserver());
    }

    private void replaySubjectDemo1() {
        Observable<String> observable = Observable.just("JAVA", "KOTLIN", "XML", "JSON").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        ReplaySubject<String> replaySubject = ReplaySubject.create();

        observable.subscribe(replaySubject);

        replaySubject.subscribe(getFirstObserver());
        replaySubject.subscribe(getSecondObserver());
        replaySubject.subscribe(getThirdObserver());

    }

    private void replaySubjectDemo2() {

        ReplaySubject<String> replaySubject = ReplaySubject.create();


        //Subscribe three Observers to the publish Subject
        replaySubject.subscribe(getFirstObserver());

        replaySubject.onNext("JAVA");
        replaySubject.onNext("KOTLIN");
        replaySubject.onNext("XML");

        replaySubject.subscribe(getSecondObserver());

        replaySubject.onNext("JSON");
        replaySubject.onComplete();

        replaySubject.subscribe(getThirdObserver());
    }

    private Observer<String> getFirstObserver() {

        Observer<String> observer1 = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG, "Observer1 onSubscribe invoked ");

            }

            @Override
            public void onNext(@NonNull String s) {

                Log.i(TAG, "Observer1 onNext invoked " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG, "Observer1 onError invoked ");

            }

            @Override
            public void onComplete() {
                Log.i(TAG, "Observer1 onComplete invoked");
            }
        };

        return observer1;

    }

    private Observer<String> getSecondObserver() {

        Observer<String> observer2 = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG, "Observer2 onSubscribe invoked ");

            }

            @Override
            public void onNext(@NonNull String s) {

                Log.i(TAG, "Observer2 onNext invoked " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG, "Observer2 onError invoked ");

            }

            @Override
            public void onComplete() {
                Log.i(TAG, "Observer2 onComplete invoked");
            }
        };

        return observer2;

    }

    private Observer<String> getThirdObserver() {

        Observer<String> observer3 = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG, "Observer3 onSubscribe invoked ");

            }

            @Override
            public void onNext(@NonNull String s) {

                Log.i(TAG, "Observer3 onNext invoked " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG, "Observer3 onError invoked ");

            }

            @Override
            public void onComplete() {
                Log.i(TAG, "Observer3 onComplete invoked");
            }
        };

        return observer3;

    }
}
