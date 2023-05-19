package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private static SimpleDateFormat formatterMy = new SimpleDateFormat("MM/yyyy");

    public static String format(Date date) {
        return formatter.format(date);
    }
    public static String formatMM_yyyy(Date date) {
        return formatterMy.format(date);
    }

    public static Date parse(String strDate) {
        try {
            return formatter.parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public static Date parseMM_yyyy(String strDate) {
        try {
            return formatterMy.parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
