package com.morcinek.showcase.network;


import com.morcinek.showcase.network.model.Author;
import com.morcinek.showcase.network.model.Education;
import com.morcinek.showcase.network.model.Experience;
import com.morcinek.showcase.network.model.Skill;
import com.morcinek.showcase.network.response.NetworkResponseListener;
import com.morcinek.showcase.network.response.ProgressController;

import java.util.List;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public interface NetworkFacade {

    public void getAuthor(final NetworkResponseListener<Author> responseListener,
                          ProgressController... progressControllers);

    public void getEducation(final NetworkResponseListener<List<Education>> responseListener,
                             ProgressController... progressControllers);

    public void getExperience(final NetworkResponseListener<List<Experience>> responseListener,
                              ProgressController... progressControllers);

    public void getSkills(final NetworkResponseListener<List<Skill>> responseListener,
                          ProgressController... progressControllers);
}