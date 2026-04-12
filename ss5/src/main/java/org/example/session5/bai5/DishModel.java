package org.example.session5.bai5;

public class DishModel {
    private Long id;
    private String name;
    private Double price;
    private boolean isAvailable;

    public DishModel() {
    }

    public DishModel(Long id, String name, Double price, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isAvailable = isAvailable;
    }


    public Long getId() { return id; }
    public String getName() { return name; }
    public Double getPrice() { return price; }
    public boolean isAvailable() { return isAvailable; }
}
