package com.example.mchapagai.injection;

import com.example.mchapagai.viewModel.ViewModelModule;

import dagger.Module;

@Module(
        includes = ViewModelModule.class
)
class AppModule {
//    @Provides
//    @Singleton
//    Context context() {
//        return BaseApplication.getBaseApplication();
//    }
}
