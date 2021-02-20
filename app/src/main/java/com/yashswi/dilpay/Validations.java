package com.yashswi.dilpay;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {
    public static final Pattern mailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern numberPattern = Pattern.compile("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}", Pattern.CASE_INSENSITIVE);

    public static boolean mailValidate(String emailStr) {
        Matcher matcher = mailPattern.matcher(emailStr);
        return matcher.find();
    }

    public static boolean numberValidate(String mobileNumber) {
        Matcher matcher = numberPattern.matcher(mobileNumber);
        return matcher.find();
    }
}
