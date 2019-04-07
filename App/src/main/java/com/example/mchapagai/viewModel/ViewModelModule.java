package com.example.mchapagai.viewModel;

import com.example.mchapagai.service.ServiceModule;

import dagger.Module;

@Module(
        includes = {ServiceModule.class}
)
public class ViewModelModule {
}