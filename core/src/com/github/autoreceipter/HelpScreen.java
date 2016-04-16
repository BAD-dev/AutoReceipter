package com.github.autoreceipter;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

/**
 * Created by Julian on 4/14/2016.
 */
public class HelpScreen extends BaseScreen {

    public HelpScreen(final AutoReceipter app) {
        super(app);

        Label label = new Label("Help Screen", app.skin);

        table.defaults().pad(6f);
        table.setBackground(new NinePatchDrawable(getNinePatch("background/background_white_noheader.png")));
        table.add(label).row();

    }

    @Override
    public void onBackPress() {
        app.switchScreens(new MainMenuScreen(app));
    }

    @Override
    public transitionDir getDirection(BaseScreen nextScreen) {
        return transitionDir.UP;
    }
}
