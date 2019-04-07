package com.example.mchapagai.injection;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class TestDaggerSetupApplication extends DaggerApplication {

    AndroidInjector<DaggerApplication> injector;

    @Override
    public AndroidInjector<DaggerApplication> applicationInjector() {
        if (injector == null) {
            injector = DaggerTestAppComponent.builder().create(this);
        }

        return injector;
    }

    public TestAppComponent getTestAppComponent() {
        return (TestAppComponent) applicationInjector();
    }

}