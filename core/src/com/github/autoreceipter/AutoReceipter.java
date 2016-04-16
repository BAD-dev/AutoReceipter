package com.github.autoreceipter;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by Julian on 3/20/2016.
 *
 *
 * Application Listener, which holds methods for screen transitions, rendering,
 * resizing, and screen switching
 */
public abstract class AutoReceipter implements ApplicationListener {
	public Stage stage;
    public TextureAtlas atlas;
    public Skin skin;
    public Color color;
    public float width, height;
    public static InputMultiplexer inputs = new InputMultiplexer(); // for multiple inputs
    public float transitionDuration = .333f;    // duration of transitions
    private float totalTransitionTime = -420f;  //
    private boolean changeScreen = false;

    public BaseScreen activeScreen, nextScreen;

    //protected abstract String atlasPath();
    //protected abstract String skinPath();
    //protected abstract void styleSkin(Skin skin, TextureAtlas atlas);
    protected abstract BaseScreen getFirstScreen();

	@Override
	public void create () {
        width = 810;//Gdx.graphics.getWidth();//
        height = 1440;//Gdx.graphics.getHeight();//
        System.out.println(width +" "+ height);

        stage = new Stage(new StretchViewport(width, height));

        // packedImage.atlas generated using imagepacker tool
        atlas = new TextureAtlas("packed/packedImage.atlas");
        skin = new Skin(atlas);
        new SkinStyles().style(skin, atlas);

        /*String skinPath = skinPath();
        if(skinPath != null)
            skin.load(Gdx.files.internal(skinPath));
        styleSkin(skin, atlas);*/

        Gdx.input.setInputProcessor(stage);

        // Input listener for the android "back" key
        Gdx.input.setCatchBackKey(true);
        stage.addListener(new InputListener() {
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if(keycode == Input.Keys.BACK) {
                    activeScreen.onBackPress();
                }
                return false;
            }
        });

        activeScreen = getFirstScreen().show();
        stage.addActor(activeScreen);

        Gdx.input.setInputProcessor(inputs);
        inputs.addProcessor(stage);
	}

    /**
     * Called when the {@link Application} is resized. This can happen at any point during a non-paused state but will never happen
     * before a call to {@link #create()}.
     *
     * @param width  the new widgetWidth in pixels
     * @param height the new height in pixels
     */
    @Override
    public void resize(int width, int height) {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        stage.getViewport().setWorldSize(width, height);
    }

    @Override
    // This will remove the old screen after it has fully transitioned,
    // based on the delta time.
	public void render () {
		float delta = Gdx.graphics.getDeltaTime();

        if(totalTransitionTime > 0f) {
            totalTransitionTime -= delta;

            if(totalTransitionTime <= 0f) {
                activeScreen.hide();
                activeScreen.remove();
                activeScreen = nextScreen;
                activeScreen.setTouchable(Touchable.enabled);
                activeScreen.setPosition(0f, 0f);
                //nextScreen = null;
            }
        }

        /*if(changeScreen) {
            activeScreen.hide();
            activeScreen.remove();
            activeScreen = nextScreen;
            activeScreen.setTouchable(Touchable.enabled);
            activeScreen.setPosition(0f, 0f);
            nextScreen = null;
            changeScreen = false;
        }*/

        color = new Color(Color.BLACK);
        Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
	}

    public void setColor(Color newColor) {
        color.set(newColor);
    }

    // This is called whenever a screen changes
    public void switchScreens(BaseScreen screen) {
        totalTransitionTime = activeScreen.defaultDuration;

        nextScreen = screen;
        nextScreen.setTouchable(Touchable.disabled);
        nextScreen.show();
        stage.addActor(nextScreen);

        if (activeScreen != null) {
            activeScreen.screenTransition(activeScreen.getDirection(nextScreen));
            //changeScreen = true;
            activeScreen.setTouchable(Touchable.disabled);
            //activeScreen.toBack();
            activeScreen.toFront();
            //activeScreen.hide();
        }
    }

    /**
     * Called when the {@link Application} is paused, usually when it's not active or visible on screen. An Application is also
     * paused before it is destroyed.
     */
    @Override
    public void pause() {

    }

    /**
     * Called when the {@link Application} is resumed from a paused state, usually when it regains focus.
     */
    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        atlas.dispose();
    }
}
