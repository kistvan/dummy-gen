/**
 * 
 */
package dummygen.type;

import java.util.ArrayList;
import java.util.Collections;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 内容がカンマ区切りされた中からランダムに選ばれた文字列
 *
 */
public class RandomSelectString implements TypeValue {
	
	private List<String> list = new ArrayList<String>();

	@Override
	public Object getValue() {
		Collections.shuffle(list);
		return list.get(0);
	}

	@Override
	public void eval(String arg) {
		String[] array = StringUtils.split(arg, ",");
		for (String s : array) {
			list.add(StringUtils.trim(s));
		}
	}

	@Override
	public boolean isCacheValue() {
		return false;
	}

}
