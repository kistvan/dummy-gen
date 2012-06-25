/**
 * 
 */
package dummygen;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * main.
 *
 */
public class Main {
	
	private static Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] aras) throws Exception {
		//テーブル情報の初期化
		GeneratorBuilder builder = GeneratorBuilder.getInstance();
		Connection con = null;
		try {
			con = builder.createConnection();
			logger.info("コネクション接続OK");
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
