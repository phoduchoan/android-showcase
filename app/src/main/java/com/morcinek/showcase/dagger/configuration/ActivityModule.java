package com.morcinek.showcase.dagger.configuration;

import com.morcinek.showcase.dagger.activity.ShowcaseActivity;
import com.morcinek.showcase.home.HomeActivity;
import com.morcinek.showcase.splash.SplashActivity;

import dagger.Module;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
@Module(
        injects = {
                HomeActivity.class,
        },
        addsTo = ApplicationModule.class,
        library = true
)
public class ActivityModule {

    private final ShowcaseActivity showcaseActivity;

    public ActivityModule(ShowcaseActivity showcaseActivity) {
        this.showcaseActivity = showcaseActivity;
    }
}