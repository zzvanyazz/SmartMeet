package com.lemonado.smartmeet.core.calculator.data;

import com.lemonado.smartmeet.core.data.Status;
import com.lemonado.smartmeet.core.data.User;

public record ExtendedTimeRangeData(User user,
                                    Status status,
                                    SimpleTimeRange range) {
}
