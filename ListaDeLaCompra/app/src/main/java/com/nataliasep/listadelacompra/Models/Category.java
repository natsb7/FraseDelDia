package com.nataliasep.listacompra.Models;

public class Category {

    private int id;

    private String name;

    private String img;

    public Category(int id, String name, String img){
        this.id = id;
        this.name = name;
        this.img = img;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getImg(){
        return img;
    }
}
