package com.github.autoreceipter;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

/**
 * Created by Julian on 4/14/2016.
 */
public class HelpScreen extends BaseScreen {

    public HelpScreen(final AutoReceipter app) {
        super(app);

        Label header = new Label("Auto Receipter // B.A.D. Development", app.skin, "segoeui_bold");

        Label body = new Label("   This app is a free grocery receipt scanner and inventory app for the Android operating system.\nThis software will actively scan printed receipts and automatically add them into the user's inventory.\nThis app was created for our Processes of Software Engineering class at the University of Central Florida.\n\n   To use this app, begin scanning a receipt by pressing the Scan button located on the Main Menu screen. Once scanning is complete, the picture that was just taken will be decoded and will automatically populate your personal inventory. To see this inventory, press the Fridge button on the Main Menu screen. From this Fridge screen, you will be able to modify the scanned items' attributes by pressing the three vertical dots. Finally, to see view or create your own shopping list, press the List button from the Main Menu screen. From here, you can manually create your own shopping list, or let the app automatically generate one for you.\n\n\n\n - Georg Anemogiannis\n - Julian Bonnells\n - Jorge De Gouveia\n", app.skin, "segoeui_semibold");
        body.setWidth(header.getWidth());
        body.setWrap(true);

        Label liscence = new Label("All fridge icons designed by Freepik", app.skin, "segoeui_light", Color.BLACK);

        table.reset();
        table.defaults().pad(6f);
        table.setSize(app.width, app.height);
        table.setBackground(new NinePatchDrawable(getNinePatch("background/background_white_noheader.png")));
        table.add(header).top().center().height(100f).row();
        table.add(body).expandY().top().left().padTop(50f).padBottom(50f).width(body.getWidth()).row();
        table.add(liscence).bottom().height(100f).row();
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
