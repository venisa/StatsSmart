package com.monitoring.models;

/**
 * Created by venisac
 */
public class Memory extends Metric {

    private final static String NAME = "memory";

    public Memory(MemoryFields fields) {
        super(NAME, fields);
    }

}
