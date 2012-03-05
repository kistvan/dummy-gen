/**
 * 
 */
package dummygen.type;

import org.joda.time.DateTime;

/**
 * 現在日時
 *
 */
public class CurrentTimestamp implements TypeValue {
	
	@Override
	public Object getValue() {
		return new DateTime().toDate();
	}

	@Override
	public void eval(String arg) {
		
	}

}
