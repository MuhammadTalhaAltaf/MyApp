package com.example.user.myapplication;

public class ChefItem  {

    String itemId;
    String itemName;
    String itemPrice;
    String itemCat;
    String itemQty;

    public ChefItem(){

    }

    public ChefItem(String itemId, String itemName, String itemPrice, String itemQty, String ItemCat){

        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQty = itemQty;
        this.itemCat = ItemCat;
    }

    public String getItemId(){
        return itemId;
    }

    public String getItemName(){
        return itemName;
    }

    public String getItemPrice(){
        return itemPrice;
    }

    public String getItemQty(){
        return itemQty;
    }

    public String getItemCat() {return  itemCat; }
}
