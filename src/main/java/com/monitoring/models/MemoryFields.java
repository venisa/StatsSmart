package com.monitoring.models;

/**
 * Created by venisac
 */
public class MemoryFields {

    private final double total;
    private final double used;
    private final double free;

    public MemoryFields(double total, double used, double free) {
        this.total = total;
        this.used = used;
        this.free = free;
    }

    public double getTotal() {
        return total;
    }

    public double getUsed() {
        return used;
    }

    public double getFree() {
        return free;
    }
}
