/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
}
