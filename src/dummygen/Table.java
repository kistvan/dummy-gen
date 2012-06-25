/**
 * 
 */
package dummygen;

import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dummygen.type.CurrentTimestamp;
import dummygen.type.IncrementalUniqueString;
import dummygen.type.MaybeNull;
import dummygen.type.RandomBoolean;
import dummygen.type.TypeValue;

/**
 * table.
 *
 */
public class Table {
	
	public String tableName;
	public int row;
	public Connection con;
	public ColumnMapper mapper;
	
	private boolean showedSql = false;
	
	private static Logger logger = LoggerFactory.getLogger(Table.class);

	
	/**
	 * @param row 作成数
	 * @param con コネクション
	 */
	public Table(String tableName, int row, Connection con, ColumnMapper mapper) {
		super();
		this.tableName = tableName;
		this.row = row;
		this.con = con;
		this.mapper = mapper;
	}
	
	public void allInsert() throws Exception {
		logger.debug(tableName + " のインサート開始します");
		showedSql = false;
		
		int interval = 1;
		int loop = 1;
		BulkInsert bi = new BulkInsert(tableName, mapper);
		for (int i = 0; i < row; i++) {
			if (interval >= 10000) {
				logger.info((loop * interval) + "件処理しました");
				loop++;
				interval = 0;
			}
			bi.insert(con);
			interval++;
		}
		bi.flush(con);
		logger.debug(tableName + " " + row + "件インサートしました");
		showedSql = false;
		
		mapper.clearCache();
	}
	
	public void insert() throws Exception {
		QueryRunner runner = new QueryRunner();
		StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " (" + mapper.getColumnString() + ")VALUES");
		sql.append("(" + mapper.getValuePlaceHolder() + ")");
		if (!showedSql) {
			logger.debug(sql.toString());
		}
		runner.update(con, sql.toString(), mapper.getParams());
		showedSql = true;
	}
}
