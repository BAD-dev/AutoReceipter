package com.github.autoreceipter;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Julian on 3/20/2016.
 *
 * The first screen you see when app starts.
 */
public class MainMenuScreen extends BaseScreen {

    public MainMenuScreen(final AutoReceipter app) {
        super(app);

        // Create our three buttons
        final ImageButton scanButton = new ImageButton(app.skin.get("scanButtonStyle", ImageButton.ImageButtonStyle.class));
        final ImageButton fridgeButton = new ImageButton(app.skin.get("fridgeButtonStyle", ImageButton.ImageButtonStyle.class));
        final ImageButton listButton = new ImageButton(app.skin.get("listButtonStyle", ImageButton.ImageButtonStyle.class));
        final ImageButton questionMarkButton = new ImageButton(app.skin.get("questionMarkStyle", ImageButton.ImageButtonStyle.class));

        // Change screens if scan button is pressed
        scanButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.switchScreens(new ScanScreen(app));
                scanButton.setChecked(false);
            }
        });

        // Change screens if fridge button is pressed
        fridgeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.switchScreens(new FridgeScreen(app));
                fridgeButton.setChecked(false);
            }
        });

        // Change screens if list button is pressed
        listButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.switchScreens(new ListScreen(app));
                listButton.setChecked(false);
            }
        });

        // Set up table and add in our buttons
        table.defaults().pad(6f);
        //table.setBackground(app.skin.getDrawable("scan-button"));
        //table.setBackground(app.skin.getDrawable())
        table.add(questionMarkButton).expandY().expandX().top().right();
        table.row();
        //table.bottom();
        //table.setColor(app.skin.getColor("lt-blue"));
        table.add(scanButton);
        table.row();
        table.add(fridgeButton);
        table.row();
        table.add(listButton).padBottom(300f);
        //table.row();
        //table.debug();
    }

    @Override
    public void onBackPress() {
        app.switchScreens(new MainMenuScreen(app));
    }

    @Override
    public transitionDir getDirection() {
        return transitionDir.LEFT;
    }
}
