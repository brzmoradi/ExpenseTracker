package com.behrooz.expensetracker.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class DateUtilTest {
  @ParameterizedTest
  @ValueSource(strings = {"2024.05.26", "2024_05_26", "2024/05/26", "2024- 1- 5"})
  public void should_Return_False_For_Invalid_DateFormat(String invalidDate) {
    Assertions.assertFalse(DateUtil.isValidateDate(invalidDate));
  }

  @ParameterizedTest
  @ValueSource(strings = {"2020-05-30", "2028-05-26", "2024-01-26"})
  public void should_Return_TRUE_For_Valid_DateFormat(String validDate) {
    Assertions.assertTrue(DateUtil.isValidateDate(validDate));
  }

  @ParameterizedTest
  @ValueSource(strings = {"12:00:00", "23:00:01", "23:59:59", "00:00:00", "00:00:01"})
  public void should_Return_TRUE_For_Valid_TimeFormat(String validTime) {
    Assertions.assertTrue(DateUtil.isValidateTime(validTime));
  }

  @ParameterizedTest
  @ValueSource(strings = {"24:00:00", "23-00-01", "0:0:0", "22:0:0", "2:0:01"})
  public void should_Return_FALSE_For_Valid_TimeFormat(String invalidTime) {
    Assertions.assertFalse(DateUtil.isValidateTime(invalidTime));
  }

  @Test
  public void test_local_date_formatter() {
    LocalDate actualDate = LocalDate.of(2024, 11, 1);
    String expectedDateFormattedResult = "2024-11-01";
    Assertions.assertEquals(DateUtil.format(actualDate), expectedDateFormattedResult);
  }

  @Test
  public void test_local_date_time_formatter() {
    LocalDateTime actualDateTime = LocalDateTime.of(2024, 11, 1, 23, 0, 3);
    String expectedDateFormattedResult = "2024-11-01 23:00:03";
    Assertions.assertEquals(DateUtil.format(actualDateTime), expectedDateFormattedResult);
  }

  @ParameterizedTest
  @MethodSource("timeProvider")
  public void time_input_valid_format(String timeString, LocalTime time) {
    Assertions.assertEquals(DateUtil.getTime(timeString), time);
  }

  private static Stream<Arguments> timeProvider() {
    return Stream.of(
        Arguments.of("12:00:00", LocalTime.of(12, 0, 0)),
        Arguments.of("13:29:00", LocalTime.of(13, 29, 0)),
        Arguments.of("23:00:00", LocalTime.of(23, 0, 0)),
        Arguments.of("09:01:33", LocalTime.of(9, 1, 33)),
        Arguments.of("15:00:36", LocalTime.of(15, 0, 36)),
        Arguments.of("00:01:01", LocalTime.of(0, 1, 1)),
        Arguments.of("23:59:59", LocalTime.of(23, 59, 59)));
  }
}
