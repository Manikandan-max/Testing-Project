package org.project.qa.utils;

import java.util.Date;

public class ElementUtils {
    public static final int IMPLICITWAIT_WAIT_TIME=10;
    public static final int PAGE_LOAD_TIME=5;
    public static String generateEmailWithTimeStamp() {
        Date date = new Date();
        String s = date.toString().replaceAll(" ", "_").replaceAll(":", "_").trim();
        return "testingpractice"+s+"@gmail.com";
    }
}
