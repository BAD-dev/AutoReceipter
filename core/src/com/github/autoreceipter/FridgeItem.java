package com.github.autoreceipter;

import com.badlogic.gdx.graphics.Color;
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
        widget.setWidth(widgetWidth);
        widget.setHeight(widgetHeight);
        widget.add(image).expandX().left();
        widget.add(new Label(getLastPurchased(), skin)).padLeft(20f).padBottom(20f).padTop(20f);
        widget.debug();
    }

    public final void setDate(Date date) {
        this.lastPurchased = date;
    }

    public final String getLastPurchased() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(lastPurchased);
    }

    public final int getId() {
        return id;
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

    public final int getQuantity() {
        return quantity;
    }

    public final void incrementQuantity() {
        quantity++;
    }

    public static void setDimensions(float w, float h) {
        widgetWidth = w;
        widgetHeight = h;
    }
}
