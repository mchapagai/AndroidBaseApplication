package com.example.mchapagai.fragment;

import android.os.Bundle;

import dagger.android.support.DaggerFragment;

public class BaseFragment extends DaggerFragment {

    public BaseFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
