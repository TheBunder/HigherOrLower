package com.example.higherorlower;

public class Country {
    private String name;
    private long population;


    public Country(String name, long population) {
        this.name = name;
        this.population = population;
    }

    public long getPopulation() {
        return population;
    }

    public String getName() {
        return name;
    }
}
