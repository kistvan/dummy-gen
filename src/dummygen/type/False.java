/**
 * 
 */
package dummygen.type;

/**
 * falseを表すタイプ
 *
 */
public class False implements TypeValue {

	@Override
	public Object getValue() {
		return false;
	}

	@Override
	public void eval(String arg) {
	}

	@Override
	public boolean isCacheValue() {
		return false;
	}

}
