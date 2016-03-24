package com.github.autoreceipter;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Julian on 3/22/2016.
 *
 * This class holds fonts, textures, skins, and styles to be used by actors
 */
public class SkinStyles {

    public void style(Skin skin, TextureAtlas atlas) {
        /* Fonts */
        BitmapFont font = new BitmapFont();
        skin.add("default", font);

        /* Labels */
        Label.LabelStyle lbs = new Label.LabelStyle();
        lbs.font = font;
        lbs.fontColor = Color.WHITE;
        skin.add("default", lbs);

        /* Colors */
        skin.add("lt-blue", new Color(.6f, .8f, 1f, 1f));
        skin.add("lt-green", new Color(.6f, .9f, .6f, 1f));
        skin.add("dark-blue", new Color(.1f, .3f, 1f, 1f));

        /* Skins */
        skin.add("scan-button", atlas.findRegion("scan-button"), TextureRegion.class);
        skin.add("fridge-button", atlas.findRegion("fridge-button"), TextureRegion.class);
        skin.add("list-button", atlas.findRegion("list-button"), TextureRegion.class);
        skin.add("back-button", atlas.findRegion("back-button"), TextureRegion.class);

        /* TextureRegions */
        TextureRegionDrawable scanButtonRegion = new TextureRegionDrawable(new TextureRegion(skin.getRegion("scan-button")));
        TextureRegionDrawable fridgeButtonRegion = new TextureRegionDrawable(new TextureRegion(skin.getRegion("fridge-button")));
        TextureRegionDrawable listButtonRegion = new TextureRegionDrawable(new TextureRegion(skin.getRegion("list-button")));
        TextureRegionDrawable backButtonRegion = new TextureRegionDrawable(new TextureRegion(skin.getRegion("back-button")));

        /* ImageButtons */
        ImageButton.ImageButtonStyle scan_ibs = new ImageButton.ImageButtonStyle(scanButtonRegion, scanButtonRegion, scanButtonRegion, scanButtonRegion, scanButtonRegion, scanButtonRegion);
        ImageButton.ImageButtonStyle fridge_ibs = new ImageButton.ImageButtonStyle(fridgeButtonRegion, fridgeButtonRegion, fridgeButtonRegion, fridgeButtonRegion, fridgeButtonRegion, fridgeButtonRegion);
        ImageButton.ImageButtonStyle list_ibs = new ImageButton.ImageButtonStyle(listButtonRegion, listButtonRegion, listButtonRegion, listButtonRegion, listButtonRegion, listButtonRegion);
        ImageButton.ImageButtonStyle back_ibs = new ImageButton.ImageButtonStyle(backButtonRegion, backButtonRegion, backButtonRegion, backButtonRegion, backButtonRegion, backButtonRegion);

        skin.add("scanButtonStyle", scan_ibs);
        skin.add("fridgeButtonStyle", fridge_ibs);
        skin.add("listButtonStyle", list_ibs);
        skin.add("backButtonStyle", back_ibs);
    }
}
