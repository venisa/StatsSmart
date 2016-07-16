package com.monitoring.models;

/**
 * Created by venisac
 */
public class CPUFields {

    private final String cpu;
    private final double perUsr;
    private final double perNice;
    private final double perSys;
    private final double perIoWait;

    public CPUFields(
            String cpu,
            double perUsr,
            double perNice,
            double perSys,
            double perIoWait
    ) {
        this.cpu = cpu;
        this.perUsr = perUsr;
        this.perNice = perNice;
        this.perSys = perSys;
        this.perIoWait = perIoWait;
    }

    public String getCpu() {
        return cpu;
    }

    public double getPerUsr() {
        return perUsr;
    }

    public double getPerNice() {
        return perNice;
    }

    public double getPerSys() {
        return perSys;
    }

    public double getPerIoWait() {
        return perIoWait;
    }
}
