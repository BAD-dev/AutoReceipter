package com.github.autoreceipter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

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

    public static float padSize, defaultDuration;
    //public float screenDuration;

    public abstract void onBackPress();

    public enum transitionDir {UP, DOWN, LEFT, RIGHT}
    public abstract transitionDir getDirection(BaseScreen nextScreen);

    public BaseScreen(AutoReceipter app) {
        this.app = app;
        padSize = Math.round(Math.max(app.width, app.height)*.02f);
        defaultDuration = app.transitionDuration;

        table.defaults().pad(padSize);
        table.setBackground(new NinePatchDrawable(getNinePatch("background/background.png")));
        //table.setBackground(app.skin.getDrawable("bg_header"));
        table.setSize(app.width, app.height);
        this.addActor(table);
    }

    public NinePatch getNinePatch(String fName) {
        final Texture t = new Texture(Gdx.files.internal(fName));
        return new NinePatch( new TextureRegion(t, 1, 1 , t.getWidth() - 2, t.getHeight() - 2), 10, 10, 10, 10);
    }

    public BaseScreen show() {
        return this;
    }

    // Moves the screen left
    public void screenTransition(transitionDir d) {
        float x, y;
        float duration = .333f;
        //float x = -app.widgetWidth;
        MoveToAction action;

        if(d == transitionDir.LEFT) {
            x = -app.width;
            action = Actions.moveTo(x, 0f, duration);
        }
        else if(d == transitionDir.RIGHT) {
            x = app.width;
            action = Actions.moveTo(x, 0f, duration);
        }

        else if (d == transitionDir.UP) {
            y = app.height;
            action = Actions.moveTo(0f, y, duration);
        }

        // down
        else {
            y = -app.height;
            action = Actions.moveTo(0f, y, duration);
        }

        addAction(action);
    }

    public void hide() { }
}
