package com.neiry.flightcatalog.common.util;

import java.time.LocalTime;

public final class TimeUtils {

    private TimeUtils() {
    }

    public static int calculateMinutesBetween(LocalTime arrivalTime, LocalTime departureTime) {
        int arrival = arrivalTime.getHour() * 60 + arrivalTime.getMinute();
        int departure = departureTime.getHour() * 60 + departureTime.getMinute();

        if (departure >= arrival) {
            return departure - arrival;
        }

        return (24 * 60 - arrival) + departure;
    }
}