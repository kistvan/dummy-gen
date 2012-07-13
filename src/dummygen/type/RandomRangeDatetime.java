/**
 * 
 */
package dummygen.type;

import java.util.Random;

import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 現在日時から指定された範囲までのランダムな日付時間を返すタイプ
 *
 */
public class RandomRangeDatetime implements TypeValue {
	
	private Integer rangeDays = 1;
	
	private static final Logger logger = LoggerFactory.getLogger(RandomRangeDatetime.class);

	@Override
	public Object getValue() {
		DateTime dt = new DateTime();
		DateTime ranged = new DateTime().plusDays(rangeDays);
		DateTime from = rangeDays < 0 ? ranged : dt;
		DateTime to = rangeDays < 0 ? dt : ranged;
		int sec = Seconds.secondsBetween(from, to).getSeconds();
		return from.plusSeconds(new Random().nextInt(sec)).toDate();
	}

	@Override
	public void eval(String arg) {
		int n = NumberUtils.toInt(arg);
		if (n == 0) {
			logger.warn("arg is not number. rangeDays use default value 1.");
			return ;
		}
		rangeDays = n;
	}

	@Override
	public boolean isCacheValue() {
		return false;
	}

}
