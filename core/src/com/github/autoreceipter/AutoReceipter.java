package com.github.autoreceipter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AutoReceipter extends Game {
	public SpriteBatch batch;
    private BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
        font = new BitmapFont();

        this.setScreen(new MainMenuScreen(this, batch));
	}

	@Override
	public void render () {
		super.render();
	}

    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
