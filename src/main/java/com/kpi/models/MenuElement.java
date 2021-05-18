package com.kpi.models;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.Objects;

public class MenuElement {
    private int elementId;
    private String name;
    private String description;
    private String ingredients;
    private double price;
    private byte[] image;
    private String base64Image;

    public MenuElement(){

    }

    public MenuElement(int elementId, String name, String description, String ingredients, double price, byte[] image) {
        this.elementId = elementId;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.price = price;
        this.image = image;
        this.base64Image = Base64.getEncoder().encodeToString(image);
    }

    public MenuElement(int elementId, String name, String description, String ingredients, double price) {
        this.elementId = elementId;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.price = price;
    }

    public MenuElement(String name, String description, String ingredients, double price) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.price = price;
    }

    public int getElementId() {
        return elementId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
        base64Image = Base64.getEncoder().encodeToString(image);
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuElement that = (MenuElement) o;
        return elementId == that.elementId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(elementId);
    }
}
