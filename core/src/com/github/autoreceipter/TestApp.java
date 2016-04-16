package com.github.autoreceipter;

import com.github.autoreceipter.screens.MainMenuScreen;

/**
 * Created by Julian on 3/22/2016.
 *
 * Test application which initiates a new main menu screen
 */
public class TestApp extends AutoReceipter {

    @Override
    protected com.github.autoreceipter.screens.BaseScreen getFirstScreen() {
        return new MainMenuScreen(this);
    }
}
