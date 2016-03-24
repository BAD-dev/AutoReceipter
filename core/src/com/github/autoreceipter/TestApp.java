package com.github.autoreceipter;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Julian on 3/22/2016.
 */
public class TestApp extends AutoReceipter {

    /*@Override
    protected String atlasPath() {
        return null;
    }

    @Override
    protected String skinPath() {
        return null;
    }

    @Override
    protected void styleSkin(Skin skin, TextureAtlas atlas) {
        new Styles().styleSkin(skin, atlas);
    }
*/
    @Override
    protected BaseScreen getFirstScreen() {
        return new MainMenuScreen(this);
    }
}
