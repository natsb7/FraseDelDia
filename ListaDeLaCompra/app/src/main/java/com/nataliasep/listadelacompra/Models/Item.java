package com.nataliasep.listacompra.Models;

public class Item {

    private int id;
    private String name;
    private Category category;
    private int img;

    public Item() {
    }

    public Item(int id, String name, Category category, int img) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public int getImg() {
        return img;
    }

}
