package com.lemonado.smartmeet.core.data;

import java.time.LocalDateTime;

public interface TimeRange {

    Status status();

    LocalDateTime startTime();

    LocalDateTime endTime();

}
