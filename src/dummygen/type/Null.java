/**
 * 
 */
package dummygen.type;

/**
 * falseを表すタイプ
 *
 */
public class Null implements TypeValue {

	@Override
	public Object getValue() {
		return null;
	}

	@Override
	public void eval(String arg) {
	}

	@Override
	public boolean isCacheValue() {
		return false;
	}

}
