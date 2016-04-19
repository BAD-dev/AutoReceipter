package com.github.autoreceipter;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

import java.util.ArrayList;

/**
 * Created by Georg on 4/18/2016.
 */
public class ShoppingScreen extends BaseScreen {

    ArrayList<FridgeItem> items;

    public ShoppingScreen(final AutoReceipter app) {
        super(app);

        table.defaults().pad(6f);
        table.setBackground(new NinePatchDrawable(getNinePatch("background/background_white_noheader.png")));

        ImageButton manual = new ImageButton(app.skin.get("questionMarkStyle", ImageButton.ImageButtonStyle.class));
        ImageButton automatic = new ImageButton(app.skin.get("questionMarkStyle", ImageButton.ImageButtonStyle.class));

        table.add(manual).padLeft(20);
        table.add(automatic).row();

        items = new ArrayList<FridgeItem>();

        final BaseScreen screen = this;
        manual.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.switchScreens(new ManualItemInsertionScreen(app, items, screen));
            }
        });

        automatic.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                generateAutomaticList();
            }
        });

    }

    public ShoppingScreen(final AutoReceipter app,  ArrayList<FridgeItem> createdList) {
        super(app);

        table.defaults().pad(6f);
        table.setBackground(new NinePatchDrawable(getNinePatch("background/background_white_noheader.png")));

        TextButton manual = new TextButton("Manual", app.skin);
        table.add(manual).row();

        items = new ArrayList<FridgeItem>();

        final BaseScreen screen = this;
        manual.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.switchScreens(new ManualItemInsertionScreen(app, items, screen));
            }
        });

        addItemsToTable(createdList);

    }

    public void addItemsToTable(ArrayList<FridgeItem> list) {
        for(int x=0; x<list.size(); x++) {
            table.add(list.get(x).widget);
        }
    }

    public void generateAutomaticList() {
        for(int x=0; x<app.items.size(); x++) {
            //if(app.items.get(x).getLastPurchased())
        }
    }

    @Override
    public void onBackPress() {
        app.switchScreens(new MainMenuScreen(app));
    }

    @Override
    public transitionDir getDirection(BaseScreen nextScreen) {
        return transitionDir.DOWN;
    }
}
