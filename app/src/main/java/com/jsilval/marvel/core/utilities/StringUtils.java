package com.jsilval.marvel.core.utilities;

//import com.google.code.regexp.Pattern;

//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

import com.florianingerl.util.regex.Matcher;
import com.florianingerl.util.regex.Pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtils {

    public static boolean isValidString(String value) {
        if (isNullOrEmpty(value) || value.trim().length() < 3) {
            return false;
        }
        String regexp = "^(?<p>([-+]??(\\d+|\\(\\h*?(?'p')\\h*\\))(\\h*?[-+*\\/]\\h*?(?'p'))?))$";
        Pattern p = Pattern.compile(regexp);
        Matcher m = p.matcher(value.trim());
        m.setMode(Matcher.CAPTURE_TREE);
        return m.matches();
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    private static void check(String p, String s, boolean expected) {
        Matcher matcher = Pattern.compile(p).matcher(s);
        if (matcher.find() != expected)
            System.exit(1);
    }

    static void check(String regex, String input, String[] expected) {
        List<String> result = new ArrayList<String>();
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        while (m.find()) {
            result.add(m.group());
        }
        if (!Arrays.asList(expected).equals(result))
            System.exit(1);
    }
}
