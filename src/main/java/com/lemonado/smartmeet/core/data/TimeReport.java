package com.lemonado.smartmeet.core.data;

import java.util.List;

public interface TimeReport {

    TimeRange totalTimeRange();

    List<CommonRange> report();

}
