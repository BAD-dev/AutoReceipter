package com.github.autoreceipter;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.awt.TextArea;
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

    private int id;
    private Label description;
    private Image image;
    private Color color;
    private Date lastPurchased;
    private int daysSinceLastPurchase;
    private int quantity;
    private int totalPurchased;
    private Skin skin;

    public FridgeItem(int index, String description, Color color, Skin skin) {
        this.widget = new Table();
        this.stats = new Table();
        this.id = index;
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
        widget.setWidth(widgetWidth);
        widget.add(image).center().left();

        stats.add(new Label(getId(), skin, "itemNameStyle")).pad(2f).left().top().row();
        stats.add(new Label(getQuantity(), skin)).pad(2f).left().top().row();
        stats.add(new Label(getLastPurchased(), skin)).pad(2f).left().top().row();

        widget.add(stats);

        widget.debug();
        stats.debug();
    }

    public final void setDate(Date date) {
        this.lastPurchased = date;
    }

    public final String getLastPurchased() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return "Last Purchased: "+dateFormat.format(lastPurchased);
    }

    public final String getId() {
        String sID = "Name: ";
        sID += id;
        return sID;
    }

    public final Label getDescription() {
        return description;
    }

    public final Image getImage() {
        return image;
    }

    public final Color getColor() {
        return color;
    }

    public final String getQuantity() {
        String qty = "";
        qty += quantity;
        return "Quantity: "+qty;
    }

    public final void incrementQuantity(int i) {
        quantity += i;
    }

    public static void setDimensions(float w, float h) {
        widgetWidth = w;
        widgetHeight = h;
    }
}
