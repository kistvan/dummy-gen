/**
 * 
 */
package dummygen.type;

import java.util.Random;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCacheValue() {
		return false;
	}

}
