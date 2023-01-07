package tech.thegamedefault.notification.utility;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import software.amazon.awssdk.utils.StringUtils;

public class StringUtility {

  public static final String DOT_STRING = ".";

  public static final String COMMA_SEPARATOR = ",";
  public static final String EMPTY_STRING = "";

  private StringUtility() {
    throw new IllegalStateException("Utility class");
  }

  public static String concat(String delimiter, String... args) {
    return Stream.of(args)
        .filter(s -> s != null && !s.isEmpty())
        .collect(Collectors.joining(delimiter));
  }

  public static boolean isNotBlank(String inputString) {
    return StringUtils.isNotBlank(inputString);
  }

}
