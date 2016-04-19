package com.github.autoreceipter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Julian on 3/22/2016.
 *
 * This class holds fonts, textures, skins, and styles to be used by actors
 */
public class SkinStyles {

    private Skin skin;
    private TextureAtlas atlas;

    public void style(Skin skin, TextureAtlas atlas) {
        this.skin = skin;
        this.atlas = atlas;

        /* Fonts */
        BitmapFont font = new BitmapFont();
        font.getData().setScale(1, 1);
        skin.add("default", font);
        skin.add("segoeui", font("fonts/segoeui.ttf", 20));
        skin.add("segoeui_bold", font("fonts/segoeui_b.ttf", 30));
        skin.add("segoeui_semi_bold", font("fonts/segoeui_sb.ttf", 20));
        skin.add("segoeui_light", font("fonts/segoeui_l.ttf", 20));

        BitmapFont itemNameFont = new BitmapFont();
        itemNameFont.getData().setScale(2, 2);
        skin.add("itemName", itemNameFont);

        /* Labels */
        Label.LabelStyle lbs = new Label.LabelStyle();
        lbs.font = font;
        lbs.fontColor = Color.WHITE;
        skin.add("default", lbs);

        Label.LabelStyle itemName = new Label.LabelStyle();
        itemName.font = itemNameFont;
        itemName.fontColor = Color.BLACK;
        skin.add("itemNameStyle", itemName);

        Label.LabelStyle segoeui = new Label.LabelStyle();
        segoeui.font = skin.getFont("segoeui");
        segoeui.fontColor = Color.BLACK;
        skin.add("segoeui", segoeui);

        Label.LabelStyle segoeui_bold = new Label.LabelStyle();
        segoeui_bold.font = skin.getFont("segoeui_bold");
        segoeui_bold.fontColor = Color.BLACK;
        skin.add("segoeui_bold", segoeui_bold);

        /* Colors */
        skin.add("lt-blue", new Color(.6f, .8f, 1f, 1f));
        skin.add("lt-green", new Color(.6f, .9f, .6f, 1f));
        skin.add("dark-blue", new Color(.1f, .3f, 1f, 1f));

        /* Loading Animation */
//        Texture walkSheet = new Texture(Gdx.files.internal("loadingSheet.png"));
//        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/6, walkSheet.getHeight());
//        TextureRegion[] frames = new TextureRegion[6]; // number of frames
//        int index = 0;
//        for(int i = 0; i < 1; i++)
//            for(int j = 0; j < 6; j++)
//                frames[index++] = tmp[i][j];
//        Animation loadingAnimation = new Animation(0.025f, frames);
//        skin.add("loadingAnimation", loadingAnimation);

        /* Skins */
        skin.add("scan-button", atlas.findRegion("scan-button3"), TextureRegion.class);
        skin.add("fridge-button", atlas.findRegion("fridge-button3"), TextureRegion.class);
        skin.add("list-button", atlas.findRegion("list-button3"), TextureRegion.class);
        skin.add("scan-button-clicked", atlas.findRegion("scan-button3-clicked"), TextureRegion.class);
        skin.add("fridge-button-clicked", atlas.findRegion("fridge-button3-clicked"), TextureRegion.class);
        skin.add("list-button-clicked", atlas.findRegion("list-button3-clicked"), TextureRegion.class);
        skin.add("question-mark-button", atlas.findRegion("question-mark-button2"), TextureRegion.class);
        skin.add("decode-button", atlas.findRegion("decode-button"), TextureRegion.class);
        skin.add("decode-button-clicked", atlas.findRegion("decode-button-clicked"), TextureRegion.class);
        //skin.add("question-mark-button-clicked", atlas.findRegion("question-mark-button-clicked"), TextureRegion.class);
        skin.add("ok-button", atlas.findRegion("ok-button"), TextureRegion.class);
        skin.add("ok-button-clicked", atlas.findRegion("ok-button-clicked"), TextureRegion.class);
        skin.add("edit-button", atlas.findRegion("edit-button"), TextureRegion.class);
        skin.add("BADdev", atlas.findRegion("BADdev"), TextureRegion.class);

        /* Food skins */
        skin.add("apple-icon", atlas.findRegion("apple"), TextureRegion.class);
        skin.add("banana-icon", atlas.findRegion("banana"), TextureRegion.class);
        skin.add("bread-icon", atlas.findRegion("bread"), TextureRegion.class);
        skin.add("burger-icon", atlas.findRegion("burger"), TextureRegion.class);
        skin.add("candy-icon", atlas.findRegion("candy"), TextureRegion.class);
        skin.add("cheese-icon", atlas.findRegion("cheese"), TextureRegion.class);
        skin.add("coffee-icon", atlas.findRegion("coffee"), TextureRegion.class);
        skin.add("default-icon", atlas.findRegion("default"), TextureRegion.class);
        skin.add("drink-icon", atlas.findRegion("drink"), TextureRegion.class);
        skin.add("fish-icon", atlas.findRegion("fish"), TextureRegion.class);
        skin.add("fruit-icon", atlas.findRegion("fruit"), TextureRegion.class);
        skin.add("icecream-icon", atlas.findRegion("icecream"), TextureRegion.class);
        skin.add("meat-icon", atlas.findRegion("meat"), TextureRegion.class);
        skin.add("pizza-icon", atlas.findRegion("pizza"), TextureRegion.class);

        /* TextureRegions */
        TextureRegionDrawable scanButtonRegion = new TextureRegionDrawable(new TextureRegion(skin.getRegion("scan-button")));
        TextureRegionDrawable scanButtonRegionClicked = new TextureRegionDrawable(new TextureRegion(skin.getRegion("scan-button-clicked")));
        TextureRegionDrawable fridgeButtonRegion = new TextureRegionDrawable(new TextureRegion(skin.getRegion("fridge-button")));
        TextureRegionDrawable fridgeButtonRegionClicked = new TextureRegionDrawable(new TextureRegion(skin.getRegion("fridge-button-clicked")));
        TextureRegionDrawable listButtonRegion = new TextureRegionDrawable(new TextureRegion(skin.getRegion("list-button")));
        TextureRegionDrawable listButtonRegionClicked = new TextureRegionDrawable(new TextureRegion(skin.getRegion("list-button-clicked")));
        //TextureRegionDrawable backButtonRegion = new TextureRegionDrawable(new TextureRegion(skin.getRegion("back-button")));
        TextureRegionDrawable questionMarkButtonRegion = new TextureRegionDrawable(new TextureRegion(skin.getRegion("question-mark-button")));
        TextureRegionDrawable questionMarkButtonRegionClicked = new TextureRegionDrawable(new TextureRegion(skin.getRegion("question-mark-button")));
        //TextureRegionDrawable questionMarkButtonRegionClicked = new TextureRegionDrawable(new TextureRegion(skin.getRegion("question-mark-button-clicked")));
        TextureRegionDrawable BADdevReqion = new TextureRegionDrawable(new TextureRegion(skin.getRegion("BADdev")));
        TextureRegionDrawable checkBox = new TextureRegionDrawable(new TextureRegion(skin.getRegion("checkbox")));
        TextureRegionDrawable checkBoxChecked = new TextureRegionDrawable(new TextureRegion(skin.getRegion("checkbox_checked")));
        TextureRegionDrawable decodeButtonRegion = new TextureRegionDrawable(new TextureRegion(skin.getRegion("decode-button")));
        TextureRegionDrawable decodeButtonRegionClicked = new TextureRegionDrawable(new TextureRegion(skin.getRegion("decode-button-clicked")));
        TextureRegionDrawable okButtonRegion = new TextureRegionDrawable(new TextureRegion(skin.getRegion("ok-button")));
        TextureRegionDrawable okButtonRegionClicked = new TextureRegionDrawable(new TextureRegion(skin.getRegion("ok-button-clicked")));
        TextureRegionDrawable editButton = new TextureRegionDrawable(new TextureRegion(skin.getRegion("edit-button")));

        /* Food texture regions */
//        skin.add("appleRegion", new Image(skin.getRegion("apple-icon")));
//        skin.add("bananaRegion", new Image(skin.getRegion("banana-icon")));
//        skin.add("breadRegion", new Image(skin.getRegion("bread-icon")));
//        skin.add("burgerRegion",new Image(skin.getRegion("burger-icon")));
//        skin.add("candyRegion", new Image(skin.getRegion("candy-icon")));
//        skin.add("cheeseRegion", new Image(skin.getRegion("cheese-icon")));
//        skin.add("coffeeRegion", new Image(skin.getRegion("coffee-icon")));
//        skin.add("defaultRegion", new Image(skin.getRegion("default-icon")));
//        skin.add("drinkRegion", new Image(skin.getRegion("drink-icon")));
//        skin.add("fishRegion", new Image(skin.getRegion("fish-icon")));
//        skin.add("fruitRegion", new Image(skin.getRegion("fruit-icon")));
//        skin.add("icecreamRegion", new Image(skin.getRegion("icecream-icon")));
//        skin.add("meatRegion", new Image(skin.getRegion("meat-icon")));
//        skin.add("pizzaRegion", new Image(skin.getRegion("pizza-icon")));

        //TextField.TextFieldStyle searchStyle
        TextField.TextFieldStyle manualInput = new TextField.TextFieldStyle();
        manualInput.font = skin.getFont("segoeui");
        manualInput.fontColor = Color.BLACK;
        skin.add("textFieldStyle", manualInput);

        /* ImageButtons */
        ImageButton.ImageButtonStyle scan_ibs = new ImageButton.ImageButtonStyle(scanButtonRegion, scanButtonRegionClicked, scanButtonRegionClicked, scanButtonRegion, scanButtonRegionClicked, scanButtonRegionClicked);
        ImageButton.ImageButtonStyle fridge_ibs = new ImageButton.ImageButtonStyle(fridgeButtonRegion, fridgeButtonRegionClicked, fridgeButtonRegionClicked, fridgeButtonRegion, fridgeButtonRegionClicked, fridgeButtonRegionClicked);
        ImageButton.ImageButtonStyle list_ibs = new ImageButton.ImageButtonStyle(listButtonRegion, listButtonRegionClicked, listButtonRegionClicked, listButtonRegion, listButtonRegionClicked, listButtonRegionClicked);
        //ImageButton.ImageButtonStyle back_ibs = new ImageButton.ImageButtonStyle(backButtonRegion, backButtonRegion, backButtonRegion, backButtonRegion, backButtonRegion, backButtonRegion);
        ImageButton.ImageButtonStyle question_ibs = new ImageButton.ImageButtonStyle(questionMarkButtonRegion, questionMarkButtonRegionClicked, questionMarkButtonRegionClicked, questionMarkButtonRegion, questionMarkButtonRegionClicked, questionMarkButtonRegionClicked);
        //ImageTextButton.ImageTextButtonStyle scan_tbs = new ImageTextButton.ImageTextButtonStyle()
        ImageButton.ImageButtonStyle BADdevButtonStyle = new ImageButton.ImageButtonStyle(BADdevReqion,BADdevReqion,BADdevReqion,BADdevReqion,BADdevReqion,BADdevReqion);
        ImageButton.ImageButtonStyle decode_ibs = new ImageButton.ImageButtonStyle(decodeButtonRegion, decodeButtonRegionClicked, decodeButtonRegionClicked, decodeButtonRegion, decodeButtonRegionClicked, decodeButtonRegionClicked);
        ImageButton.ImageButtonStyle ok_ibs = new ImageButton.ImageButtonStyle(okButtonRegion, okButtonRegionClicked, okButtonRegionClicked, okButtonRegion, okButtonRegionClicked, okButtonRegionClicked);
        ImageButton.ImageButtonStyle edit_ibs = new ImageButton.ImageButtonStyle(editButton, editButton, editButton, editButton, editButton, editButton);


        // Checkbox style
        CheckBox.CheckBoxStyle checkBoxStyle = new CheckBox.CheckBoxStyle(checkBox, checkBoxChecked, font, skin.getColor("lt-blue"));

        skin.add("scanButtonStyle", scan_ibs);
        skin.add("fridgeButtonStyle", fridge_ibs);
        skin.add("listButtonStyle", list_ibs);
        //skin.add("backButtonStyle", back_ibs);
        skin.add("questionMarkStyle", question_ibs);
        skin.add("decodeButtonStyle", decode_ibs);
        skin.add("okButtonStyle", ok_ibs);
        skin.add("editButtonStyle", edit_ibs);

        skin.add("BADdevButtonStyle", BADdevButtonStyle);
        skin.add("default", checkBoxStyle);

        skin.add("bg_header", getBackground("main"));
        skin.add("bg_noheader", getBackground(""));
    }

    private BitmapFont font(String fpath, int size) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fpath));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        return font;
    }

    public NinePatchDrawable getBackground(String screen) {
        if(screen.equalsIgnoreCase("main"))
            return new NinePatchDrawable(getNinePatch("background/background_white.png"));
        else
            return new NinePatchDrawable(getNinePatch("background/background_white_noheader.png"));
    }

    private NinePatch getNinePatch(String fName) {
        final Texture t = new Texture(Gdx.files.internal(fName));
        return new NinePatch( new TextureRegion(t, 1, 1 , t.getWidth() - 2, t.getHeight() - 2), 10, 10, 10, 10);
    }
}
