/**
 * 
 */
package dummygen.type;

import java.text.ParseException;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;

/**
 * yyyy/MM/dd HH:mm:ss でフォーマットされた文字列から時間に変換するTypeValue
 *
 */
public class FormatTimestamp implements TypeValue {
	
	private Date date;
	private int randRangeSec = 0;

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
			if (arg.contains(",")) {
				//ランダム
				String[] s = StringUtils.split(arg, ',');
				date = DateUtils.parseDate(StringUtils.trim(s[0]), "yyyy/MM/dd HH:mm:ss");
				randRangeSec = NumberUtils.toInt(s[1]);
				//0以外の場合はランダムに加算する
				if (randRangeSec == 0) {
					return ;
				}
				int n = new Random().nextInt(randRangeSec);
				date = new DateTime(date).plusSeconds(n).toDate();
			} else {
				date = DateUtils.parseDate(arg, "yyyy/MM/dd HH:mm:ss");
			}
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean isCacheValue() {
		return false;
	}

}
