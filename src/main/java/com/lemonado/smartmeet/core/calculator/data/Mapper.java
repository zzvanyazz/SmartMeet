package com.lemonado.smartmeet.core.calculator.data;

import com.lemonado.smartmeet.core.data.TimeLine;
import com.lemonado.smartmeet.core.data.UserStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Mapper {

    public static Stream<ExtendedTimeRangeData> toTimePoint(TimeLine timeLine) {
        var user = timeLine.user();
        return timeLine.ranges()
                .stream()
                .map(timeRange -> new ExtendedTimeRangeData(
                        user,
                        timeRange.status(),
                        new SimpleTimeRange(timeRange.startTime(), timeRange.endTime())));
    }

    public static Stream<TimePoint> toTimePoint(SimpleTimeRange range, List<ExtendedTimeRangeData> data) {
        var startTimePoint = new TimePoint(range.startTime(), data, PointType.START_POINT);
        var endTimePoint = new TimePoint(range.endTime(), data, PointType.END_POINT);
        return Stream.of(startTimePoint, endTimePoint);
    }

    public static <T> List<T> cloneList(List<T> list) {
        return new ArrayList<>(list);
    }

    public static ExtendedCommonRangeData updateCommonRange(ExtendedCommonRangeData commonRange,
                                                    List<ExtendedTimeRangeData> touchedTimeRanges,
                                                    LocalDateTime lastDateTime) {
        var startTime = commonRange.endTime();
        var users = cloneList(touchedTimeRanges);

        return new ExtendedCommonRangeData(startTime, lastDateTime, users);
    }
}
