package com.github.autoreceipter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ArrayMap;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public static ArrayMap<String, Image> foods;

    public Table widget;
    public Table stats;

    public String name;
    public Label description;
    public double cost;
    public Image image;
    public int quantity;
    public Color color;
    public Date lastPurchased;
    public int daysSinceLastPurchase;
    public int totalQuantity;
    public Skin skin;

    public FridgeItem(Skin skin) {
        this.widget = new Table();
        this.stats = new Table();
        this.skin = skin;

        if(foods == null)
            //populateFoods();

        this.name = "";
        this.cost = 0.00;
        this.quantity = 0;
        this.lastPurchased = new Date();
        this.image = new Image(new TextureRegion(skin.getRegion("default-icon")));

        //populateFoods();
    }

    public FridgeItem(String name, double cost, int quantity, Color color, Skin skin) {
        this.widget = new Table();
        this.stats = new Table();

        if(foods == null)
            //populateFoods();

        this.name = name;
        this.description = new Label(name, skin);
        this.cost = cost;
        this.image = new Image(new TextureRegion(skin.getRegion("default-icon")));
        this.color = color;
        this.quantity = quantity;
        this.lastPurchased = new Date();
        this.skin = skin;

        //image.setColor(color);
        //image.addListener(new ImageClick(this));

        //populateFoods();
        setWidget();
    }

//    private void populateFoods() {
//        foods = new ArrayMap<String, Image>();
//        foods.put("apple", skin.get("appleRegion", Image.class));
//        foods.put("banana", skin.get("bananaRegion", Image.class));
//        foods.put("bread", skin.get("breadRegion", Image.class));
//        foods.put("burger", skin.get("burgerRegion", Image.class));
//        foods.put("candy", skin.get("candyRegion", Image.class));
//        foods.put("cheese", skin.get("cheeseRegion", Image.class));
//        foods.put("coffee", skin.get("coffeeRegion", Image.class));
//        foods.put("default", skin.get("defaultRegion", Image.class));
//        foods.put("drink", skin.get("drinkRegion", Image.class));
//        foods.put("fish", skin.get("fishRegion", Image.class));
//        foods.put("fruit", skin.get("fruitRegion", Image.class));
//        foods.put("icecream", skin.get("icecreamRegion", Image.class));
//        foods.put("meat", skin.get("meatRegion", Image.class));
//        foods.put("pizza", skin.get("pizzaRegion", Image.class));
//    }

    public final void setWidget() {
        //widget.setWidth(widgetWidth);
        //widget.setHeight(widgetHeight);
        widget.defaults();
        //widget.setBackground(new NinePatchDrawable(getNinePatch("background/background_trans.png")));
        widget.setWidth(widgetWidth);

        widget.add(image).size(155, 155).left();
        //stats.add(this.getImage()).left();
        stats.add(new Label(name, skin, "segoeui_bold")).pad(2f).left().top().row();
        stats.add(new Label("Amount in Fridge: "+getQuantity(), skin, "segoeui")).pad(2f).left().top().row();
        stats.add(new Label("Cost per item: "+getCostStr(), skin, "segoeui")).pad(2f).left().top().row();
        stats.add(new Label("Last purchased on: "+getLastPurchased(), skin, "segoeui")).pad(2f).left().top().row();

        widget.add(stats).center().width(400f).pad(20f);

        final ImageButton edit = new ImageButton(skin.get("editButtonStyle", ImageButton.ImageButtonStyle.class));
        widget.add(edit).padLeft(50f).right();
    }

    // Sets dimensions for the this widget
    public static void setDimensions(float w, float h) {
        widgetWidth = w;
        widgetHeight = h;
    }

    // Returns a string containing item name
    public final String getItemName() {
        return name;
    }

    public final void setItemName(String name) {
        this.name = name;
    }

    // Get the current quantity of this item
    public final String getQuantity() {
        String str = "";
        return str + quantity;
    }

    public final void setImage(Image i) {
        this.image = i;
    }

    public final void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public final void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    // Set the date of the last purchased item
    public final void setDate(Date date) {
        this.lastPurchased = date;
    }

    // Returns a string containing the date this item was last purchased
    public final String getLastPurchased() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(lastPurchased);
    }

    // Not sure what to do with description. Maybe used for picking an image
    public final Label getDescription() {
        return description;
    }

    // Returns the image used for this item
    public final Image getImage() {
        return this.image;
    }

    // Should be called whenever we scan in a new item
    public final void incrementQuantity(int i) {
        quantity += i;
    }

    public final Color getColor() {
        return color;
    }

    public final double getCost() {
        return cost;
    }

    public final String getCostStr() {
        return ""+NumberFormat.getCurrencyInstance().format(new BigDecimal(cost));
    }

    public final void setCost(double cost) {
        this.cost = cost;
    }

    public static ArrayMap<String, Image> getFoods() {
        return foods;
    }

    // Used for getting background image
    private NinePatch getNinePatch(String fName) {
        final Texture t = new Texture(Gdx.files.internal(fName));
        return new NinePatch(new TextureRegion(t, 1, 1, t.getWidth() - 2, t.getHeight() - 2), 10, 10, 10, 10);
    }
}
