package br.com.devtools.service.template.lang.test.helpers;

import static java.lang.String.format;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.RegularExpressionValueMatcher;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.util.ResourceUtils;

public abstract class FileHelper {

  private static final String REGEX_IGNORE_DATETIME =
      "(19|20)\\d\\d-(0[1-9]|1[012])-([012]\\d|3[01])T([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d).*";
  private static final String REGEX_IGNORE_UUID =
      "^[0-9a-fA-F]{8}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{12}$";

  public static String getStringFromFile(String filePath) {
    try {
      return IOUtils.toString(
          new FileInputStream(ResourceUtils.getFile(format("classpath:%s", filePath))),
          StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new RuntimeException(format("Unable to parse file [%s] from classpath", filePath), e);
    }
  }

  public static void validateResponse(String expected, String actual) throws JSONException {
    JSONAssert.assertEquals(
        getStringFromFile(expected),
        actual,
        new CustomComparator(
            JSONCompareMode.STRICT,
            new Customization("id", new RegularExpressionValueMatcher<>(REGEX_IGNORE_UUID)),
            new Customization(
                "createdAt", new RegularExpressionValueMatcher<>(REGEX_IGNORE_DATETIME)),
            new Customization(
                "blockedAt", new RegularExpressionValueMatcher<>(REGEX_IGNORE_DATETIME)),
            new Customization(
                "timestamp", new RegularExpressionValueMatcher<>(REGEX_IGNORE_DATETIME)),
            new Customization(
                "*.createdAt", new RegularExpressionValueMatcher<>(REGEX_IGNORE_DATETIME)),
            new Customization(
                "*.blockedAt", new RegularExpressionValueMatcher<>(REGEX_IGNORE_DATETIME))));
  }
}
