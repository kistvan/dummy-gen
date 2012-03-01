/**
 * 
 */
package dummygen;

import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;

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
		System.out.println(tableName + " のインサート開始します");
		
		for (int i = 0; i < row; i++) {
			insert();
		}
		System.out.println(tableName + " " + row + "件インサートしました");
		
		mapper.clearCache();
	}
	
	public void insert() throws Exception {
		QueryRunner runner = new QueryRunner();
		StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " (" + mapper.getColumnString() + ")VALUES");
		sql.append("(" + mapper.getValuePlaceHolder() + ")");
		System.out.println(sql);
		runner.update(con, sql.toString(), mapper.getParams());
	}
}
