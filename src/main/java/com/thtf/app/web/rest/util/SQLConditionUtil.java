package com.thtf.app.web.rest.util;

public class SQLConditionUtil {

	public static enum Strings {
		EMPTY("EMPTY"), NOT_EMPTY("NOT_EMPTY"), CONTAINS("CONTAINS"), CONTAINS_CASE_SENSITIVE(
				"CONTAINS_CASE_SENSITIVE"), DOES_NOT_CONTAIN("DOES_NOT_CONTAIN"), DOES_NOT_CONTAIN_CASE_SENSITIVE(
						"DOES_NOT_CONTAIN_CASE_SENSITIVE"), STARTS_WITH("STARTS_WITH"), STARTS_WITH_CASE_SENSITIVE(
								"STARTS_WITH_CASE_SENSITIVE"), ENDS_WITH("ENDS_WITH"), ENDS_WITH_CASE_SENSITIVE(
										"ENDS_WITH"), EQUAL("EQUAL"), EQUAL_CASE_SENSITIVE(
												"EQUAL_CASE_SENSITIVE"), NULL("NULL"), NOT_NULL("NOT_NULL");

		private String value;

		Strings(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

	}

	public static enum Numerics {
		EQUAL("EQUAL"), NOT_EQUAL("NOT_EQUAL"), LESS_THAN("LESS_THAN"), LESS_THAN_OR_EQUAL(
				"LESS_THAN_OR_EQUAL"), GREATER_THAN("GREATER_THAN"), GREATER_THAN_OR_EQUAL(
						"GREATER_THAN_OR_EQUAL"), NULL("NULL"), NOT_NULL("NOT_NULL");
		private String value;

		Numerics(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public static enum Dates {
		EQUAL("EQUAL"), NOT_EQUAL("NOT_EQUAL"), LESS_THAN("LESS_THAN"), LESS_THAN_OR_EQUAL(
				"LESS_THAN_OR_EQUAL"), GREATER_THAN("GREATER_THAN"), GREATER_THAN_OR_EQUAL(
						"GREATER_THAN_OR_EQUAL"), NULL("NULL"), NOT_NULL("NOT_NULL");
		private String value;

		Dates(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	

}
