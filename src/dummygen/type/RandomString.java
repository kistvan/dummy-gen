/**
 * 
 */
package dummygen.type;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 内容がランダムな英数字の文字列
 *
 */
public class RandomString implements TypeValue {
	
	private int size = 10;

	@Override
	public Object getValue() {
		return RandomStringUtils.randomAlphanumeric(size);
	}

	@Override
	public void eval(String arg) {
		int n = NumberUtils.toInt(arg);
		if (n > 0) {
			size = n;
		}
	}

	@Override
	public boolean isCacheValue() {
		return false;
	}

}
