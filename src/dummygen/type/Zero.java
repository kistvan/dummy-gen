/**
 * 
 */
package dummygen.type;

/**
 * Zero.
 *
 */
public class Zero implements TypeValue {

	/* (non-Javadoc)
	 * @see dummygen.type.TypeValue#getValue()
	 */
	@Override
	public Object getValue() {
		return 0;
	}

	@Override
	public void eval(String arg) {
		
	}

	@Override
	public boolean isCacheValue() {
		return true;
	}

}
