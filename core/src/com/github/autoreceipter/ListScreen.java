package com.github.autoreceipter;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.ArrayMap;

/**
 * Created by Julian on 3/23/2016.
 *
 * Shopping list screen class
 */
public class ListScreen extends BaseScreen {

    // List of items in our shopping list
    private ArrayMap<Integer, ListItem> list;

    public ListScreen(final AutoReceipter app) {
        super(app);

        //final ImageButton backButton = new ImageButton(app.skin.get("backButtonStyle", ImageButton.ImageButtonStyle.class));

        Label label = new Label("Shopping List Screen", app.skin);
        CheckBox checkBox = new CheckBox("Sample Checkbox", app.skin);

        table.defaults().pad(6f);
        table.setBackground(new NinePatchDrawable(getNinePatch("background/background_white_noheader.png")));
        table.add(label).row();
        table.add(checkBox).row();

        table.debug();
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
    public transitionDir getDirection(BaseScreen nextScreen) {
        return transitionDir.RIGHT;
    }
}
