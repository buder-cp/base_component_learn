package com.test.opensourceframework.dagger2;

import com.test.opensourceframework.MainActivity;

import dagger.Component;

@Component(modules = {MainModule.class})
public interface MainComponent {
    void inject(MainActivity activity);
}
