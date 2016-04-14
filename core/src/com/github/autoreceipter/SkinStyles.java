package com.github.autoreceipter;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
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
        font.getData().setScale(5, 5);
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
        skin.add("scan-button", atlas.findRegion("scan-button2"), TextureRegion.class);
        skin.add("fridge-button", atlas.findRegion("fridge-button2"), TextureRegion.class);
        skin.add("list-button", atlas.findRegion("list-button2"), TextureRegion.class);
        skin.add("scan-button-clicked", atlas.findRegion("scan-button2-clicked"), TextureRegion.class);
        skin.add("fridge-button-clicked", atlas.findRegion("fridge-button2-clicked"), TextureRegion.class);
        skin.add("list-button-clicked", atlas.findRegion("list-button2-clicked"), TextureRegion.class);
        skin.add("question-mark-button", atlas.findRegion("question-mark-button"), TextureRegion.class);
        skin.add("question-mark-button-clicked", atlas.findRegion("question-mark-button-clicked"), TextureRegion.class);

        /* TextureRegions */
        TextureRegionDrawable scanButtonRegion = new TextureRegionDrawable(new TextureRegion(skin.getRegion("scan-button")));
        TextureRegionDrawable scanButtonRegionClicked = new TextureRegionDrawable(new TextureRegion(skin.getRegion("scan-button-clicked")));
        TextureRegionDrawable fridgeButtonRegion = new TextureRegionDrawable(new TextureRegion(skin.getRegion("fridge-button")));
        TextureRegionDrawable fridgeButtonRegionClicked = new TextureRegionDrawable(new TextureRegion(skin.getRegion("fridge-button-clicked")));
        TextureRegionDrawable listButtonRegion = new TextureRegionDrawable(new TextureRegion(skin.getRegion("list-button")));
        TextureRegionDrawable listButtonRegionClicked = new TextureRegionDrawable(new TextureRegion(skin.getRegion("list-button-clicked")));
        //TextureRegionDrawable backButtonRegion = new TextureRegionDrawable(new TextureRegion(skin.getRegion("back-button")));
        TextureRegionDrawable questionMarkButtonRegion = new TextureRegionDrawable(new TextureRegion(skin.getRegion("question-mark-button")));
        TextureRegionDrawable questionMarkButtonRegionClicked = new TextureRegionDrawable(new TextureRegion(skin.getRegion("question-mark-button-clicked")));

        /* ImageButtons */
        ImageButton.ImageButtonStyle scan_ibs = new ImageButton.ImageButtonStyle(scanButtonRegion, scanButtonRegionClicked, scanButtonRegionClicked, scanButtonRegion, scanButtonRegionClicked, scanButtonRegionClicked);
        ImageButton.ImageButtonStyle fridge_ibs = new ImageButton.ImageButtonStyle(fridgeButtonRegion, fridgeButtonRegionClicked, fridgeButtonRegionClicked, fridgeButtonRegion, fridgeButtonRegionClicked, fridgeButtonRegionClicked);
        ImageButton.ImageButtonStyle list_ibs = new ImageButton.ImageButtonStyle(listButtonRegion, listButtonRegionClicked, listButtonRegionClicked, listButtonRegion, listButtonRegionClicked, listButtonRegionClicked);
        //ImageButton.ImageButtonStyle back_ibs = new ImageButton.ImageButtonStyle(backButtonRegion, backButtonRegion, backButtonRegion, backButtonRegion, backButtonRegion, backButtonRegion);
        ImageButton.ImageButtonStyle question_ibs = new ImageButton.ImageButtonStyle(questionMarkButtonRegion, questionMarkButtonRegionClicked, questionMarkButtonRegionClicked, questionMarkButtonRegion, questionMarkButtonRegionClicked, questionMarkButtonRegionClicked);
        //ImageTextButton.ImageTextButtonStyle scan_tbs = new ImageTextButton.ImageTextButtonStyle()

        skin.add("scanButtonStyle", scan_ibs);
        skin.add("fridgeButtonStyle", fridge_ibs);
        skin.add("listButtonStyle", list_ibs);
        //skin.add("backButtonStyle", back_ibs);
        skin.add("questionMarkStyle", question_ibs);
    }
}
