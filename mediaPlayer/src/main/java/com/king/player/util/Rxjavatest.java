package com.king.player.util;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

class Rxjavatest {
    void run() {
        Observable<String> name = Observable.just("name");
        Observable<String> createObservable = Observable
                .create(emitter -> {
                    emitter.setDisposable(new Disposable() {
                        @Override
                        public void dispose() {

                        }

                        @Override
                        public boolean isDisposed() {
                            return false;
                        }
                    });
                });
        Observable<String> mapObservable = createObservable.map(s -> s.concat("hhh"));
        Observable<String> subscribeOnObservable = mapObservable.subscribeOn(new Scheduler() {
            @Override
            public Worker createWorker() {
                return null;
            }
        });
        Observable<String> observeOnObservable = subscribeOnObservable
                .observeOn(Schedulers.io());
        observeOnObservable.subscribe(
                new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) { // 订阅时

                    }

                    @Override
                    public void onNext(String s) { // dispose时候用

                    }

                    @Override
                    public void onError(Throwable e) { // 出错

                    }

                    @Override
                    public void onComplete() { //结束

                    }
                }
        );
    }

   void register(){
    EventBus.getDefault().register(this);
   }

   @Subscribe(threadMode = ThreadMode.MAIN_ORDERED , sticky = true,priority = 3)
   public void onMessage(String str){

   }


}
