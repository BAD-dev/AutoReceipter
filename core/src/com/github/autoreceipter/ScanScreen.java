package com.github.autoreceipter;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

/**
 * Created by Julian on 3/22/2016.
 *
 * Scan screen class
 */
public class ScanScreen extends BaseScreen {

    public ScanScreen(final AutoReceipter app) {
        super(app);

        //final ImageButton backButton = new ImageButton(app.skin.get("backButtonStyle", ImageButton.ImageButtonStyle.class));

        Label label = new Label("Scan Screen", app.skin);

        table.defaults().pad(6f);
        table.setBackground(new NinePatchDrawable(getNinePatch("background/background_noheader.png")));
        table.add(label);
        table.row();
        //table.add(backButton);

        /*backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.switchScreens(new MainMenuScreen(app));
                backButton.setChecked(false);
            }
        });*/

    }

    @Override
    public void onBackPress() {
        app.switchScreens(new MainMenuScreen(app));
    }

    @Override
    public transitionDir getDirection() {
        return transitionDir.RIGHT;
    }
}
