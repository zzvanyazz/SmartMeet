package com.lemonado.smartmeet.core.data;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public interface User extends Comparable<User> {

    Comparator<User> comparator = Comparator.comparing(User::name).thenComparing(User::phone);

    String name();

    String phone();

    @Override
    default int compareTo(@NotNull User o) {
        return comparator.compare(this, o);
    }
}
