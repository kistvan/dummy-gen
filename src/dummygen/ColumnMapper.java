/**
 * 
 */
package dummygen;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import dummygen.json.TableDef;
import dummygen.type.ChainTypeValue;
import dummygen.type.TypeValue;

/**
 * ColumnMapper.
 *
 */
public class ColumnMapper {
	
	public TableDef tableDef;
	
	private Map<String, TypeValue> typeValueChace = new HashMap<String, TypeValue>();

	/**
	 * 
	 * @return カンマ区切りのカラム名
	 */
	public String getColumnString() {
		return StringUtils.join(tableDef.columns.keySet(), ',');
	}

	/**
	 * 
	 * @return getColumnString()と対応するカンマ区切りのプレースホルダ
	 */
	public String getValuePlaceHolder() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < tableDef.columns.size(); i++) {
			list.add("?");
		}
		return StringUtils.join(list, ',');
	}

	/**
	 * 
	 * @return getValuePlaceHolder()と対応するパラメータの配列
	 * @throws Exception 
	 */
	public Object[] getParams() throws Exception {
		List<Object> result = new ArrayList<Object>();
		for (String key : tableDef.columns.keySet()) {
			String[] classNames = tableDef.columns.get(key);
			TypeValue typeValue = null;
			for (String clazz : classNames) {
				String[] params = StringUtils.split(TypeValue.class.getPackage().getName()  + "." + clazz, '(');
				String fqdn = params[0];
				String argText = "";
				if (params.length > 1) {
					argText = StringUtils.replace(params[1], ")", "");
				}
				TypeValue val = (TypeValue)Class.forName(fqdn).newInstance();
				val.eval(argText);
				if (val instanceof ChainTypeValue) {
					 typeValue = ((ChainTypeValue) val).with(typeValue);
				} else {
					 if (typeValueChace.containsKey(fqdn)) {
						 typeValue = typeValueChace.get(fqdn);
					 } else {
						 typeValue = val;
						 if (val.isCacheValue()) {
							 typeValueChace.put(fqdn, val);
						 }
					 }
				}
			}
			result.add(typeValue.getValue());
		}
		return result.toArray();
	}
	
	public String getParamsSql() throws Exception {
		Object[] values = getParams();
		StringBuilder sql = new StringBuilder();
		List<String> list = new ArrayList<String>();
		for (Object o : values) {
			if (o == null) {
				list.add("null");
			} else if (o instanceof String) {
				list.add("'" + o + "'");
			} else if(o instanceof Date) {
				list.add("'" + new DateTime(o).toString("yyyy/MM/dd HH:mm:ss") + "'");
			} else {
				list.add(o.toString());
			}
		}
		sql.append(StringUtils.join(list, ","));
		return sql.toString();
	}

	public void clearCache() {
		typeValueChace.clear();
	}

}
