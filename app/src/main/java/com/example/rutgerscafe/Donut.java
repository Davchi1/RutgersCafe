package com.example.rutgerscafe;
/**
 * An instance of this class is a donut menu item in an order, extends MenuItem class and implements Customizable
 * @author David Okechukwu, Sajan Thornhill
 */
public class Donut extends MenuItem {
    private String flavor;
    private double donutPrice;
    private int Quantity;

    /** Constructor for Donut
     * @param flavor
     * @param Quantity
     */
    public Donut(String flavor, int Quantity) {
        this.flavor = flavor;
        this.Quantity = Quantity;
        this.donutPrice = Constants.DONUT_PRICE;

    }

    /**
     * @return donutPrice
     */
    @Override
    public double itemPrice() {
        return donutPrice;
    }

    /**
     * @return Quantity
     */
    public int getQuantity() {
        return Quantity;
    }

    /**
     * @return flavor
     */
    public String getFlavor() {
        return flavor;
    }

    /** returns a string of donut type and flavor
     * @return
     */
    @Override
    public String toString() {
        return Quantity + " " + flavor + " " + "Donuts";
    }

}