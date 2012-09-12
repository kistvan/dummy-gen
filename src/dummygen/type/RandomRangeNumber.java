/**
 * 
 */
package dummygen.type;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;

/**
 * 範囲の数値をランダムに返します
 *
 */
public class RandomRangeNumber implements TypeValue {
	
	private Integer from;
	private Integer to;

	@Override
	public Object getValue() {
		if (from == to) {
			return from;
		}
		Random r = new Random();
		int range = to - from + 1;
		int rand = r.nextInt(range);
		System.out.println("rand:" + rand + " from:" + from + "range:" + range);
		return rand + from;
	}

	@Override
	public void eval(String arg) {
		String[] fromTo = StringUtils.split(arg, ',');
		if (fromTo.length != 2) {
			throw new IllegalArgumentException();
		}
		from = Integer.parseInt(StringUtils.trim(fromTo[0]));
		to = Integer.parseInt(StringUtils.trim(fromTo[1]));
	}

	@Override
	public boolean isCacheValue() {
		return false;
	}

}
