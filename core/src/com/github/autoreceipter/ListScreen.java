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

        app.fileIO.changeFilePath("data/jorge.txt", FileIO.STORAGE.EXTERNAL);
        app.fileIO.saveItems(items);
        //final ImageButton backButton = new ImageButton(app.skin.get("backButtonStyle", ImageButton.ImageButtonStyle.class));

        Label label = new Label("Shopping List Screen", app.skin);

        table.reset();
        table.defaults().pad(6f);
        table.setBackground(new NinePatchDrawable(getNinePatch("background/background_white_noheader.png")));
        //table.add(label).row();


        scrollTable = new Table();
        final ScrollPane scrollPane = new ScrollPane(scrollTable);

        // vertical only scrolling
        scrollPane.setScrollingDisabled(true, false);

        FridgeItem.setDimensions(app.width, app.height);

        list = new ArrayList<ListItem>();
        generateListItems(items);

        ImageButton fridgeButton = new ImageButton(app.skin.get("okButtonStyle", ImageButton.ImageButtonStyle.class));

        fridgeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                for(int i=0; i<list.size(); i++)
                    if(list.get(i).checked)
                        app.items.add(items.get(i));
                app.switchScreens(new FridgeScreen(app));
            }
        });

        table.add(scrollPane).row();
        table.add(fridgeButton);
    }

    public void generateListItems(ArrayList<FridgeItem> items) {
        for(int x=0; x<items.size(); x++) {
            Color c = new Color(MathUtils.random(0.5f), MathUtils.random(0.5f), MathUtils.random(0.5f), 1f);
            ListItem o = new ListItem(items.get(x), app.skin);
            list.add(o);
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
