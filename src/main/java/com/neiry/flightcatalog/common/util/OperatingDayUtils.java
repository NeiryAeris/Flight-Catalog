package com.neiry.flightcatalog.common.util;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class OperatingDayUtils {

    private OperatingDayUtils() {
    }

    public static boolean hasOverlap(String days1, String days2) {
        return !intersect(days1, days2).isBlank();
    }

    public static String intersect(String days1, String days2) {
        Set<String> set1 = parse(days1);
        Set<String> set2 = parse(days2);

        set1.retainAll(set2);

        return set1.stream().collect(Collectors.joining(","));
    }

    private static Set<String> parse(String days) {
        if (days == null || days.isBlank()) {
            return new LinkedHashSet<>();
        }

        return Stream.of(days.split(","))
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}