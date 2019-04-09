package com.example.mchapagai.utils;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxUtils {

    private RxUtils() {
        throw new AssertionError();
    }

    /**
     * Transform an existing {@link Observable} to apply appropriate {@link Schedulers}
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> applySchedulers() {
        return new ObservableTransformer<T, T>() {
            /**
             * Applies a function to the upstream Observable and returns an ObservableSource with
             * optionally different element type.
             *
             * @param upstream the upstream Observable instance
             * @return the transformed ObservableSource instance
             */
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> FlowableTransformer<T, T> applyFlowableSchedulers() {
        return new FlowableTransformer<T, T>() {

            /**
             * Applies a function to the upstream Flowable and returns a Publisher with
             * optionally different element type.
             *
             * @param upstream the upstream Flowable instance
             * @return the transformed Publisher instance
             */
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}