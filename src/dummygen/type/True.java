/**
 * 
 */
package dummygen.type;

/**
 * Trueを表すタイプ
 *
 */
public class True implements TypeValue {

	@Override
	public Object getValue() {
		return true;
	}

	@Override
	public void eval(String arg) {
	}

	@Override
	public boolean isCacheValue() {
		return false;
	}

}
