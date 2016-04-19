package com.github.autoreceipter;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.ArrayMap;

import java.util.ArrayList;

/**
 * Created by Julian on 3/23/2016.
 *
 * Shopping list screen class
 */
public class ListScreen extends BaseScreen {

    // List of items in our shopping list
    private ArrayList<ListItem> list;

    Table scrollTable;

    public ListScreen(final AutoReceipter app, final ArrayList<FridgeItem> items) {
        super(app);

        //final ImageButton backButton = new ImageButton(app.skin.get("backButtonStyle", ImageButton.ImageButtonStyle.class));

        Label label = new Label("Shopping List Screen", app.skin);

        table.defaults().pad(6f);
        table.setBackground(new NinePatchDrawable(getNinePatch("background/background_white_noheader.png")));
        table.add(label).row();

        table.debug();

        scrollTable = new Table();
        final ScrollPane scrollPane = new ScrollPane(scrollTable);

        // vertical only scrolling
        scrollPane.setScrollingDisabled(true, false);

        FridgeItem.setDimensions(app.width, app.height);

        generateListItems(items);


        ImageButton fridgeButton = new ImageButton(app.skin.get("fridgeButtonStyle", ImageButton.ImageButtonStyle.class));

        fridgeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                for(int i=0; i<list.size(); i++)
                    if(list.get(i).checked)
                        app.items.add(items.get(i));
                app.switchScreens(new FridgeScreen(app));
            }
        });

        scrollTable.add(fridgeButton);

        table.add(scrollPane);
    }

    public void generateListItems(ArrayList<FridgeItem> items) {
        for(int x=0; x<items.size(); x++) {
            Color c = new Color(MathUtils.random(0.5f), MathUtils.random(0.5f), MathUtils.random(0.5f), 1f);
            ListItem o = new ListItem(items.get(x).getItemName(), c, app.skin);
            scrollTable.add(o.widget).expandX().prefHeight(300f).padLeft(35f).left().row();
        }
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
