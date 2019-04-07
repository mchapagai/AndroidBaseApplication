package com.example.mchapagai.injection;

import com.example.mchapagai.activity.AboutActivity;
import com.example.mchapagai.activity.BaseActivity;
import com.example.mchapagai.activity.HomeActivity;
import com.example.mchapagai.fragment.BaseFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ViewsModule {
    @ActivityScope
    @ContributesAndroidInjector
    abstract BaseActivity baseActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract HomeActivity homeActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract AboutActivity aboutActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract BaseFragment baseFragment();
}