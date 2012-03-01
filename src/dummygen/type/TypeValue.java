/**
 * 
 */
package dummygen.type;

/**
 * DBにインサートする値のタイプ
 *
 */
public interface TypeValue {
	
	/**
	 * 
	 * @return sql実行時に渡される値
	 */
	Object getValue();

}
