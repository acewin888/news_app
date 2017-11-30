package com.example.android.newsapp.util;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by kevinsun on 11/21/17.
 */

public class RxBus {

    private static volatile RxBus rxBus;

    private final Subject<Object, Object> mBus;

    public RxBus(){ mBus = new SerializedSubject<>(PublishSubject.create());}

    public static RxBus getInstance(){
        if(rxBus == null){
            synchronized (RxBus.class){
                if(rxBus == null){
                    rxBus = new RxBus();
                }
            }
        }
        return  rxBus;
    }

    public void post(Object o){ mBus.onNext(o);}

    public <T>Observable<T> toObservable(Class<T> eventType){
        return mBus.ofType(eventType);
    }
}
