package com.lemonado.smartmeet;

import com.lemonado.smartmeet.core.calculator.MeetTimeCalculator;
import com.lemonado.smartmeet.core.data.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" dd ");
    static Map<User, List<TimeRange>> users = Map.of(
            UserData.of("1"), List.of(TimeRangeData.of("01/01/2021", "07/01/2021", Status.PRESENT)),
            UserData.of("2"), List.of(TimeRangeData.of("01/01/2021", "03/01/2021", Status.NOT_PRESENT)),
            UserData.of("9"), List.of(TimeRangeData.of("01/01/2021", "03/01/2021", Status.NOT_PRESENT)),
            UserData.of("3"), List.of(TimeRangeData.of("03/01/2021", "10/01/2021", Status.PRESENT)),
            UserData.of("4"), List.of(TimeRangeData.of("02/01/2021", "04/01/2021", Status.NOT_PRESENT)),
            UserData.of("5"), List.of(TimeRangeData.of("05/01/2021", "07/01/2021", Status.PRESENT)),
            UserData.of("6"), List.of(TimeRangeData.of("03/01/2021", "10/01/2021", Status.PRESENT)),
            UserData.of("7"), List.of(TimeRangeData.of("05/01/2021", "09/01/2021", Status.NOT_PRESENT)),
            UserData.of("8"), List.of(TimeRangeData.of("03/01/2021", "10/01/2021", Status.NOT_PRESENT)));


    public static void main(String[] args) {
        var calculator = new MeetTimeCalculator();
        var lines = users.entrySet()
                .stream()
                .map(userListEntry -> (TimeLine) new TimeLineData(userListEntry.getKey(), userListEntry.getValue()))
                .collect(Collectors.toList());

        var result = calculator.calculate(lines);
        print(result);
    }

    static void print(TimeReport report) {
        var users = report.report()
                .stream()
                .flatMap(commonRange -> commonRange.userStatuses().stream())
                .map(UserStatus::user)
                .distinct()
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));

        var ranges = report.report()
                .stream()
                .collect(Collectors.toMap(CommonRange::startTime, commonRange -> commonRange, (c, c2) -> c2));

        var sorterRanges = ranges.keySet().stream().sorted().collect(Collectors.toList());

        System.out.print(" ");
        for (var r : sorterRanges) {
            System.out.print(r.format(formatter));
        }
        System.out.println(report.report()
                .stream()
                .map(CommonRange::endTime)
                .sorted()
                .collect(Collectors.toList())
                .get(report.report().size() - 1)
                .format(formatter));

        for (var user : users) {
            System.out.print(user.name() + " ");
            for (var r : sorterRanges) {

                var common = ranges.get(r);
                System.out.print(common.userStatuses()
                        .stream()
                        .filter(userStatus -> userStatus.user().equals(user))
                        .findFirst()
                        .map(UserStatus::status)
                        .map(status -> switch (status) {
                            case PRESENT -> "++++";
                            case NOT_PRESENT -> "----";
                            default -> "####";
                        })
                        .orElse("####"));

            }
            System.out.println();
        }
    }

    private static void print1(CommonRange r, List<User> users) {

        var map = r.userStatuses().stream().collect(Collectors.toMap(UserStatus::user,
                userStatus -> {
                    if (userStatus.status() != null) {
                        return switch (userStatus.status()) {
                            case PRESENT -> "+";
                            case NOT_PRESENT -> "-";
                            default -> "#";
                        };
                    } else return "#";
                }, (s, s2) -> s));

        for (var user : users) {
            System.out.print(map.getOrDefault(user, "#"));
        }
    }

    record TimeLineData(User user, List<TimeRange> ranges) implements TimeLine {

    }

    record TimeRangeData(LocalDateTime startTime, LocalDateTime endTime, Status status) implements TimeRange {
        static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        public static TimeRangeData of(String startTime, String endTime, Status status) {
            return new TimeRangeData(
                    LocalDate.parse(startTime, formatter).atStartOfDay(),
                    LocalDate.parse(endTime, formatter).atStartOfDay(),
                    status);
        }
    }

    record UserData(String name, String phone) implements User {

        public static User of(String name) {
            return new UserData(name, null);
        }

    }

}
