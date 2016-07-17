package com.monitoring.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by venisac
 */
public abstract class Metric {
    private final String name;
    private final Object fields;

    public String getHost() {
        return host;
    }

    private final String host;

    public Metric(String name, Object fields, String host) {
        this.name = name;
        this.fields = fields;
        this.host = host;
    }

    @JsonProperty(value = "metric")
    public String getName() {
        return name;
    }

    public Object getFields() {
        return fields;
    }

}
