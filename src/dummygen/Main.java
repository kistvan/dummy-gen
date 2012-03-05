/**
 * 
 */
package dummygen;

import java.sql.Connection;

/**
 * main.
 *
 */
public class Main {

	public static void main(String[] aras) throws Exception {
		//テーブル情報の初期化
		GeneratorBuilder builder = GeneratorBuilder.getInstance();
		Connection con = null;
		try {
			con = builder.createConnection();
			System.out.println("コネクション接続OK");
			con.setAutoCommit(false);
			
			builder.truncate(con);
			
			builder.generate(con);
			
			con.commit();
		} catch (Exception ex) {
			if (con != null && !con.isClosed()) {
				System.out.println("ロールバックします。。");
				con.rollback();
			}
			throw ex;
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}
}
