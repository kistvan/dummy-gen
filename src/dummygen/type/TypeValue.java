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
	
	/**
	 * jsonに指定された引数の文字列を評価します
	 * @param arg jsonに指定された引数の文字列 指定されてなかった場合は空文字が渡される
	 */
	void eval(String arg);
	
	/**
	 * 
	 * @return キャッシュすべき値かどうか。インクリメントする値などの場合はtrue
	 */
	boolean isCacheValue();

}
