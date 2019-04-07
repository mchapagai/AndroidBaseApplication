package com.example.mchapagai.injection;

import com.example.mchapagai.activity.HomeActivityTest;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

/**
 * Test App component used for replacing injected members with mocks for Robolectric testing.
 * Also serves to inject actual Robolectric test classes
 */
@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                TestAppModule.class,
                ViewsModule.class
        }
)
public interface TestAppComponent extends AndroidInjector<DaggerApplication> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<DaggerApplication> {
    }

    void inject(HomeActivityTest mainActivityTest);
}