package com.muecode.leetcode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * HKMA Question,
 */
public class ShortestFileName {
  public static void main(String[] args) throws ParseException {
    System.out.println(solution("715K 2009-01-31 abc.zip~"));
  }

  public static String solution(String S) throws ParseException {
    // Implement your solution here
    String[] result = S.split("\\\n");
    List<String> strings =
        Arrays.stream(result).filter(e -> e.endsWith("~")).collect(Collectors.toList());
    // List<String> newStrings = strings.stream()..collect(Collectors.toList());

    for (String s : strings) {
      String[] ns = s.split("\\ ");
      Date dataDate = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(ns[1]);
      Date constantDate = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse("1990-01-31");
      if (dataDate.after(constantDate)) {
        System.out.println(dataDate);
      }
      // if (tradeDate.after()
      for (String s2 : ns) {
        System.out.println(s2);
      }
    }
    return "6";
  }
}
