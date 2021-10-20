package com.lemonado.smartmeet.core.data;

import java.time.LocalDateTime;
import java.util.List;

public interface CommonRange {

    List<UserStatus> userStatuses();

    LocalDateTime startTime();

    LocalDateTime endTime();
}
