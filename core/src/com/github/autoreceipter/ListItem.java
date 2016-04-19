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
 */
public class ListItem extends Widget {

    public Table widget;
    public Table stats;
    public Color color;
    public Skin skin;
    public CheckBox checkBox;
    public boolean checked = true;
    public String name;

    public ListItem(String name, Color color, Skin skin) {
        //super(name, color, skin);
        this.widget = new Table();
        this.color = color;
        this.skin = skin;

        this.name = name;
        widget.defaults();

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

        widget.add(new Label(name, skin)).pad(20f).left();
        widget.add(checkBox).pad(20f).right();
    }

    public ListItem(FridgeItem item, Skin skin) {
        //super(item.getItemName(), item.getDescription().getText().toString(), item.getColor(), skin);
        this.widget = new Table();
        widget.defaults();

        this.name = item.name;

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

        widget.add(new Label(name, skin)).pad(20f).left();
        widget.add(checkBox).padLeft(20).right();
    }

    public ListItem visibilityCheckbox(boolean b) {
        checkBox.setVisible(b);
        return this;
    }

    public void setChecked(boolean b) {
        checkBox.setChecked(b);
        checked = b;
    }
}
