package com.lemonado.smartmeet.core.calculator.data;

import com.lemonado.smartmeet.core.data.Status;
import com.lemonado.smartmeet.core.data.User;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record TimePoint(LocalDateTime time, List<ExtendedTimeRangeData> data, PointType type) implements Comparable<TimePoint> {

    @Override
    public int compareTo(@NotNull TimePoint o) {
        var result = time.compareTo(o.time);
        return result != 0? result : type.weight - o.type.weight;
    }
}
