package com.neiry.flightcatalog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.transfer")
public class TransferProperties {

    private int minMinutes;
    private int maxMinutes;

    public int getMinMinutes() {
        return minMinutes;
    }

    public void setMinMinutes(int minMinutes) {
        this.minMinutes = minMinutes;
    }

    public int getMaxMinutes() {
        return maxMinutes;
    }

    public void setMaxMinutes(int maxMinutes) {
        this.maxMinutes = maxMinutes;
    }
}