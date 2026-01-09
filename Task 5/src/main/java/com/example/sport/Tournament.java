package com.example.sport;

public class Tournament {
    private int id;
    private String name;
    private int year;
    private double prizePool;
    private int sportId;

    public Tournament() {}

    public Tournament(int id, String name, int year, double prizePool, int sportId) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.prizePool = prizePool;
        this.sportId = sportId;
    }

    // Геттери і Сеттери
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public double getPrizePool() { return prizePool; }
    public void setPrizePool(double prizePool) { this.prizePool = prizePool; }
    public int getSportId() { return sportId; }
    public void setSportId(int sportId) { this.sportId = sportId; }
}