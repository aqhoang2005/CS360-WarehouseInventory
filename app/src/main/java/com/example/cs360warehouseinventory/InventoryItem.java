package com.example.cs360warehouseinventory;

public class InventoryItem {
    public long id;
    public String name;
    public int qty;
    public String desc;
    public String tags;
    public String imageUri;

    public InventoryItem(long id, String name, int qty, String desc, String tags, String imageUri) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.desc = desc;
        this.tags = tags;
        this.imageUri = imageUri;
    }
}
