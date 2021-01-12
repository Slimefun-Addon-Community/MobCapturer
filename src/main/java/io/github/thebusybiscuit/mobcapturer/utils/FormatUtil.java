package io.github.thebusybiscuit.mobcapturer.utils;

import java.lang.StringBuilder;

public class FormatUtil{
  public static String format(String string) {
    string = string.toLowerCase();
    StringBuilder builder = new StringBuilder();
    int i = 0;
    for (String s: string.split("_")) {
      if (i == 0) builder.append(Character.toUpperCase(s.charAt(0)) + s.substring(1));
      else builder.append(" " + Character.toUpperCase(s.charAt(0)) + s.substring(1));
      i++;
    }
    return builder.toString();
  }
}
