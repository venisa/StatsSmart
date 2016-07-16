package com.monitoring.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by venisac
 */
public abstract class Metric {
    private final String name;
    private final Object fields;

    public Metric(String name, Object fields) {
        this.name = name;
        this.fields = fields;
    }

    @JsonProperty(value = "metric")
    public String getName() {
        return name;
    }

    public Object getFields() {
        return fields;
    }
}
