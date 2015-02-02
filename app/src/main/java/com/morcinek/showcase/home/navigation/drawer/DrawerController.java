package com.morcinek.showcase.home.navigation.drawer;

import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.morcinek.showcase.R;
import com.morcinek.showcase.author.AuthorFragment;
import com.morcinek.showcase.education.EducationListFragment;
import com.morcinek.showcase.experience.ExperienceListFragment;
import com.morcinek.showcase.home.HomeContentController;
import com.morcinek.showcase.skills.SkillsListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class DrawerController implements AdapterView.OnItemClickListener {

    private final HomeContentController homeContentController;
    private final DrawerLayout drawerLayout;

    private final ListView drawerListView;

    public DrawerController(FragmentActivity activity, HomeContentController homeContentController, DrawerLayout drawerLayout) {
        this.homeContentController = homeContentController;
        this.drawerLayout = drawerLayout;

        drawerListView = (ListView) activity.findViewById(R.id.left_drawer);
        drawerListView.setOnItemClickListener(this);
        setupDrawerListAdapter(activity);
    }

    private void setupDrawerListAdapter(FragmentActivity activity) {
        DrawerListAdapter drawerListAdapter = new DrawerListAdapter(activity);
        drawerListAdapter.setList(prepareDrawerItemList());
        drawerListView.setAdapter(drawerListAdapter);
    }

    private List<DrawerItem> prepareDrawerItemList() {
        ArrayList<DrawerItem> drawerItems = new ArrayList<>();
        drawerItems.add(new DrawerItem(R.string.author_title, new AuthorFragment()));
        drawerItems.add(new DrawerItem(R.string.education_list_title, new EducationListFragment()));
        drawerItems.add(new DrawerItem(R.string.experience_list_title, new ExperienceListFragment()));
        drawerItems.add(new DrawerItem(R.string.skills_list_title, new SkillsListFragment()));
        return drawerItems;
    }

    private void selectItemAtPosition(DrawerItem drawerItem, int position) {
        homeContentController.addFragment(drawerItem.getFragment());

        drawerListView.setItemChecked(position, true);
        drawerLayout.closeDrawer(drawerListView);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectItemAtPosition((DrawerItem) parent.getItemAtPosition(position), position);
    }

    public void showDefaultFragment() {
        selectItemAtPosition((DrawerItem) drawerListView.getItemAtPosition(0), 0);
    }
}