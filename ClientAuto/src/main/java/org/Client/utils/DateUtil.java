package org.Client.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Util для работы с временем
 */
public class DateUtil {

    private static final String DATE_FORMAT = "dd.MM.yyyy";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    /**
     * Преобразуем дату с LocalDate в String
     * @param date - дата
     * @return Строковое представление даты
     */
    public static String format(LocalDate date){
        if(date == null){
            return null;
        }
        return DATE_TIME_FORMATTER.format(date);
    }

    /**
     * Преобразуем строку с датой в LocalDate
     * @param dateString - дата
     * @return LocalDate даты
     */
    public static LocalDate parse(String dateString){
        try{
            return DATE_TIME_FORMATTER.parse(dateString, LocalDate::from);
        }catch(DateTimeParseException e){
            return null;
        }
    }

    /**
     * Полученние сегоднешней даты
     * @return Строка с сегоднешней датой
     */
    public static String getNowDateAsString(){
        return format(LocalDate.now());
    }
    /**
     * Полученние сегоднешней даты
     * @return LocalDate с сегоднешней датой
     */
    public static LocalDate getNowDateAsDate(){
        return LocalDate.now();
    }


    /**
     * Проверка на валидность строки
     * @param dateString - Строка с датой
     * @return
     */
    public static boolean isValid(String dateString){
        return DateUtil.parse(dateString) != null;
    }

}
