package displaying;

import Restaurant.restaurant;

import java.util.ArrayList;

public abstract class display {
    public abstract void displayRestaurnats_default();
    public abstract ArrayList<restaurant> displayRestaurnats_category(String Category);
    public abstract ArrayList<restaurant> displayRestaurnats_rating(float rating);
}
