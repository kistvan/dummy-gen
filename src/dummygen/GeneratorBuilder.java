/**
 * 
 */
package dummygen;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

import dummygen.json.JsonConfig;
import dummygen.json.TableDef;

/**
 * GeneratorBuilder
 *
 */
public abstract class GeneratorBuilder {

	protected JsonConfig config;
	
	public GeneratorBuilder() throws IOException {
		Gson gson = new Gson();
		
		Reader reader = null;
		try {
			reader = new InputStreamReader(MysqlGeneratorBuilder.class.getResourceAsStream("/tables.json"));
			config = gson.fromJson(reader, JsonConfig.class);
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}
	
	public Connection createConnection() throws Exception {
		System.out.println(config.url + "に接続します。。");
		return DriverManager.getConnection(config.url, config.user, config.pass);
	}
	
	public void generate(Connection con) throws Exception {
		List<Table> tables = new ArrayList<Table>();
		for (TableDef def : config.tables) {
			System.out.println(def.name + "を登録します..");
			ColumnMapper mapper = new ColumnMapper();
			mapper.tableDef = def;
			Table t = new Table(def.name, def.row, con, mapper);
			t.allInsert();
		}
	}

	/**
	 * 
	 * trancateプロパティがtrueの場合はtablesプロパティの逆順にtrancateを実行
	 * @param con
	 * @throws Exception
	 */
	public abstract void truncate(Connection con) throws Exception;

	public static GeneratorBuilder getInstance() throws IOException {
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		if (!drivers.hasMoreElements()) {
			throw new RuntimeException("jdbc driver not found..");
		}
		Driver d = null;
		while(drivers.hasMoreElements()) {
			d = drivers.nextElement();
			if (StringUtils.contains(d.getClass().getName(), "mysql")) {
				return new MysqlGeneratorBuilder();
			}
		}
		throw new RuntimeException(d + " : not support driver.");
	}
	
}
