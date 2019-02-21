package com.tajawal.common.utilities;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtility {

    public static final String DATE_FORMAT_PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DATE_FORMAT_PATTERN_DD_MM_YYYY = "dd-MM-yyyy";
    public static final String DATE_FORMAT_PATTERN_DD_MMM_YY = "ddMMMyy";


    public static LocalDateTime getDateNow() {
        return LocalDateTime.now();
    }

    public static LocalDateTime getDateFromToday(long daysFromToday) {
        LocalDateTime date;

        if (daysFromToday > 0) {
            date = getDateNow().plusDays(daysFromToday);
        } else {
            daysFromToday = daysFromToday * -1;
            date = getDateNow().minusDays(daysFromToday);
        }

        return date;
    }

    public static String getFormattedDateFromToday(long daysFromToday,String pattern) {
        LocalDateTime date = getDateFromToday(daysFromToday);
        DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);

        return format.format(date);
    }
}
