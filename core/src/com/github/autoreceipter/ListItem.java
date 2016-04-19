package com.github.autoreceipter;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
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

    public ListItem(String name, Color color, Skin skin) {
        //super(name, color, skin);
        this.widget = new Table();
        this.color = color;
        this.skin = skin;

        widget.defaults();

        checkBox = new CheckBox("", skin);
        checkBox.getCells().get(0).size(100, 100);
        checkBox.setChecked(true);
        checkBox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(checked)
                    checked = false;
                else
                    checked = true;

                checkBox.setChecked(checked);
            }
        });

        widget.add(checkBox).padLeft(20).right();
    }
}
