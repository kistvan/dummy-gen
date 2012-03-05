/**
 * 
 */
package dummygen;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.google.gson.Gson;

import dummygen.json.JsonConfig;
import dummygen.json.TableDef;

/**
 * GeneratorBuilder.
 *
 */
public class MysqlGeneratorBuilder extends GeneratorBuilder{
	
	public MysqlGeneratorBuilder() throws IOException {
		super();
	}

	@Override
	public void truncate(Connection con) throws Exception {
		if (!config.trancate) {
			return ;
		}
		QueryRunner q = new QueryRunner();
		q.update(con, "SET foreign_key_checks=0");
		for (int i = config.tables.length - 1; i >= 0; i--) {
			System.out.println("truncate " + config.tables[i].name);
			q.update(con, "truncate " + config.tables[i].name);
		}
		q.update(con, "SET foreign_key_checks=1");
		
	}

}
