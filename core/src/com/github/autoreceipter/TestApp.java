package com.github.autoreceipter;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Julian on 3/22/2016.
 *
 * Test application which initiates a new main menu screen
 */
public class TestApp extends AutoReceipter {

    @Override
    protected BaseScreen getFirstScreen() {
        return new MainMenuScreen(this);
    }
}
