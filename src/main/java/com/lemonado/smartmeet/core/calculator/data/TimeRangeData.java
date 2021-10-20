package com.lemonado.smartmeet.core.calculator.data;

import com.lemonado.smartmeet.core.data.Status;
import com.lemonado.smartmeet.core.data.TimeRange;

import java.time.LocalDateTime;

public record TimeRangeData(LocalDateTime startTime, LocalDateTime endTime, Status status) implements TimeRange {
}
