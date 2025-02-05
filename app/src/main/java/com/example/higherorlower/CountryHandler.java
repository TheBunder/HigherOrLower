package com.example.higherorlower;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CountryHandler {
    private final Context context;
    private int numberOfCountries = 0;
    private Country[] countries = new Country[2];

    public CountryHandler(Context context) {
        this.context = context;
    }

    public void createEarth() {

        // Open file and insert to the array all the countries.
        try {

            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("Countries.txt");

            // Open file and set reader
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String data;

                // Go over all the file and insert the data
                while ((data = reader.readLine()) != null) {
                    // Enter countries to the array
                    insertCountry(new Country(data.split("---")[0], Integer.parseInt(data.split("---")[1])));

                }
                // Close file
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }

    }

    public void insertCountry(Country c) {
        if (countries.length - 1 == numberOfCountries) {
            Country[] temp = new Country[countries.length * 2];
            copyArrayToArray(temp, countries);
            countries = temp;
        }

        countries[numberOfCountries] = c;
        numberOfCountries++;
    }

    private void copyArrayToArray(Country[] to, Country[] from) {
        System.arraycopy(from, 0, to, 0, from.length);
    }

    public Country[] getCountries() {
        return countries;
    }

    public int getNumberOfCountries() {
        return numberOfCountries;
    }
}

