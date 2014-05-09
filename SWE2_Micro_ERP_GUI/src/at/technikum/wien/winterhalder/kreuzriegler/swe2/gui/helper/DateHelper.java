/**
 * 
 */
package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author richie
 * 
 */
public class DateHelper {
	public static Date validateDate(String dateString) throws ParseException {
		// check birthDate
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		sdf.setLenient(false);
		return sdf.parse(dateString);
	}
}
