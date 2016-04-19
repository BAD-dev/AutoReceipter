package com.github.autoreceipter;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

import java.util.ArrayList;

/**
 * Created by Georg on 4/18/2016.
 */
public class ShoppingScreen extends BaseScreen {

    static ArrayList<FridgeItem> items;
    ArrayList<ListItem> listItems;
    Table scrollTable;

    boolean deletionMode = false;

    public ShoppingScreen(final AutoReceipter app) {
        super(app);

        table.defaults().pad(6f);
        table.setBackground(new NinePatchDrawable(getNinePatch("background/background_white_noheader.png")));

        ImageButton manual = new ImageButton(app.skin.get("questionMarkStyle", ImageButton.ImageButtonStyle.class));
        ImageButton automatic = new ImageButton(app.skin.get("questionMarkStyle", ImageButton.ImageButtonStyle.class));

        Table buttonTable = new Table();

        buttonTable.add(manual).left().padLeft(100);
        buttonTable.add(new Label("Manual Input", app.skin)).left();
        buttonTable.add().padLeft(200).padRight(200);
        buttonTable.add(new Label("Automatic", app.skin)).right();
        buttonTable.add(automatic).right().padRight(100);

        table.add(buttonTable).row();

        scrollTable = new Table();
        final ScrollPane scrollPane = new ScrollPane(scrollTable);

        scrollPane.setScrollingDisabled(true, false);

        table.add(scrollPane).left().expand().fill();

        if (items == null)
            items = new ArrayList<FridgeItem>();
        else
            addItemsToTable(items);

        final BaseScreen screen = this;
        manual.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.switchScreens(new ManualItemInsertionScreen(app, items, screen));
            }
        });

        automatic.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                generateAutomaticList();
            }
        });

        table.row();

        if (listItems != null && listItems.size() > 0) {
            Table forDeletion = new Table();
            ImageButton deletion = new ImageButton(app.skin.get("questionMarkStyle", ImageButton.ImageButtonStyle.class));
            forDeletion.add(deletion);
            forDeletion.add(new Label("Deletion", app.skin)).left();
            table.add(forDeletion);

            deletion.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (!deletionMode) {
                        for (int i = 0; i < listItems.size(); i++) {
                            listItems.get(i).visibilityCheckbox(true).setChecked(false);

                        }
                        deletionMode = true;
                    } else {
                        for (int i = 0; i < listItems.size(); i++) {
                            if (listItems.get(i).checked) {
                                items.remove(i);
                            }
                        }

                        app.switchScreens(new ShoppingScreen(app, items));
                        deletionMode = false;
                    }
                }
            });
         }

    }

    public ShoppingScreen(final AutoReceipter app,  ArrayList<FridgeItem> createdList) {
        super(app);

        table.defaults().pad(6f);
        table.setBackground(new NinePatchDrawable(getNinePatch("background/background_white_noheader.png")));

        ImageButton manual = new ImageButton(app.skin.get("questionMarkStyle", ImageButton.ImageButtonStyle.class));
        ImageButton automatic = new ImageButton(app.skin.get("questionMarkStyle", ImageButton.ImageButtonStyle.class));

        Table buttonTable = new Table();

        buttonTable.add(manual).left().padLeft(100);
        buttonTable.add(new Label("Manual Input", app.skin)).left();
        buttonTable.add().padLeft(200).padRight(200);
        buttonTable.add(new Label("Automatic", app.skin)).right();
        buttonTable.add(automatic).right().padRight(100);

        table.add(buttonTable).row();

        items = createdList;

        scrollTable = new Table();
        final ScrollPane scrollPane = new ScrollPane(scrollTable);
        scrollTable.debug();

        scrollPane.setScrollingDisabled(true, false);

        table.add(scrollPane).left().expand().fill().row();

        final BaseScreen screen = this;
        manual.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.switchScreens(new ManualItemInsertionScreen(app, items, screen));
            }
        });

        addItemsToTable(createdList);

        Table forDeletion = new Table();
        ImageButton deletion = new ImageButton(app.skin.get("questionMarkStyle", ImageButton.ImageButtonStyle.class));
        forDeletion.add(deletion);
        forDeletion.add(new Label("Deletion", app.skin)).left();
        table.add(forDeletion);

        deletion.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!deletionMode) {
                    for (int i = 0; i < listItems.size(); i++) {
                        listItems.get(i).visibilityCheckbox(true).setChecked(false);

                    }
                    deletionMode = true;
                } else {
                    for (int i = 0; i < listItems.size(); i++) {
                        if (listItems.get(i).checked) {
                            items.remove(i);
                        }
                    }

                    app.switchScreens(new ShoppingScreen(app, items));
                    deletionMode = false;
                }
            }
        });

        table.debug();
    }

    public void addItemsToTable(ArrayList<FridgeItem> list) {
        if(listItems == null)
            listItems = new ArrayList<ListItem>();
        else
            listItems.clear();


        FridgeItem.setDimensions(app.width, app.height);
        for(int x=0; x<list.size(); x++) {
            ListItem itm = new ListItem(list.get(x), app.skin).visibilityCheckbox(false);
            listItems.add(itm);
            scrollTable.add(itm.widget).width(app.width).left().row();
        }
    }

    public void generateAutomaticList() {
        for(int x=0; x<app.items.size(); x++) {
            //if(app.items.get(x).getLastPurchased())
        }
    }

    @Override
    public void onBackPress() {
        app.switchScreens(new MainMenuScreen(app));
    }

    @Override
    public transitionDir getDirection(BaseScreen nextScreen) {
        return transitionDir.FADE;
    }
}
