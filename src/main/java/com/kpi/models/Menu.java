package com.kpi.models;

import java.util.ArrayList;

public class Menu {
    private ArrayList<MenuElement> menuElements;

    public Menu(){
        menuElements = new ArrayList<>();
    }

    public Menu(ArrayList<MenuElement> menuElements){
        this.menuElements = menuElements;
    }

    public ArrayList<MenuElement> getMenuElements() {
        return menuElements;
    }

    public void add(MenuElement menuElement){
        if (menuElement == null){
            throw new NullPointerException("Menu element cannot be null");
        }
        if (menuElements.contains(menuElement)){
            throw new IllegalArgumentException("This element is already in menu");
        }
        menuElements.add(menuElement);
    }

    public void remove(MenuElement menuElement){
        if (menuElement == null){
            throw new NullPointerException("Null value cannot be removed");
        }
        menuElements.remove(menuElement);
    }

}
