package com.lemonado.smartmeet.core.calculator.data;

import java.util.Comparator;

public enum PointType {

    START_POINT(2),
    END_POINT(1);

    final int weight;

    PointType(int weight) {
        this.weight = weight;
    }
}
