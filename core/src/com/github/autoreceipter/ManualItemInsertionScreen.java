package com.github.autoreceipter;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

import java.util.ArrayList;

/**
 * Created by Georg on 4/19/2016.
 */
public class ManualItemInsertionScreen extends BaseScreen {

    ArrayList<FridgeItem> list;

    public ManualItemInsertionScreen(final AutoReceipter app, final ArrayList<FridgeItem> listToAddTo, final BaseScreen screenToSwap) {
        super(app);

        list = listToAddTo;

        table.defaults().pad(6f);
        table.setBackground(new NinePatchDrawable(getNinePatch("background/background_white_noheader.png")));


       // final TextField field = new TextField("NAME", app.skin);
       // table.add(field).row();

        ImageButton accept = new ImageButton(app.skin.get("manualButtonStyle", ImageButton.ImageButtonStyle.class));
        accept.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Color c = new Color(MathUtils.random(0.5f), MathUtils.random(0.5f), MathUtils.random(0.5f), 1f);
                FridgeItem o = new FridgeItem("UserNameInput", 0.0, 1, c, app.skin);
                listToAddTo.add(o);

                if(screenToSwap instanceof ShoppingScreen)
                    app.switchScreens(new ShoppingScreen(app, listToAddTo));
                else if(screenToSwap instanceof  ListScreen)
                    app.switchScreens(new ListScreen(app, listToAddTo));
                else
                    app.switchScreens(new MainMenuScreen(app));
            }
        });

        table.add(accept);

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
