package com.lemonado.smartmeet.core.calculator;

import com.lemonado.smartmeet.core.calculator.data.*;
import com.lemonado.smartmeet.core.data.CommonRange;
import com.lemonado.smartmeet.core.data.TimeLine;
import com.lemonado.smartmeet.core.data.UserStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MeetTimeCalculator {

    public TimeReportData calculate(List<TimeLine> timeLines) {
        var timePoints = toTimePoints(timeLines);

        var report = calculateReport(timePoints);
        var timeRange = calculateRange(timePoints);

        return new TimeReportData(timeRange, report);
    }

    private TimeRangeData calculateRange(List<TimePoint> timePoints) {
        return new TimeRangeData(
                timePoints.get(0).time(),
                timePoints.get(timePoints.size() - 1).time(),
                null);
    }

    private List<CommonRange> calculateReport(List<TimePoint> timePoints) {
        var report = new ArrayList<ExtendedCommonRangeData>();
        var currentRange = new ExtendedCommonRangeData();
        var touched = new ArrayList<ExtendedTimeRangeData>();
        var iterator = timePoints.listIterator();

        var nextPoint = iterator.next();
        update(touched, nextPoint);
        currentRange = Mapper.updateCommonRange(currentRange, touched, nextPoint.time());
        while (iterator.hasNext()) {
            var endPoint = iterator.next();

            currentRange = Mapper.updateCommonRange(currentRange, touched, endPoint.time());
            report.add(currentRange);
            update(touched, endPoint);

        }
        return report.stream()
                .map(data -> (CommonRange) new CommonRangeData(
                        data.startTime(),
                        data.endTime(),
                        data.touchedTimeRanged()
                                .stream()
                                .map(data1 -> (UserStatus) new UserStatusData(data1.user(), data1.status()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    private void update(List<ExtendedTimeRangeData> touched, TimePoint point) {
        if (point.type() == PointType.END_POINT)
            touched.removeAll(point.data());
        else if (point.type() == PointType.START_POINT)
            touched.addAll(point.data());
    }

    private List<TimePoint> toTimePoints(List<TimeLine> timeLines) {
        return timeLines.stream()
                .flatMap(Mapper::toTimePoint)
                .collect(Collectors.groupingBy(ExtendedTimeRangeData::range))
                .entrySet()
                .stream()
                .flatMap(entry -> Mapper.toTimePoint(entry.getKey(), entry.getValue()))
                .sorted()
                .collect(Collectors.toList());
    }

}
