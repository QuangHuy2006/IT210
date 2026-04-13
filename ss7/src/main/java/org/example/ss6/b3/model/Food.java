package org.example.ss6.b3.model;

import org.springframework.web.multipart.MultipartFile;

public class Food {
    private String name;
    private double price;
    private MultipartFile file;

    public Food() {
    }

    public Food(String name, double price, MultipartFile file) {
        this.name = name;
        this.price = price;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
