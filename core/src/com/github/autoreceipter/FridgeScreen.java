package com.github.autoreceipter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Julian on 3/22/2016.
 */
public class FridgeScreen extends BaseScreen {

    public FridgeScreen(final AutoReceipter app) {
        super(app);

        final ImageButton backButton = new ImageButton(app.skin.get("backButtonStyle", ImageButton.ImageButtonStyle.class));

        Label label = new Label("Fridge Screen", app.skin);

        table.defaults().pad(6f);
        table.add(label);
        table.row();
        table.add(backButton);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.switchScreens(new MainMenuScreen(app));
                backButton.setChecked(false);
            }
        });
    }

    @Override
    public void onBackPress() {
        app.switchScreens(new MainMenuScreen(app));
    }
}