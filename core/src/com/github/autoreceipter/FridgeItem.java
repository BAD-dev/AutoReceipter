package com.github.autoreceipter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Julian on 4/14/2016.
 */
public class FridgeItem extends Widget {

    private final class ImageClick extends ClickListener {
        private FridgeItem item;

        public ImageClick(FridgeItem item) {
            this.item = item;
        }
    }

    public static float widgetWidth, widgetHeight;

    public Table widget;
    public Table stats;

    private String name;
    private Label description;
    private Image image;
    private int quantity;
    private Color color;
    private Date lastPurchased;
    private int daysSinceLastPurchase;
    private int totalQuantity;
    private Skin skin;

    public FridgeItem(String name, String description, Color color, Skin skin) {
        this.widget = new Table();
        this.stats = new Table();
        this.name = name;
        this.description = new Label(description, skin);
        this.image = new Image(skin.getRegion("BADdev"));
        this.color = color;
        this.quantity = 1;
        this.lastPurchased = new Date();
        this.skin = skin;

        image.setColor(color);
        image.addListener(new ImageClick(this));

        setWidget();
    }

    private final void setWidget() {
        //widget.setWidth(widgetWidth);
        //widget.setHeight(widgetHeight);
        widget.defaults();
        //widget.setBackground(new NinePatchDrawable(getNinePatch("background/background_trans.png")));
        widget.setWidth(widgetWidth);
        widget.add(image).center().left();

        stats.add(new Label(getItemName(), skin, "itemNameStyle")).pad(2f).left().top().row();
        stats.add(new Label(getQuantity(), skin)).pad(2f).left().top().row();
        stats.add(new Label(getLastPurchased(), skin)).pad(2f).left().top().row();

        widget.add(stats);

        widget.debug();
        stats.debug();
    }

    // Sets dimensions for the this widget
    public static void setDimensions(float w, float h) {
        widgetWidth = w;
        widgetHeight = h;
    }

    // Returns a string containing item name
    public final String getItemName() {
        return "Name: " + name;
    }

    // Get the current quantity of this item
    public final String getQuantity() {
        return "Quantity: " + quantity;
    }

    // Set the date of the last purchased item
    public final void setDate(Date date) {
        this.lastPurchased = date;
    }

    // Returns a string containing the date this item was last purchased
    public final String getLastPurchased() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return "Last Purchased: " + dateFormat.format(lastPurchased);
    }

    // Not sure what to do with description. Maybe used for picking an image
    public final Label getDescription() {
        return description;
    }

    // Returns the image used for this item
    public final Image getImage() {
        return image;
    }

    // Should be called whenever we scan in a new item
    public final void incrementQuantity(int i) {
        quantity += i;
    }

    public final Color getColor() {
        return color;
    }

    // Used for getting background image
    private NinePatch getNinePatch(String fName) {
        final Texture t = new Texture(Gdx.files.internal(fName));
        return new NinePatch(new TextureRegion(t, 1, 1, t.getWidth() - 2, t.getHeight() - 2), 10, 10, 10, 10);
    }
}
