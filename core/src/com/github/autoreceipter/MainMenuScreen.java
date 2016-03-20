package com.github.autoreceipter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Julian on 3/20/2016.
 */
public class MainMenuScreen implements Screen{

    final AutoReceipter obj;
    final SpriteBatch batch;

    private OrthographicCamera camera;
    private Texture img;
    private Texture scanButtonTexture;
    private Texture fridgeButtonTexture;
    private Texture listButtonTexture;
    private Sound scannerBeepSound;

    private Rectangle scanButton;
    private Rectangle fridgeButton;
    private Rectangle listButton;

    public MainMenuScreen(final AutoReceipter obj, final SpriteBatch batch) {
        this.obj = obj;
        this.batch = batch;

        // Load assets
        img = new Texture("badlogic.jpg");
        scanButtonTexture = new Texture(Gdx.files.internal("scan-button.png"));
        fridgeButtonTexture = new Texture(Gdx.files.internal("fridge-button.png"));
        listButtonTexture = new Texture(Gdx.files.internal("list-button.png"));
        scannerBeepSound = Gdx.audio.newSound(Gdx.files.internal("store-scanner-beep.mp3"));

        // To ensure the screen size is a constant resolution
        camera = new OrthographicCamera();
        int width = 480;
        int height = 800;
        camera.setToOrtho(false, width, height);

        createButtons(width, height);
    }

    /**
     * Method to instantiate the SCAN, FRIDGE, and LIST buttons
     * @param width The width, in pixels, of the screen (default 480)
     * @param height Not currently used
     */
    public void createButtons(int width, int height) {
        // Create rectangle objects. By default, all rendering in libGDX is
        // performed with the y-axis pointing upwards.
        listButton = new Rectangle();
        listButton.width = 290;
        listButton.height = 104;
        listButton.x = width / 2 - listButton.width / 2;
        listButton.y = 20;

        fridgeButton = new Rectangle();
        fridgeButton.width = 290;
        fridgeButton.height = 104;
        fridgeButton.x = width / 2 - fridgeButton.width / 2;
        fridgeButton.y = 40 + listButton.height;

        scanButton = new Rectangle();
        scanButton.width = 290;
        scanButton.height = 104;
        scanButton.x = width / 2 - scanButton.width / 2;
        scanButton.y = 60 + listButton.height + fridgeButton.height;
    }

    /**
     * Called when this screen becomes the current screen for an application
     */
    @Override
    public void show() {

    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        // Clear the screen with a dark blue color
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update the camera
        camera.update();

        // Tell the SpriteBatch to render in the coordinate
        // system specified by the camera
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(scanButtonTexture, scanButton.x, scanButton.y);
        batch.draw(fridgeButtonTexture, fridgeButton.x, fridgeButton.y);
        batch.draw(listButtonTexture, listButton.x, listButton.y);
        batch.end();
    }

    /**
     * @param width
     * @param height
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     *
     */
    @Override
    public void pause() {

    }

    /**
     *
     */
    @Override
    public void resume() {

    }

    /**
     * Called when this screen is no longer the current screen for an application
     */
    @Override
    public void hide() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }
}
