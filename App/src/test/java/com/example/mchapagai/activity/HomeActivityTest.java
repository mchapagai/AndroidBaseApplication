package com.example.mchapagai.activity;

import com.example.mchapagai.injection.TestDaggerSetupApplication;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

@Ignore
@RunWith(RobolectricTestRunner.class)
public class HomeActivityTest {

    @Before
    public void setup() {
        // inject the unit test with the same dependencies that will be injected into the activity
        TestDaggerSetupApplication app = (TestDaggerSetupApplication) RuntimeEnvironment.application;
        app.getTestAppComponent().inject(this);
    }
}