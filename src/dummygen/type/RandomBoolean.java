/**
 * 
 */
package dummygen.type;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * ランダムにtrue or falseの値を返す型
 *
 */
public class RandomBoolean implements TypeValue {
	
	private int persent = 50;
	private Random random = new Random();
	
	/**
	 * 確率50％で初期化します
	 */
	public RandomBoolean() {
		super();
	}
	
	/**
	 * @param persent 0以上99以下 10を指定した場合は10％の確率でtrueを返します
	 */
	public RandomBoolean(int persent) {
		if (persent < 0 || persent > 99) {
			throw new IllegalArgumentException("persent (persent < 0 || persent > 99) arg:" + persent);
		}
		this.persent = persent;
	}

	@Override
	public Object getValue() {
		int n = random.nextInt(100);
		return n < persent;
	}

	@Override
	public void eval(String arg) {
		if (StringUtils.isBlank(arg)) {
			return ;
		}
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
