package com.github.autoreceipter;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by Julian on 3/22/2016.
 *
 *
 * Abstract class to be used by all Screen classes
 */
public abstract class BaseScreen extends Group {

    protected final AutoReceipter app;
    protected final Table table = new Table();
    //protected final AssetManager assetManager = new AssetManager();

    public static float padSize;
    //public float screenDuration;

    public abstract void onBackPress();

    public BaseScreen(AutoReceipter app) {
        this.app = app;
        padSize = Math.round(Math.max(app.width, app.height)*.02f);

        table.defaults().pad(padSize);
        table.setSize(app.width, app.height);
        this.addActor(table);
    }

    public BaseScreen show() {
        return this;
    }

    // Moves the screen left
    public void screenTransition() {
        float x = -app.width;
        float duration = .333f;

        MoveToAction action = Actions.moveTo(x, 0f, duration);
        addAction(action);
    }

    public void hide() { }
}
