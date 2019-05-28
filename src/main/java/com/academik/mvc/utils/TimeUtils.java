package com.academik.mvc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author esvux
 */
public class TimeUtils {
    
    public static Date getFromDDMMYYYY(String dateAsString) {
        if(dateAsString == null)
            return null;
        SimpleDateFormat formatterWithForwardSlash = new SimpleDateFormat("dd/MM/YYYY");
        try {
            return formatterWithForwardSlash.parse(dateAsString);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        SimpleDateFormat formatterWithDashes = new SimpleDateFormat("dd-MM-YYYY");
        try {
            return formatterWithDashes.parse(dateAsString);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static String parseToDDMMYYYY(Date date) {
        if(date == null)
            return null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YYYY");
        return formatter.format(date);
    }
    
}
