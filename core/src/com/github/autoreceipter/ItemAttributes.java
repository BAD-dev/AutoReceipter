package com.github.autoreceipter;


import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by Julian on 4/19/2016.
 */
public class ItemAttributes extends BaseScreen {

    public ItemAttributes(final AutoReceipter app) {
        super(app);

        table.reset();
        table.defaults().pad(6f);
        table.add(new Label("This is the item attribute screen", app.skin, "segoeui_b")).center().expand();
    }

    @Override
    public void onBackPress() {
        app.switchScreens(new FridgeScreen(app));
    }

    @Override
    public transitionDir getDirection(BaseScreen nextScreen) {
        return transitionDir.UP;
    }
}
