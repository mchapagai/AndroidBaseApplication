package com.example.mchapagai.service;

import com.example.mchapagai.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
        * @author Mono Chapagai
        * Singleton class for making the newtwork call for specifit URL (requests) and getting responses
        */

public class ServiceFactory {
    private static Retrofit retrofit = null;
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    /**
     * Creates a service using the base url
     *
     * @param clazz The class of the service type we want to instantiate
     * @param <T> The the service type
     * @return  An instance of the service
     */

    public static <T> T createService(final Class<T> clazz) {
        if (retrofit == null) {

            httpClient.addInterceptor(chain -> {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .setQueryParameter(Constants.API_KEY, Constants.API_VALUE)
                        .build();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Accept", "application/json")
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            });

            httpClient.connectTimeout(Constants.INTERCEPTOR_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));

            OkHttpClient okHttpClient = httpClient.build();

            Retrofit.Builder builder = new Retrofit.Builder()
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(Constants.SERVICE_ENDPOINT);

            retrofit = builder.build();
        }
        return retrofit.create(clazz);
    }
}

