package com.morcinek.showcase.general.dagger.configuration;

import com.morcinek.showcase.author.AuthorFragment;
import com.morcinek.showcase.education.EducationDetailsFragment;
import com.morcinek.showcase.general.dagger.components.ShowcaseActivity;
import com.morcinek.showcase.education.EducationListFragment;
import com.morcinek.showcase.general.controllers.ProgressBarController;
import com.morcinek.showcase.general.controllers.RefreshProgressController;
import com.morcinek.showcase.general.handlers.RetryLayoutErrorHandler;
import com.morcinek.showcase.general.handlers.ToastErrorHandler;
import com.morcinek.showcase.home.HomeActivity;
import com.morcinek.showcase.general.network.error.ErrorHandler;

import dagger.Module;
import dagger.Provides;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
@Module(
        injects = {
                HomeActivity.class,
                AuthorFragment.class,
                EducationListFragment.class,
                EducationDetailsFragment.class,
        },
        addsTo = ApplicationModule.class,
        library = true
)
public class ActivityModule {

    private final ShowcaseActivity showcaseActivity;

    public ActivityModule(ShowcaseActivity showcaseActivity) {
        this.showcaseActivity = showcaseActivity;
    }

    @Provides
    ErrorHandler provideErrorHandler() {
        return new ToastErrorHandler(showcaseActivity);
    }

    @Provides
    RetryLayoutErrorHandler provideRetryLayoutErrorHandler() {
        return new RetryLayoutErrorHandler(showcaseActivity);
    }

    @Provides
    ProgressBarController provideProgressBarController() {
        return new ProgressBarController(showcaseActivity);
    }

    @Provides
    RefreshProgressController provideRefreshProgressController() {
        return new RefreshProgressController(showcaseActivity);
    }
}