package com.github.autoreceipter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;


/**
 * Created by Julian on 3/20/2016.
 */
public class MainMenuScreen extends BaseScreen {

    public MainMenuScreen(final AutoReceipter app) {
        super(app);

        final ImageButton scanButton = new ImageButton(app.skin.get("scanButtonStyle", ImageButton.ImageButtonStyle.class));
        final ImageButton fridgeButton = new ImageButton(app.skin.get("fridgeButtonStyle", ImageButton.ImageButtonStyle.class));
        final ImageButton listButton = new ImageButton(app.skin.get("listButtonStyle", ImageButton.ImageButtonStyle.class));

        scanButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.switchScreens(new ScanScreen(app));
                scanButton.setChecked(false);
            }
        });

        fridgeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.switchScreens(new FridgeScreen(app));
                fridgeButton.setChecked(false);
            }
        });

        listButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.switchScreens(new ListScreen(app));
                listButton.setChecked(false);
            }
        });

        table.defaults().pad(6f);
        //table.setBackground(app.skin.getDrawable("scan-button"));
        table.bottom();
        table.setColor(app.skin.getColor("lt-blue"));
        table.add(scanButton);
        table.row();
        table.add(fridgeButton);
        table.row();
        table.add(listButton);
        table.row();
        table.debug();
    }

    @Override
    public void onBackPress() {
        app.switchScreens(new MainMenuScreen(app));
    }
}
