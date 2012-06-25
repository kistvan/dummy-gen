/**
 * 
 */
package dummygen;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * bulk insertするクラス
 *
 */
public class BulkInsert {
	
	private static Logger logger = LoggerFactory.getLogger(BulkInsert.class);
	
	private String tableName;
	private ColumnMapper mapper;
	
	private List<String> sqlValues = new ArrayList<String>();
	private String insertHeader;
	/**
	 * @param tableName
	 * @param mapper
	 */
	public BulkInsert(String tableName, ColumnMapper mapper) {
		super();
		this.tableName = tableName;
		this.mapper = mapper;
		
		insertHeader = "INSERT INTO " + tableName + " (" + mapper.getColumnString() + ")VALUES";
	}
	
	public void insert(Connection con) throws Exception {
		if (sqlValues.size() >= 1000) {
			QueryRunner runner = new QueryRunner();
			StringBuilder sql = new StringBuilder(insertHeader);
			sql.append(StringUtils.join(sqlValues, ","));
//			logger.debug(sql.toString());
			runner.update(con, sql.toString());
			sqlValues.clear();
		}
		sqlValues.add("(" + mapper.getParamsSql() + ")");
	}
	
	public void flush(Connection con) throws Exception {
		if (!sqlValues.isEmpty()) {
			QueryRunner runner = new QueryRunner();
			StringBuilder sql = new StringBuilder(insertHeader);
			sql.append(StringUtils.join(sqlValues, ","));
//			logger.debug(sql.toString());
			runner.update(con, sql.toString());
			sqlValues.clear();
		}
	}
	
}
