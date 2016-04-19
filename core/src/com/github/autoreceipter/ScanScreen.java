package com.github.autoreceipter;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.github.autoreceipter.ocr.ImageProcessing;

import java.io.File;

/**
 * Created by Julian on 3/22/2016.
 *
 * Scan screen class
 */
public class ScanScreen extends BaseScreen {

    public ScanScreen(final AutoReceipter app) {
        super(app);

        //final ImageButton backButton = new ImageButton(app.skin.get("backButtonStyle", ImageButton.ImageButtonStyle.class));

        final FileIO fileIO = new FileIO("data/convertedText.txt", FileIO.STORAGE.EXTERNAL);

        Label label = new Label("Scan Screen", app.skin);

        table.defaults().pad(6f);
        table.setBackground(new NinePatchDrawable(getNinePatch("background/background_white_noheader.png")));
        //table.add(label);
        table.row();

        final ImageButton decodeButton = new ImageButton(app.skin.get("decodeButtonStyle", ImageButton.ImageButtonStyle.class));
        table.add(decodeButton);

        decodeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ImageProcessing processor = new ImageProcessing();
                try {
                    processor.ProcessFile(TestApp.taker.scannedImagePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(processor.getConvertedFile().trim());
                fileIO.writeFile(processor.getConvertedFile().trim(), false);
                app.switchScreens(new FridgeScreen(app));
            }
        });
    }

    @Override
    public void onBackPress() {
        app.switchScreens(new MainMenuScreen(app));
    }

    @Override
    public transitionDir getDirection(BaseScreen nextScreen) {
        return transitionDir.UP;
    }
}