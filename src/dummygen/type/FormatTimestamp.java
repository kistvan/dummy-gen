/**
 * 
 */
package dummygen.type;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatterBuilder;

/**
 * yyyy/MM/dd HH:mm:ss でフォーマットされた文字列から時間に変換するTypeValue
 *
 */
public class FormatTimestamp implements TypeValue {
	
	private Date date;

	@Override
	public Object getValue() {
		if (date == null) {
			throw null;
		}
		return date;
	}

	@Override
	public void eval(String arg) {
		try {
			date = DateUtils.parseDate(arg, "yyyy/MM/dd HH:mm:ss");
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

}
