package com.lemonado.smartmeet.core.calculator.data;

import com.lemonado.smartmeet.core.data.CommonRange;
import com.lemonado.smartmeet.core.data.UserStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record CommonRangeData(LocalDateTime startTime,
                              LocalDateTime endTime,
                              List<UserStatus> userStatuses) implements CommonRange {

    public CommonRangeData() {
        this(null, null, new ArrayList<>());
    }
    public CommonRangeData(LocalDateTime startTime, LocalDateTime endTime, List<UserStatus> userStatuses) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.userStatuses = Collections.unmodifiableList(userStatuses);
    }

}
