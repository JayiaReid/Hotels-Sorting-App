/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel;

/**
 *
 * @author jayia
 */
import java.util.*;

public class Hotel implements Comparable<Hotel> {
    private String name;
    private int rating;
    private double price;

    public Hotel(String name, int rating, double price) {
        this.name = name;
        this.rating = rating;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getStarRating() {
        return rating;
    }

    public double getPricePerNight() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s (%d stars): $%.2f", name, rating, price);
    }

    @Override
    public int compareTo(Hotel other) {
//        logic: if they are equal sort compare the names
        if (this.rating != other.rating) {
            return Integer.compare(other.rating, this.rating);
        }
        return this.name.compareTo(other.name); 
    }
}

