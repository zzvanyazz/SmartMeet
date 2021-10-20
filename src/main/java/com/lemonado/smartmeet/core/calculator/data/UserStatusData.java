package com.lemonado.smartmeet.core.calculator.data;

import com.lemonado.smartmeet.core.data.Status;
import com.lemonado.smartmeet.core.data.User;
import com.lemonado.smartmeet.core.data.UserStatus;

public record UserStatusData(User user, Status status) implements UserStatus {
}
