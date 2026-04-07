package org.example.ss2.Bai4;

public class Event {
    private int id;
    private String name;
    private String eventDate;   // Dạng String "yyyy-MM-dd"
    private double price;
    private int remainingTickets;
    // Constructor + Getter/Setter đầy đủ

    public Event(int id, String name, String eventDate, double price, int remainingTickets) {
        this.id = id;
        this.name = name;
        this.eventDate = eventDate;
        this.price = price;
        this.remainingTickets = remainingTickets;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEventDate() {
        return eventDate;
    }

    public double getPrice() {
        return price;
    }

    public int getRemainingTickets() {
        return remainingTickets;
    }
}