package com.lemonado.smartmeet.core.calculator.data;

import com.lemonado.smartmeet.core.data.CommonRange;
import com.lemonado.smartmeet.core.data.TimeRange;
import com.lemonado.smartmeet.core.data.TimeReport;

import java.util.List;

public record TimeReportData(TimeRange totalTimeRange, List<CommonRange> report) implements TimeReport {
}
