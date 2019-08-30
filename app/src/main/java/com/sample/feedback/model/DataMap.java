package com.sample.feedback.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataMap {

    @SerializedName("options")
    private ArrayList<String> options;

    public DataMap(ArrayList<String> options) {
        this.options = options;
    }

    public List<String> getOptions() {
        return options;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataMap dataMap = (DataMap) o;
        return Objects.equals(options, dataMap.options);
    }

    @Override
    public int hashCode() {
        return Objects.hash(options);
    }
}
