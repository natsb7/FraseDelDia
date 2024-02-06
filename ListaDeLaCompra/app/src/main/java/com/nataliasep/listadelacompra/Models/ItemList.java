package com.nataliasep.listacompra.Models;

public class ItemList {
    private Item item;
    private boolean checked;

    public ItemList() {
    }

    public ItemList(Item item, boolean checked) {
        this.item = item;
        this.checked = checked;
    }


    public Item getItem() {
        return item;
    }

    public boolean getChecked() {
        return checked;
    }


    public void setItem(Item item) {
        this.item = item;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

}
