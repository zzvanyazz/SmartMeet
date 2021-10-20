package com.lemonado.smartmeet.core.calculator.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record ExtendedCommonRangeData(LocalDateTime startTime,
                                      LocalDateTime endTime,
                                      List<ExtendedTimeRangeData> touchedTimeRanged) {

    public ExtendedCommonRangeData() {
        this(null, null, new ArrayList<>());
    }
    public ExtendedCommonRangeData(LocalDateTime startTime, LocalDateTime endTime, List<ExtendedTimeRangeData> touchedTimeRanged) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.touchedTimeRanged = Collections.unmodifiableList(touchedTimeRanged);
    }

}
