package com.thtf.app.web.rest.util;

import java.util.Map;

public class FilterUtil {
	
	/**
	 * filter according to url parameter and parameter name must equals entity 's one field name.
	 * @param filters
	 * @param filterFieldName
	 * @param condition
	 * @param operator
	 */
	public  static void setFilter(Map<String, Object> filters,String filterFieldName,String condition,String operator) {
		String evaluateType = (String) filters.get(filterFieldName);
    	if(evaluateType != null ) {// 0 standfor model
    		setFilter(filters, filterFieldName, condition, operator, evaluateType);
    	}
	}

	public static void setFilter(Map<String, Object> filters, String filterFieldName, String condition,
			String operator, Object filterValue) {
		String fcount = filters.get("filterscount")== null?"0":(String)filters.get("filterscount");
		Integer count =  Integer.parseInt(fcount);
		filters.put(filterFieldName+"operator", operator);
		filters.put("filtervalue"+count, filterValue);
		filters.put("filtercondition"+count, condition);
		filters.put("filterdatafield"+count, filterFieldName);
		String opcode = "0";
		if(operator.equals("and")) {
			opcode="0";
		}
		if(operator.equals("or")) {
			opcode="1";
		}
		filters.put("filteroperator"+count, opcode);
		filters.put("filterscount",count+1+"");
	}
}
