package com.example.steven.joetzandroid.Domain;

/**
 * Created by Steven on 4/11/14.
 */
public class NavDrawerItem
{

    private String title;
    private int icon;

    public NavDrawerItem(){}
    public NavDrawerItem(String title, int icon){
        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
