/**
 * 
 */
package dummygen.type;

import java.util.Random;

import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 指定した確率でnullを返します。デフォルトは50％です
 *
 */
public class MaybeNull implements ChainTypeValue {
	
	private Random random = new Random();
	private int persent = 50;
	private TypeValue typeValue;
	
	public MaybeNull() {
		
	}
	
	public MaybeNull(int persent) {
		if (persent < 0 || persent > 99) {
			throw new IllegalArgumentException("persent (persent < 0 || persent > 99) arg:" + persent);
		}
		this.persent = persent;
	}

	@Override
	public Object getValue() {
		int n = random.nextInt(100);
		if (n < persent) {
			return null;
		}
		return typeValue.getValue();
	}

	@Override
	public TypeValue with(TypeValue typeValue) {
		this.typeValue = typeValue;
		return this;
	}

	@Override
	public void eval(String arg) {
		int n = NumberUtils.toInt(arg);
		if (n < 0 || n > 100) {
			throw new IllegalArgumentException();
		}
		persent = n;
	}

	@Override
	public boolean isCacheValue() {
		return false;
	}

}
