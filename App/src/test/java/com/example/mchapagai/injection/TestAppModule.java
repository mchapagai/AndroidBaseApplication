package com.example.mchapagai.injection;

import com.example.mchapagai.viewModel.ViewModelModuleTest;

import dagger.Module;

/**
 * Test module that mirrors AppModule but mocks the dependencies
 */
@Module(
        includes = ViewModelModuleTest.class
)
class TestAppModule {
}
