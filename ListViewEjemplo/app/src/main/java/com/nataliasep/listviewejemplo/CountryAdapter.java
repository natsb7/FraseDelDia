package com.nataliasep.listviewejemplo;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

public class CountryAdapter extends ArrayAdapter<Country> {

    private List<Country> countryList;

    public CountryAdapter(Context context, List<Country> countryList) {
        super(context, 0, countryList);
        this.countryList = countryList;
    }
}
