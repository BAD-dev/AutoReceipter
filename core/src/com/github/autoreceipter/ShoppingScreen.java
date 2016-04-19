package com.github.autoreceipter;

import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

import java.util.ArrayList;

/**
 * Created by Georg on 4/18/2016.
 */
public class ShoppingScreen extends BaseScreen {

    ArrayList<FridgeItem> items;

    public ShoppingScreen(AutoReceipter app) {
        super(app);

        table.defaults().pad(6f);
        table.setBackground(new NinePatchDrawable(getNinePatch("background/background_white_noheader.png")));


    }
    public ShoppingScreen(AutoReceipter app,  ArrayList<FridgeItem> createdList) {
        super(app);

        table.defaults().pad(6f);
        table.setBackground(new NinePatchDrawable(getNinePatch("background/background_white_noheader.png")));

        addItemsToTable(createdList);

    }

    public void addItemsToTable(ArrayList<FridgeItem> list) {
        for(int x=0; x<list.size(); x++) {
            table.add(list.get(x));
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
