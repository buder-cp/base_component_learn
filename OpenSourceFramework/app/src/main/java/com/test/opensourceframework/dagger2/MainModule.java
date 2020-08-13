package com.test.opensourceframework.dagger2;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    @Provides
    public Flower provideRedRose() {
        return new Flower("玫瑰", "红色");
    }
}
