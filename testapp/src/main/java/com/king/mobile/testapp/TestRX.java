package com.king.mobile.testapp;


import org.reactivestreams.Subscription;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

class TestRX {
    public void text(){
        int [] arr = {1,2,3,4,5,6};
        Observable<int[]> fromArray = Observable.fromArray(arr);
        Observable<int[]> just = Observable.just(arr);
        Observable<Integer> map = just.map(ar -> ar.length);
        Observable<Integer> subscribeOn = map.subscribeOn(Schedulers.io());
        Observable<Integer> observeOn = subscribeOn.observeOn(AndroidSchedulers.mainThread());
        Disposable disposable = observeOn.subscribe((o) -> Logger.getLogger("aa").log(Level.OFF, o + ""));
        Observable<Integer> map1 = fromArray.flatMap(ar ->Observable.just(ar.length));

        Single.just(2).subscribe(new SingleObserver(){

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull Object o) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
        Flowable.just(2).onBackpressureBuffer(1,false).subscribe(new FlowableSubscriber<Integer>() {
            @Override
            public void onSubscribe(@NonNull Subscription s) {

            }

            @Override
            public void onNext(Integer integer) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
        Completable.fromFuture(new FutureTask(() -> 5)).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
        Maybe.just(2).subscribe(new MaybeObserver<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull Integer integer) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
