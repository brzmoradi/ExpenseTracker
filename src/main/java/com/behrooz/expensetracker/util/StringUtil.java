package com.behrooz.expensetracker.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.RandomStringUtils;

public class StringUtil {
  public static final String USER_NAME_REGEX = "^[A-Za-z0-9._-]{3,}$";
  public static final String GET_CONSTRAINT_REGEX = "'([^']+)'[^']+'([^']+)'";

  public static final Pattern USER_NAME_REGEX_PATTERN =
      Pattern.compile(USER_NAME_REGEX, Pattern.CASE_INSENSITIVE);
  public static final Pattern GET_CONSTRAINT_REGEX_PATTERN =
      Pattern.compile(GET_CONSTRAINT_REGEX, Pattern.CASE_INSENSITIVE);

  public static String generatePassword(int len) {
    return RandomStringUtils.randomAlphanumeric(len);
  }

  public static String getConstraintName(String message) {
    Matcher matcher = GET_CONSTRAINT_REGEX_PATTERN.matcher(message);
    if (matcher.find()) return matcher.group(1);
    return null;
  }

  private StringUtil() {}
}
