package com.example.higherorlower;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "compare";
    private static final Random RANDOM = new Random();
    Country[] earth;
    int score = 0;
    int bestScore = 0;
    CountryHandler cHandler;
    private Button btnHigher;
    private Button btnLower;
    private int prevCountryIndex;
    private int currentCountryIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set buttons
        btnHigher = findViewById(R.id.higher);
        btnLower = findViewById(R.id.lower);

        // Set country handler
        cHandler = new CountryHandler(this);
        cHandler.createEarth();
        earth = cHandler.getCountries();

        // Generate a random country
        currentCountryIndex = RANDOM.nextInt(cHandler.getNumberOfCountries());
        // Set the country details

        generatePrevCountry();
        generateRandomCourentCountry();

        btnHigher.setOnClickListener(v -> {
            try {
                compareCountryPopulation(true);
            } catch (Exception e) {
                Log.e(TAG, "error calculating answer", e);  // Log the error for debugging
            }
        });

        btnLower.setOnClickListener(v -> {
            try {
                compareCountryPopulation(false);
            } catch (Exception e) {
                Log.e(TAG, "error calculating answer", e);  // Log the error for debugging
            }
        });
    }

    private void compareCountryPopulation(boolean userChoice) {
        // Correct
        if ((earth[prevCountryIndex].getPopulation() < earth[currentCountryIndex].getPopulation()) == userChoice ||
                earth[prevCountryIndex].getPopulation() == earth[currentCountryIndex].getPopulation()) {
            score++;
            if (score > bestScore) {
                bestScore = score;
            }
        }
        // Wrong
        else {
            score = 0;
        }

        generatePrevCountry();
        generateRandomCourentCountry();

        TextView tvCurrentScore = (TextView) findViewById(R.id.ScoreText);
        tvCurrentScore.setText("Score: " + score);
    }

    private void generateRandomCourentCountry() {
        currentCountryIndex = RANDOM.nextInt(cHandler.getNumberOfCountries());
        TextView tvCurrentCountry = (TextView) findViewById(R.id.CountryCurrent);
        if (currentCountryIndex == prevCountryIndex) {
            currentCountryIndex = (currentCountryIndex + 1) % cHandler.getNumberOfCountries();
        }
        tvCurrentCountry.setText("Country name: " + earth[currentCountryIndex].getName());
    }

    private void generatePrevCountry() {
        prevCountryIndex = currentCountryIndex;

        TextView tvPrevCountryName = (TextView) findViewById(R.id.CountryPrev);
        tvPrevCountryName.setText("Country name: " + earth[prevCountryIndex].getName());
        TextView tvPrevCountryPopulation = (TextView) findViewById(R.id.PrevPopulation);
        DecimalFormat formatter = new DecimalFormat("#,###");
        tvPrevCountryPopulation.setText("" + formatter.format(earth[prevCountryIndex].getPopulation()));
    }

}