package com.github.autoreceipter;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.autoreceipter.ocr.ImageProcessing;

/**
 * Created by Julian on 4/16/2016.
 *
 * A Fridge Item with an attached CheckBox
 * Used for item selection
 */
public class ListItem extends FridgeItem {

    public CheckBox checkBox;
    public boolean checked = true;

    public ListItem(String name,  double cost, int quantity, Color color, Skin skin) {
        super(name, cost, quantity, color, skin);

        checkBox = new CheckBox("", skin);
        checkBox.getCells().get(0).size(100, 100);
        checkBox.setChecked(true);
        checkBox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (checked)
                    checked = false;
                else
                    checked = true;

                checkBox.setChecked(checked);
            }
        });

        widget.add(checkBox).pad(20f).right();
    }

    /*
     * Simplified constructor if a
     * FridgeItem is to be copied
     */
    public ListItem(FridgeItem item, Skin skin) {
        super(item.getItemName(), item.getCost(), item.quantity, item.getColor(), skin);

        checkBox = new CheckBox("", skin);
        checkBox.getCells().get(0).size(100, 100);
        checkBox.setChecked(true);
        checkBox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (checked)
                    checked = false;
                else
                    checked = true;

                checkBox.setChecked(checked);
            }
        });

        widget.add(checkBox).padLeft(20).right();
    }

    /*
     *  Show/Hide checkbox
     */
    public ListItem visibilityCheckbox(boolean b) {
        checkBox.setVisible(b);
        return this;
    }

    /*
     *  Set the state of checked and show
     *  state in checkbox
     */
    public void setChecked(boolean b) {
        checkBox.setChecked(b);
        checked = b;
    }
}
