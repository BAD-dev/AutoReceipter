package com.github.autoreceipter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import java.util.ArrayList;
import java.util.*;


/**
 * Created by Julian on 4/18/2016.
 *
 * This is where FridgeItems are parsed from text file
 */
public class ItemParser {

    private AutoReceipter app;
    private static String str;

    public ItemParser(final AutoReceipter app) {
        this.app = app;
    }

    public ArrayList<FridgeItem> parse(String textToConvert) {
        ArrayList<FridgeItem> temp = new ArrayList<FridgeItem>();

        ArrayList<String> itemList = new ArrayList<String>();
        ArrayList<String> itemList2 = new ArrayList<String>();
        ArrayList<String> costList = new ArrayList<String>();
        ArrayList<String> costList2 = new ArrayList<String>();
        ArrayList<String> blackList = new ArrayList<String>();
        blackList.add("Orde");
        blackList.add("Order");
        blackList.add("Order ");
        blackList.add(" Orde");
        blackList.add(" Order");
        blackList.add(" Total");
        blackList.add(" Tota");
        blackList.add("Tota");
        blackList.add("Total");
        blackList.add("Total ");
        blackList.add("Sale");
        blackList.add("Sales");
        blackList.add("Sales ");
        blackList.add(" Sales");
        blackList.add(" Sale");
        blackList.add("Gran");
        blackList.add("Grand");
        blackList.add("Grand ");
        blackList.add(" Gran");

        String currentReceipt = textToConvert;

        int hyphenCounter =0;
        int i=0;
        for(i=0; i<currentReceipt.length(); i++) {
            if(currentReceipt.charAt(i) == '-')
            {
                hyphenCounter++;
            }

            if(hyphenCounter==2) {
                i+=5;
                break;
            }
        }
        currentReceipt = currentReceipt.substring(i);

        i=0;
        for(i=0; i<currentReceipt.length(); i++)
            if(currentReceipt.charAt(i) != ' ')
                break;

        currentReceipt = currentReceipt.substring(i);

        String item = "";
        String cost = "";
        boolean itemFound = false;
        boolean costFound = false;
        boolean FItem_TCost = false;
        int index=0;
        int spaceCounter=0;
        for(index=0; index<currentReceipt.length(); index++) {
            //System.out.printf("\ntest\n");
            if(blackList.contains(item) || blackList.contains(cost)) {
                //System.out.printf("\nEndingTest\n");
                break;
            }
            if(FItem_TCost == false)
            {
                if (itemFound == false && currentReceipt.charAt(index) != ' ')
                {
                    item += currentReceipt.charAt(index);
                    itemFound = true;
                    spaceCounter=0;
                }

                else if ((itemFound == true) && (currentReceipt.charAt(index) != ' ')) {
                    //System.out.printf("aaa%d\n", spaceCounter);
                    item += currentReceipt.charAt(index);
                }

                else if((itemFound == true) && (currentReceipt.charAt(index) == ' '))
                {
                    if(spaceCounter < 3)
                    {
                        //System.out.printf("%d\n", spaceCounter);
                        //System.out.printf("index: %d    char: %c\n", index, currentReceipt.charAt(index));

                        spaceCounter++;
                        item += currentReceipt.charAt(index);
                    }

                    else if (spaceCounter >= 3)
                    {
                        //System.out.printf("\ntest\n");
                        if(item.length()>4) {
                            itemList.add(item);
                            item = "";
                            itemFound = false;
                            FItem_TCost = true;
                        }
                        else
                        {
                            item ="";
                            itemFound = false;
                            FItem_TCost = false;
                            spaceCounter =0;
                        }
                    }
                }
            }

            else
            {
                if (costFound == false && currentReceipt.charAt(index) != ' ')
                {
                    cost += currentReceipt.charAt(index);
                    costFound = true;
                    spaceCounter = 0;
                }

                else if(costFound == true && currentReceipt.charAt(index) != ' ')
                    cost += currentReceipt.charAt(index);

                else if(costFound == true && currentReceipt.charAt(index) == ' ')
                {
                    if(spaceCounter < 2)
                    {
                        spaceCounter++;
                        cost += currentReceipt.charAt(index);
                    }

                    else if (spaceCounter >= 2)
                    {
                        int counter = 0;
                        for(counter=0; counter <cost.length(); counter++) {
                            if(cost.charAt(counter) == '.')
                                break;
                        }
                        if(counter+3 < cost.length())
                            cost = cost.substring(0, counter+3);
                        costList.add(cost);
                        cost = "";
                        costFound = false;
                        FItem_TCost = false;
                    }
                }
            }


        }

        int itemCounter=0;
        int itemIndex=0;
        int spaces=0;
        for(itemCounter=0; itemCounter<costList.size(); itemCounter++) {
            spaces=0;
            for(itemIndex=itemList.get(itemCounter).length()-1; itemIndex >=0; itemIndex--) {
                if(itemList.get(itemCounter).charAt(itemIndex) == ' ')
                    spaces++;
                else
                    break;
            }
            itemList2.add(itemList.get(itemCounter).substring(0, itemList.get(itemCounter).length()-spaces));
        }


        int costCounter=0;
        int costIndex=0;
        for(costCounter=0; costCounter<costList.size(); costCounter++) {
            for(costIndex=0; costIndex<costList.get(costCounter).length(); costIndex++) {
                if(costList.get(costCounter).charAt(costIndex) == '.' || costList.get(costCounter).charAt(costIndex) == ',' || costList.get(costCounter).charAt(costIndex) == ' ' )
                    break;
            }
            costList2.add(costList.get(costCounter).substring(0, costIndex) + "." + costList.get(costCounter).substring(costIndex+1, costIndex+3));
        }

        int x=0;
        for(x=0; x<itemList2.size(); x++) {
            FridgeItem test = new FridgeItem(app.skin);
            test.setItemName(itemList2.get(x));
            for(String s : itemList2)
                System.out.println(s);
            test.setCost(Double.parseDouble(costList2.get(x)));
            test.incrementQuantity(1);
            //cost is probably not a String, will need to parse in as double/float
            //set the cost with costList2.get(x);
            test.setWidget();
            temp.add(test);
        }

        return temp;
    }

    public FridgeItem parseLine(String lineToConvert) {
        FridgeItem temp = new FridgeItem(app.skin);



        return temp;
    }
}
