package util;
//----------------------------------------------------------------------------------------------------------------------

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author :
 * @dateCreation :
 * @description :
 */
//----------------------------------------------------------------------------------------------------------------------
public class DateUtil
{
    private static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static String toString(Date date)
    {
        return df.format(date);
    }

    public static Date toDate(String sDate)
    {
        Date date = null;
        try
        {
            date = df.parse(sDate);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }

}
