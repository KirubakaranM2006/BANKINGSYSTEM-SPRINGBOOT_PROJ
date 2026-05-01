package fxdemo;

import java.util.ArrayList;

public class InventoryManager {

    private static InventoryManager instance;
    private ArrayList<Product> list;

    private InventoryManager() {
        list = new ArrayList<>();
    }

    public static InventoryManager getInstance() {
        if (instance == null) {
            instance = new InventoryManager();
        }
        return instance;
    }

    public void addProduct(Product p) {
        list.add(p);
    }

    public void deleteProduct(int id) {
        list.removeIf(p -> p.id == id);
    }

    public Product findProduct(int id) {
        for (Product p : list) {
            if (p.id == id) return p;
        }
        return null;
    }
}