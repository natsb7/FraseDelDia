package com.nataliasep.listviewejemplo;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class Country extends AppCompatActivity {

    String countryCode;
    String countryName;
    long countrPopulation;
    String countryCapital;
    String isoAlpha3;

    public Country(String countryCode, String countryName, long countrPopulation, String countryCapital, String isoAlpha3) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.countrPopulation = countrPopulation;
        this.countryCapital = countryCapital;
        this.isoAlpha3 = isoAlpha3;
    }
}
