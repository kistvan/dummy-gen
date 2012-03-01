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

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.Gson;

import dummygen.json.JsonConfig;
import dummygen.json.TableDef;

/**
 * GeneratorBuilder.
 *
 */
public class GeneratorBuilder {
	
	private JsonConfig config;
	public GeneratorBuilder() throws IOException {
		Gson gson = new Gson();
		
		Reader reader = null;
		try {
			reader = new InputStreamReader(GeneratorBuilder.class.getResourceAsStream("/tables.json"));
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
		//TODO trancateする
		
		List<Table> tables = new ArrayList<Table>();
		for (TableDef def : config.tables) {
			System.out.println(def.name + "を登録します..");
			ColumnMapper mapper = new ColumnMapper();
			mapper.tableDef = def;
			Table t = new Table(def.name, def.row, con, mapper);
			t.allInsert();
		}
	}

}
