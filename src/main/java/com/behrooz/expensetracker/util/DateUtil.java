package com.behrooz.expensetracker.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Pattern;

public class DateUtil {
  public static final String YYYY_MM_DD = "yyyy-MM-dd";
  public static final String HH_MM_SS = "HH:mm:ss";

  public static final String DATE_REGEX_PATTERN =
      "^(?:(?:19|20)\\d\\d)-(?:0[1-9]|1[0-2])-(?:0[1-9]|[12][0-9]|3[01])$";

  public static final String TIME_REGEX_PATTERN = "^(?:[01]\\d|2[0-3]):(?:[0-5]\\d):(?:[0-5]\\d)$";
  private static final Pattern DATE_PATTERN = Pattern.compile(DATE_REGEX_PATTERN);
  private static final Pattern TIME_PATTERN = Pattern.compile(TIME_REGEX_PATTERN);

  public static final String DATE_TIME_PATTERN = YYYY_MM_DD.concat(" ").concat(HH_MM_SS);
  public static final DateTimeFormatter DATE_TIME_FORMATTER =
      DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
  public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(YYYY_MM_DD);
  public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(HH_MM_SS);

  public static String format(LocalDateTime date) {
    if (Objects.isNull(date)) return "";
    return DATE_TIME_FORMATTER.format(date);
  }

  public static String format(LocalDate date) {
    if (Objects.isNull(date)) return "";
    return DATE_FORMATTER.format(date);
  }

  public static LocalDate getLocalDate(String inputDate) {
    if (inputDate == null || !isValidateDate(inputDate)) return null;
    return LocalDate.parse(inputDate, DATE_FORMATTER);
  }

  public static LocalTime getTime(String inputTime) {
    if (inputTime == null || !isValidateTime(inputTime)) return null;
    return LocalTime.parse(inputTime, TIME_FORMATTER);
  }

  public static boolean isValidateDate(String inputDate) {
    return DATE_PATTERN.matcher(inputDate).matches();
  }

  public static boolean isValidateTime(String inputTime) {
    return TIME_PATTERN.matcher(inputTime).matches();
  }

  private DateUtil() {}
}
