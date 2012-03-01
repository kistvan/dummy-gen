/**
 * 
 */
package dummygen.type;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

/**
 * 0詰めの固定長数値文字列としてユニークな文字列を出力する値タイプです。<br/>
 * デフォルトは100文字<br/>
 * widthを越える回数呼び出した場合の挙動は未定義です。
 *
 */
public class IncrementalUniqueString implements TypeValue {
	
	private int width;
	
	private BigDecimal count = BigDecimal.ZERO;
	
	public IncrementalUniqueString() {
		width = 100;
	}
	
	/**
	 * 
	 * @param width
	 */
	public IncrementalUniqueString(int width) {
		super();
		this.width = width;
	}

	/**
	 * 0詰めしたwidthで設定された幅の文字列。呼び出すたびにインクリメントされます。
	 */
	@Override
	public Object getValue() {
		count = count.add(BigDecimal.ONE);
		return StringUtils.leftPad(count.toString(), width, '0');
	}

}
