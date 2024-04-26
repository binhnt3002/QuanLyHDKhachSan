package QLKS.domain;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * SeperateDate
 */
public class SeperateDate {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    

    //chuyển đổi định dạng từu String sang Date
    public static int getMonth(String dateString) {
        try {
            Date date = DATE_FORMAT.parse(dateString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.get(Calendar.MONTH) + 1; //Calendar.MONTH bắt đầu tính từ số 0
        } catch (Exception e) {
            // TODO: handle exception
            throw new IllegalArgumentException("Invalid date format. Expected yyyy-MM-dd, got: " + dateString);
        }
    }

    public static int getyear(String dateString){
        try {
            Date date = DATE_FORMAT.parse(dateString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.get(Calendar.YEAR) ; 
        } catch (Exception e) {
            // TODO: handle exception
            throw new IllegalArgumentException("Invalid date format. Expected yyyy-MM-dd, got: " + dateString);
        }
    }

    public static String formatDate(Date date) {
        return DATE_FORMAT.format(date);
    }

    public static Date parseDate(String dateString){
        try{
            return DATE_FORMAT.parse(dateString);
        }catch (ParseException e){
            throw new IllegalArgumentException("Invalid date format. Expected yyyy-MM-dd, got: " + dateString);
        }
    }
}