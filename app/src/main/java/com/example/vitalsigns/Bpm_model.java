package com.example.vitalsigns;

import java.util.concurrent.TimeUnit;

public class Bpm_model {
    public double bp_value;
    public long start_time;
    public long end_time;

    public Bpm_model(double bp_value, long start_time, long end_time){
        this.bp_value = bp_value;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public double getBp_value() {
        return bp_value;
    }

    public long getEnd_time() {
        return end_time;
    }

    public long getStart_time() {
        return start_time;
    }
}
