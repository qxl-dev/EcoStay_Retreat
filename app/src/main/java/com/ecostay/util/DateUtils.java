package com.ecostay.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    // Format date to a readable format
    public static String formatDate(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
        return sdf.format(new Date(timestamp));
    }

    // Convert a string date to timestamp
    public static long stringToTimestamp(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
        try {
            Date date = sdf.parse(dateString);
            return date != null ? date.getTime() : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
