/**
 * 
 */
package dummygen.type;

import org.apache.commons.lang3.StringUtils;

/**
 * 引数に渡された文字列をインサートします。引数がおかしい場合は空文字
 *
 */
public class Text implements TypeValue {
	
	private String text = "";

	@Override
	public Object getValue() {
		return text;
	}

	/* (non-Javadoc)
	 * @see dummygen.type.TypeValue#eval(java.lang.String)
	 */
	@Override
	public void eval(String arg) {
		if (StringUtils.isNotBlank(arg)) {
			this.text = arg;
		}
	}

	@Override
	public boolean isCacheValue() {
		return false;
	}

}
