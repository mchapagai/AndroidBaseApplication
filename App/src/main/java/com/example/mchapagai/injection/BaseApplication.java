package com.example.mchapagai.injection;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class BaseApplication extends DaggerApplication {

    private static BaseApplication baseApplication;

    public static BaseApplication getBaseApplication() {
        if (baseApplication == null) {
            baseApplication = new BaseApplication();
        }
        return baseApplication;
    }

    /**
     * Implementations should return an {@link AndroidInjector} for the concrete {@link
     * dagger.android.support.DaggerApplication}. Typically, that injector is a {@link dagger.Component}.
     */

    public AndroidInjector<BaseApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }

}
