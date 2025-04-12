package com.central.framework.dateutils;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@UtilityClass
public class DateUtils {

    public LocalDate getCurrentDate(){
        return LocalDate.now();
    }

    public String getDay(String date,String dateFormat){
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(dateFormat, Locale.ENGLISH));
        return localDate.getDayOfWeek().toString();
    }

    public int getMonth(String date,String dateFormat){
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(dateFormat, Locale.ENGLISH));
        return localDate.getDayOfMonth();
    }

    public String getMonthName(String date,String dateFormat){
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(dateFormat, Locale.ENGLISH));
        return localDate.getMonth().toString();
    }

    public int getYear(String date,String dateFormat){
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(dateFormat, Locale.ENGLISH));
        return localDate.getYear();
    }

    public LocalDate parseDate(String date,String dateFormat){
        return LocalDate.parse(date,DateTimeFormatter.ofPattern(dateFormat,Locale.ENGLISH));
    }

    public LocalDateTime parseDateTime(String date,String dateTimeFormat){
        return LocalDateTime.parse(date,DateTimeFormatter.ofPattern(dateTimeFormat,Locale.ENGLISH));
    }

    // Format LocalDate to String
    public static String formatDate(LocalDate date,String dateFormat) {
        return date.format(DateTimeFormatter.ofPattern(dateFormat,Locale.ENGLISH));
    }

    // Format LocalDateTime to String
    public static String formatDateTime(LocalDateTime dateTime,String dateTimeFormat) {
        return dateTime.format(DateTimeFormatter.ofPattern(dateTimeFormat));
    }

    // Add days to a given date
    public static LocalDate addDays(LocalDate date, int days) {
        return date.plusDays(days);
    }

    // Subtract days from a given date
    public static LocalDate subtractDays(LocalDate date, int days) {
        return date.minusDays(days);
    }
    public static LocalDate addMonths(LocalDate date, int months) {
        return date.plusMonths(months);
    }

    public static LocalDate subtractMonths(LocalDate date, int months) {
        return date.minusMonths(months);
    }

    public static LocalDate addYears(LocalDate date, int years) {
        return date.plusYears(years);
    }

    public static LocalDate subtractYears(LocalDate date, int years) {
        return date.minusYears(years);
    }


}
