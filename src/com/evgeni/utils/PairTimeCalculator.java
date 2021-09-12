package com.evgeni.utils;

import com.evgeni.models.Pair;
import com.evgeni.models.Project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * A class, which calculates the days of two workers who have worked together.
 */
public class PairTimeCalculator {

    public static Date[] getPeriod(Pair employees) {

        Date dateFrom;
        Date dateTo;
        Date[] period = new Date[2];

        if (employees.getFirst().getDateFrom().before(employees.getSecond().getDateFrom())) {
            dateFrom = employees.getSecond().getDateFrom();
        } else {
            dateFrom = employees.getFirst().getDateFrom();
        }

        if (employees.getFirst().getDateTo().before(employees.getSecond().getDateTo())) {
            dateTo = employees.getFirst().getDateTo();
        } else {
            dateTo = employees.getSecond().getDateTo();
        }

        period[0] = dateFrom;
        period[1] = dateTo;

        return period;
    }

    /**
     * Calculates the days worked together
     *
     * @param employees Pair - двойка служители
     * @return long days
     */
    public static Long getDays(Pair employees) {

        List<Date[]> dates = new ArrayList<>();

        for (Map.Entry<Project, Date[]> project : employees.getProjects().entrySet()) {
            dates.add(project.getValue());
        }

        long days = 0;
        for (int date = 0; date < dates.size(); date++) {

            long periodInMilliseconds;

            if (date + 1 < dates.size()
                    && dates.get(date)[0].getTime() <= dates.get(date + 1)[1].getTime()
                    && dates.get(date + 1)[0].getTime() <= dates.get(date)[1].getTime()) {

                continue;
            } else {
                periodInMilliseconds = Math.abs(dates.get(date)[1].getTime() - dates.get(date)[0].getTime());
            }

            days += TimeUnit.DAYS.convert(periodInMilliseconds, TimeUnit.MILLISECONDS) + 1;
        }

        return days;
    }
}
