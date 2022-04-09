package com.example.mchapagai.utils;

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
     *
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

}