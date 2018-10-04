package com.example.user.myapplication;

/**
 * Created by USER on 06-Jan-18.
 */

public class CartItems {
    public String itemName;
    public Integer itemPrice;
    public Integer itemQuantity;
    public String itemCat;
    public Integer itemPriority;


    public CartItems(){
super();
}

    public CartItems(String name,Integer pr,Integer quan, String cat, Integer priority){
        super();
        this.itemName=name;
        this.itemQuantity=quan;
        this.itemPrice=pr;
        this.itemCat=cat;
        this.itemPriority = priority;
    }
    public String toString() {
        return  this.itemName + " [$" + this.itemPrice + "] "+this.itemQuantity+" ["+ this.itemCat+"]" + this.itemPriority+"" ;
    }
}
