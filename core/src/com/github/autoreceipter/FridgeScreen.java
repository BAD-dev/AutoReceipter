package com.github.autoreceipter;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.ArrayMap;

import java.util.ArrayList;

/**
 * Created by Julian on 3/22/2016.
 *
 * Fridge screen class
 */
public class FridgeScreen extends BaseScreen {

    // All fridge items
    //private ArrayMap<Integer, FridgeItem> items;

    // Fridge items currently displayed from search filter
    private ArrayList<FridgeItem> itemsDisplayed;

    private Table scrollTable;

    public FridgeScreen(final AutoReceipter app) {
        super(app);

        //FileIO fileIO = new FileIO("data/data.txt");
        //String text = fileIO.readFile();
        //System.out.println(text);

        table.reset();
        table.defaults().pad(6f);
        table.setBackground(new NinePatchDrawable(getNinePatch("background/background_white_noheader.png")));

        Label label = new Label("Fridge Screen", app.skin, "segoeui");
        scrollTable = new Table();
        final ScrollPane scrollPane = new ScrollPane(scrollTable);

        // vertical only scrolling
        scrollPane.setScrollingDisabled(true, false);

        FridgeItem.setDimensions(app.width, app.height);

        // This will be removed when scanning works
        if(app.items.isEmpty()) {
            app.items = new ArrayList<FridgeItem>();
            createFridgeInventory();
        }

        itemsDisplayed = new ArrayList<FridgeItem>();

        Table container = new Table();

        container.add(new Label("Search", app.skin)).spaceRight(10f);

//        TextField search = new TextField("", app.skin);
//        search.addListener(new InputListener() {
//            public boolean keyDown(InputEvent event, int keycode) {
//                if (keycode == Input.Keys.ENTER)
//                    filterItmes();
//                return super.keyDown(event, keycode);
//            }
//        });

        //container.add(search).minSize(400f, 15f);

        table.add(container).right().row();

        arrangeTable();

        table.add(scrollPane).fill().expand();

        InputListener stopTouchDown = new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                event.stop();
                return false;
            }
        };
    }

    /*
     * Create 30 FridgeItmes and place into fridge list
     */
    private void createFridgeInventory() {
        for(int i = 0; i < 30; i++) {
            String name = "" + (i+1);
            Color c = new Color(MathUtils.random(0.5f), MathUtils.random(0.5f), MathUtils.random(0.5f), 1f);
            FridgeItem o = new FridgeItem(name, 0.00, 1, c, app.skin);
            app.items.add(o);
        }
    }

    private void checkFilter(String text) {

    }

    /*
     * Places all FridgeItems from our list into Scrollpane
     */
    private void arrangeTable() {
        scrollTable.clear();

        //checkFilter(search.get)
        itemsDisplayed.clear();
        for(FridgeItem f : app.items)
            itemsDisplayed.add(f);

        int sizeX = app.skin.getRegion("BADdev").getRegionWidth();
        int sizeY = app.skin.getRegion("BADdev").getRegionHeight();

//        int maxItemsPerLine = (int) (app.widgetWidth / sizeX);
//        maxItemsPerLine -= (int) (20f * maxItemsPerLine / sizeX) + 1;
        int maxItemsPerLine = 1;

        int totalLines = itemsDisplayed.size() / maxItemsPerLine;
        int outOfBounds = itemsDisplayed.size() % maxItemsPerLine;

        int itemCount = 0;
        for(int i = 0; i < totalLines; i++) {
            //itemCount = 0;

            //System.out.println(maxItemsPerLine);

//            while(itemCount < maxItemsPerLine) {
//                addText((i * maxItemsPerLine) + itemCount);
//                itemCount++;
//            }

            //scrollTable.row();
            itemCount = 0;

            while(itemCount < maxItemsPerLine) {
                //addWidget((i * maxItemsPerLine) + itemCount);
                scrollTable.add(itemsDisplayed.get(i + itemCount++).widget).expandX().prefHeight(300f).padLeft(35f).left();
            }

            scrollTable.row();
        }

//        for(int i = 0; i < outOfBounds; i++) {
//            addText((totalLines * maxItemsPerLine) + i);
//        }
//
//        //scrollTable.row();
//
//        for(int i = 0; i < outOfBounds; i++) {
//            addWidget((totalLines * maxItemsPerLine) + i);
//        }
    }

    private final void addText(int i) {
        scrollTable.add(itemsDisplayed.get(i).getDescription()).center();
    }

    public final void addWidget(int i) {
        FridgeItem current = itemsDisplayed.get(i);
//        float x = app.skin.getRegion("BADdev").getRegionWidth();
//        float y = app.skin.getRegion("BADdev").getRegionHeight();
        float x = current.getImage().getWidth();
        float y = current.getImage().getHeight();

        scrollTable.add(itemsDisplayed.get(i).getImage())
                .minHeight(x)
                .minWidth(y)
                .spaceBottom(20f)
                .spaceLeft(20f)
                .spaceRight(20f)
                .left();

        String quantity = "Quantity: ";
        quantity += current.getQuantity() + "\n";
        Label qty = new Label(quantity, app.skin);
        scrollTable.add(qty);

        String lastPurchase = "Last Purchase ";
        lastPurchase += current.getLastPurchased();
        Label lp = new Label(lastPurchase, app.skin);
        scrollTable.add(lp);
    }

    @Override
    public void onBackPress() {
        app.switchScreens(new MainMenuScreen(app));
    }

    @Override
    public transitionDir getDirection(BaseScreen nextScreen) {
        return transitionDir.RIGHT;
    }
}
