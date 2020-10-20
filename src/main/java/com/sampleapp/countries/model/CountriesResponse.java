package com.sampleapp.countries.model;

import java.io.Serializable;
import java.util.Set;

public class CountriesResponse implements Serializable {
    int total;
    Set<Country> countries;

    public CountriesResponse(int total, Set<Country> countries) {
        this.total = total;
        this.countries = countries;
    }

    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
