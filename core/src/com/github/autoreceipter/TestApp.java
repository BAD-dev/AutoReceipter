package com.github.autoreceipter;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.github.autoreceipter.ocr.CameraController;

/**
 * Created by Julian on 3/22/2016.
 *
 * Test application which initiates a new main menu screen
 */
public class TestApp extends AutoReceipter {

    //Used to launch camera task
    public static CameraController taker;

    public TestApp(CameraController taker) {
        this.taker = taker;
    }

    @Override
    protected BaseScreen getFirstScreen() {
        return new MainMenuScreen(this);
    }
}
