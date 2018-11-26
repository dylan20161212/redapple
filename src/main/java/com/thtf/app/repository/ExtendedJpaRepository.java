package com.thtf.app.repository;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.StringUtils;

public class ExtendedJpaRepository<T> extends SimpleJpaRepository<T, Long> implements BaseRepository<T> {
	private final Logger log = LoggerFactory.getLogger(ExtendedJpaRepository.class);
	private JpaEntityInformation<T, ?> ei;
	private EntityManager em;

	/**
	 * Creates a new {@link ExtendedJpaRepository} for the given
	 * {@link JpaEntityInformation} and {@link EntityManager}.
	 * 
	 * @param entityInformation
	 *            must not be {@literal null}.
	 * @param entityManager
	 *            must not be {@literal null}.
	 */
	public ExtendedJpaRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.setEi(entityInformation);
		this.setEm(entityManager);
	}

	public JpaEntityInformation<T, ?> getEi() {
		return ei;
	}

	public void setEi(JpaEntityInformation<T, ?> ei) {
		this.ei = ei;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see example.springdata.jpa.customall.BaseRepository#customMethod()
	 */
//	@Override
//	public long customMethod() {
//		return 0;
//	}

	@Override
	public List<T> findAll(Map<String, Object> filters) {
		int pagenum = Integer.parseInt(null == filters.get("pagenum") ? "0" : (String) filters.get("pagenum"));
		int pagesize = Integer.parseInt(null == filters.get("pagesize") ? "20" : (String) filters.get("pagesize"));
		int start = pagenum * pagesize;
		Map<String, Object> ret = getQueryStr(filters);
		@SuppressWarnings("unchecked")
		Map<String, Object> mapParameters = (Map<String, Object>) ret.get("parameters");
		// log.debug(queryStr);
		TypedQuery<T> query = em.createQuery(
				"SELECT x FROM " + (String) ret.get("queryStr") + (String) ret.get("queryOrderBy"), ei.getJavaType());
		for (Parameter<?> sqlParam : query.getParameters()) {
			String paramName = sqlParam.getName();
			// log.debug(paramName + mapParameters.get(paramName));
			query.setParameter(paramName, mapParameters.get(paramName));
			paramName = null;
		}
		if (start > -1) {
			query.setFirstResult(start);
		}
		if (pagesize > 0) {
			query.setMaxResults(pagesize);
		}

		return query.getResultList();
		// return null;
	}

	@Override
	public long getTotalRows(Map<String, Object> filters) {
		Map<String, Object> ret = getQueryStr(filters);
		@SuppressWarnings("unchecked")
		Map<String, Object> mapParameters = (Map<String, Object>) ret.get("parameters");

		// log.debug("queryStr:=" + queryStr);
		Query query = em.createQuery("SELECT COUNT(x.id) FROM " + (String) ret.get("queryStr"));
		for (Parameter<?> sqlParam : query.getParameters()) {
			String paramName = sqlParam.getName();
			// log.debug(paramName + mapParameters.get(paramName));
			query.setParameter(paramName, mapParameters.get(paramName));
			paramName = null;
		}
		Number cResults = (Number) query.getSingleResult();
		return cResults.longValue();
	}

	private Map<String, Object> getQueryStr(Map<String, Object> filters) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> mapParameters = new HashMap<String, Object>();
		// 暂时不考虑StringBuffer线程安全和性能问题，所以用String
		// String queryStr = "SELECT x FROM " + ei.getEntityName() + " x";
		StringBuilder builder = new StringBuilder();
		StringBuilder builderOrderBy = new StringBuilder();
		builder.append(ei.getEntityName());
		builder.append(" x ");
		int filterscount = 0;
		// filter data.
		if (filters.containsKey("filterscount")) {
			filterscount = Integer
					.parseInt(null == filters.get("filterscount") ? "0" : (String) filters.get("filterscount"));
			if (filterscount > 0) {
				StringBuilder whereStr = new StringBuilder(" WHERE (");
				String tmpdatafield = "";
				String tmpfilteroperator = "";
				String typeName = "";
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				// String value = [];
				for (int i = 0; i < filterscount; i++) {
					Object tmpfiltervalue = null;
					// get the filter's value.
					Object filtervalue = filters.get("filtervalue" + i);
					// get the filter's condition.
					String filtercondition = (String) filters.get("filtercondition" + i);
					// get the filter's column.
					String filterdatafield = (String) filters.get("filterdatafield" + i);
					try {
						// 防SQL注入，判断是否存在该字段
						Field fd = ei.getJavaType().getDeclaredField(filterdatafield);
						if (null != fd) {
							typeName = fd.getType().getName();
							log.debug("typeName={}", typeName);
							if ("long".equals(typeName) || "java.lang.Long".equals(typeName)) {
								if ("IN".equals(filtercondition) || "NI".equals(filtercondition)) {

								} else {
									tmpfiltervalue = Long.parseLong((String) filtervalue);
								}
							} else if ("java.math.BigDecimal".equals(typeName)) {
								tmpfiltervalue = new java.math.BigDecimal((String) filtervalue);
							} else if ("java.lang.String".equals(typeName)) {
								// val = (String) val;
							} else if ("java.util.Date".equals(typeName)) {
								try {
									tmpfiltervalue = formatter.parse((String) filtervalue);
								} catch (ParseException e) {
									// e.printStackTrace();
									log.warn("customQuery={}", e);
									tmpfiltervalue = new java.util.Date();
								}
							} else if ("java.time.Instant".equals(typeName)) {
								// tmpfiltervalue =
								// java.time.Instant.parse(filtervalue);
								try {
									tmpfiltervalue = formatter.parse((String) filtervalue).toInstant();
								} catch (ParseException e) {
									// e.printStackTrace();
									log.warn("customQuery={}", e);
									tmpfiltervalue = new java.util.Date().toInstant();
								}
							} else if ("java.time.ZonedDateTime".equals(typeName)) {
								// tmpfiltervalue =
								// java.time.Instant.parse(filtervalue);
								try {
									DateTimeFormatter beijingFormatter = DateTimeFormatter
											.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("Asia/Shanghai"));
									if (StringUtils.isEmpty(filtervalue)) {
										tmpfiltervalue = ZonedDateTime.now();
									} else {
										if (((String) filtervalue).length() == 10) {
											filtervalue = filtervalue + " 00:00:00";
										}
										tmpfiltervalue = ZonedDateTime.parse((String) filtervalue, beijingFormatter);
									}
									// tmpfiltervalue.withZoneSameInstant(ZoneId.of("UTC"));
								} catch (Exception e) {
									// e.printStackTrace();
									log.warn("customQuery={}", e);
									tmpfiltervalue = ZonedDateTime.now();
								}
							}
						}
						// else {
						// 说明不存在该字段跳出本次循环
						// continue;
						// }
					} catch (NoSuchFieldException e) {
						tmpfiltervalue = 0;
						// e.printStackTrace();
						log.warn("customQuery={}", e);
					} catch (SecurityException e) {
						tmpfiltervalue = 0;
						// e.printStackTrace();
						log.warn("customQuery={}", e);
					}
					// get the filter's operator.
					String filteroperator = (String) filters.get("filteroperator" + i);
					if ("".equals(tmpdatafield)) {
						tmpdatafield = filterdatafield;
					} else if (!tmpdatafield.equals(filterdatafield)) {
						whereStr.append(") AND (");
					} else if (tmpdatafield.equals(filterdatafield)) {
						if ("0".equals(tmpfilteroperator)) {
							whereStr.append(" AND ");
						} else {
							whereStr.append(" OR ");
						}
					}
					// build the "WHERE" clause depending on the filter's
					// condition, value and datafield.
					String condition = "";

					switch (filtercondition) {
					case "CONTAINS": {
						condition = " LIKE ";
						tmpfiltervalue = "%" + filtervalue + "%";
						break;
					}
					case "DOES_NOT_CONTAIN": {
						condition = " NOT LIKE ";
						tmpfiltervalue = "%" + filtervalue + "%";
						break;
					}
					case "EQUAL": {
						condition = " = ";
						if (tmpfiltervalue == null) {
							if (filtervalue.equals("true")) {
								tmpfiltervalue = true;
							} else if (filtervalue.equals("false")) {
								tmpfiltervalue = false;
							} else {
								tmpfiltervalue = filtervalue;
							}
						}
						break;
					}
					case "NOT_EQUAL": {
						condition = " <> ";
						if (tmpfiltervalue == null) {
							tmpfiltervalue = filtervalue;
						}
						break;
					}
					case "GREATER_THAN": {
						condition = " > ";
						if (tmpfiltervalue == null) {
							tmpfiltervalue = filtervalue;
						}
						break;
					}
					case "LESS_THAN": {
						condition = " < ";
						if (tmpfiltervalue == null) {
							tmpfiltervalue = filtervalue;
						}
						break;
					}
					case "GREATER_THAN_OR_EQUAL": {
						condition = " >= ";
						if (tmpfiltervalue == null) {
							tmpfiltervalue = filtervalue;
						}
						break;
					}
					case "LESS_THAN_OR_EQUAL": {
						condition = " <= ";
						if (tmpfiltervalue == null) {
							tmpfiltervalue = filtervalue;
						}
						break;
					}
					case "STARTS_WITH": {
						condition = " LIKE ";
						tmpfiltervalue = filtervalue + "%";
						break;
					}
					case "ENDS_WITH": {
						condition = " LIKE ";
						tmpfiltervalue = "%" + filtervalue;
						break;
					}
					case "NULL": {
						condition = " IS NULL ";
						tmpfiltervalue = null;
						break;
					}
					case "NOT_NULL": {
						condition = " IS NOT NULL ";
						tmpfiltervalue = null;
						break;
					}
					case "IN": {
						condition = " IN ";
						tmpfiltervalue = filtervalue;
						break;
					}
					case "NI": {
						condition = " NOT IN ";
						tmpfiltervalue = filtervalue;
						break;
					}
					}
					whereStr.append(" x.");
					whereStr.append(filterdatafield);
					whereStr.append(condition);
					if (null != tmpfiltervalue) {
						mapParameters.put(filterdatafield + i, tmpfiltervalue);
						whereStr.append(":");
						whereStr.append(filterdatafield);
						whereStr.append(i);
					}
					whereStr.append(" ");

					if (i == filterscount - 1) {
						whereStr.append(")");
					}
					tmpfilteroperator = filteroperator;
					tmpdatafield = filterdatafield;
				}
				// build the query.
				builder.append(whereStr);

			}
		}

		if (filters.containsKey("sortdatafield")) {
			String sortfield = (String) filters.get("sortdatafield");
			String sortorder = (String) filters.get("sortorder");
			if (!"".equals(sortorder)) {
				builderOrderBy.append(" ORDER BY ");
				builderOrderBy.append(sortfield);
				if ("desc".equals(sortorder)) {
					builderOrderBy.append(" DESC");
				} else if ("asc".equals(sortorder)) {
					builderOrderBy.append(" ASC");
				}
			}
		}

		ret.put("queryStr", builder.toString());
		ret.put("queryOrderBy", builderOrderBy.toString());
		ret.put("parameters", mapParameters);
		return ret;
	}

	private Map<String, Object> getNativeQueryStr(Map<String, Object> filters) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> mapParameters = new HashMap<String, Object>();
		//
		// String queryStr = "SELECT x FROM " + ei.getEntityName() + " x";
		StringBuilder builder = new StringBuilder();
		StringBuilder builderOrderBy = new StringBuilder();
		builder.append(ei.getJavaType().getAnnotation(javax.persistence.Table.class).name());
		builder.append(" x ");
		int filterscount = 0;
		// filter data.
		if (filters.containsKey("filterscount")) {
			filterscount = Integer
					.parseInt(null == filters.get("filterscount") ? "0" : (String) filters.get("filterscount"));
			if (filterscount > 0) {
				StringBuilder whereStr = new StringBuilder(" WHERE (");
				String tmpdatafield = "";
				String tmpfilteroperator = "";
				// Object tmpfiltervalue = null;
				String typeName = "";
				String tempColumName = "";
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				// String value = [];
				for (int i = 0; i < filterscount; i++) {
					Object tmpfiltervalue = null;
					// get the filter's value.
					Object filtervalue = filters.get("filtervalue" + i);
					// get the filter's condition.
					String filtercondition = (String) filters.get("filtercondition" + i);
					// get the filter's column.
					String filterdatafield = (String) filters.get("filterdatafield" + i);
					// String tempTableName = "";
					Field fd = null;
					try {
						// 防SQL注入，判断是否存在该字段
						fd = ei.getJavaType().getDeclaredField(filterdatafield);
						if (null != fd) {
							if (!"id".equalsIgnoreCase(filterdatafield)) {
								javax.persistence.Column myColumn = fd.getAnnotation(javax.persistence.Column.class);
								if (null != myColumn) {
									tempColumName = myColumn.name();
								} else {
									javax.persistence.JoinColumn myJColumn = fd
											.getAnnotation(javax.persistence.JoinColumn.class);
									javax.persistence.JoinTable myJTable = fd
											.getAnnotation(javax.persistence.JoinTable.class);
									if (null != myJTable) {
										// tempTableName = myJTable.name();
									}
									if (null != myJColumn) {
										tempColumName = myJColumn.name();
									}
								}
							} else {
								tempColumName = "id";
							}
							typeName = fd.getType().getName();
							log.debug("typeName={}", typeName);
							if ("long".equals(typeName) || "java.lang.Long".equals(typeName)) {
								if ("IN".equals(filtercondition) || "NI".equals(filtercondition)) {

								} else {
									tmpfiltervalue = Integer.parseInt((String) filtervalue);
								}
							} else if ("java.math.BigDecimal".equals(typeName)) {
								tmpfiltervalue = new java.math.BigDecimal((String) filtervalue);
							} else if ("java.lang.String".equals(typeName)) {
								// val = (String) val;
							} else if ("java.util.Date".equals(typeName)) {
								try {
									tmpfiltervalue = formatter.parse((String) filtervalue);
								} catch (ParseException e) {
									// e.printStackTrace();
									log.warn("customQuery={}", e);
									tmpfiltervalue = new java.util.Date();
								}
							} else if ("java.time.Instant".equals(typeName)) {
								// tmpfiltervalue =
								// java.time.Instant.parse(filtervalue);
								try {
									tmpfiltervalue = formatter.parse((String) filtervalue).toInstant();
								} catch (ParseException e) {
									// e.printStackTrace();
									log.warn("customQuery={}", e);
									tmpfiltervalue = new java.util.Date().toInstant();
								}
							} else if ("java.time.ZonedDateTime".equals(typeName)) {
								// tmpfiltervalue =
								// java.time.Instant.parse(filtervalue);
								try {
									DateTimeFormatter beijingFormatter = DateTimeFormatter
											.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("Asia/Shanghai"));
									if (StringUtils.isEmpty(filtervalue)) {
										tmpfiltervalue = ZonedDateTime.now();
									} else {
										if (((String) filtervalue).length() == 10) {
											filtervalue = filtervalue + " 00:00:00";
										}
										tmpfiltervalue = ZonedDateTime.parse((String) filtervalue, beijingFormatter);
									}
									// tmpfiltervalue.withZoneSameInstant(ZoneId.of("UTC"));
								} catch (Exception e) {
									// e.printStackTrace();
									log.warn("customQuery={}", e);
									tmpfiltervalue = ZonedDateTime.now();
								}
							}
						}
						// else {
						// 说明不存在该字段跳出本次循环
						// continue;
						// }
					} catch (NoSuchFieldException e) {
						// tmpfiltervalue = 0;
						// e.printStackTrace();
						// log.warn("customQuery={}", e);
					} catch (SecurityException e) {
						tmpfiltervalue = 0;
						// e.printStackTrace();
						log.warn("customQuery={}", e);
					}
					if (fd == null) {
						tempColumName = stringChange(filterdatafield);
						// typeName = stringChangeType(filterdatafield);
						// if ("long".equals(typeName) ||
						// "java.lang.Long".equals(typeName)) {
						// if ("IN".equals(filtercondition) ||
						// "NI".equals(filtercondition)) {
						//
						// } else {
						// tmpfiltervalue = Integer.parseInt((String)
						// filtervalue);
						// }
						// } else if ("java.math.BigDecimal".equals(typeName)) {
						// tmpfiltervalue = new java.math.BigDecimal((String)
						// filtervalue);
						// } else if ("java.lang.String".equals(typeName)) {
						// // val = (String) val;
						// } else if ("java.util.Date".equals(typeName)) {
						// try {
						// tmpfiltervalue = formatter.parse((String)
						// filtervalue);
						// } catch (ParseException e) {
						// e.printStackTrace();
						// }
						// } else if ("java.time.Instant".equals(typeName)) {
						// try {
						// tmpfiltervalue = formatter.parse((String)
						// filtervalue).toInstant();
						// } catch (ParseException e) {
						// e.printStackTrace();
						// tmpfiltervalue = new java.util.Date().toInstant();
						// }
						// }
					}
					// get the filter's operator.
					String filteroperator = (String) filters.get("filteroperator" + i);
					if ("".equals(tmpdatafield)) {
						tmpdatafield = filterdatafield;
					} else if (!tmpdatafield.equals(filterdatafield)) {
						whereStr.append(") AND (");
					} else if (tmpdatafield.equals(filterdatafield)) {
						if ("0".equals(tmpfilteroperator)) {
							whereStr.append(" AND ");
						} else {
							whereStr.append(" OR ");
						}
					}
					// build the "WHERE" clause depending on the filter's
					// condition, value and datafield.
					String condition = "";

					switch (filtercondition) {
					case "CONTAINS": {
						condition = " LIKE ";
						tmpfiltervalue = "%" + filtervalue + "%";
						break;
					}
					case "DOES_NOT_CONTAIN": {
						condition = " NOT LIKE ";
						tmpfiltervalue = "%" + filtervalue + "%";
						break;
					}
					case "EQUAL": {
						condition = " = ";
						if (tmpfiltervalue == null) {
							if (filtervalue.equals("true")) {
								tmpfiltervalue = true;
							} else if (filtervalue.equals("false")) {
								tmpfiltervalue = false;
							} else {
								tmpfiltervalue = filtervalue;
							}
						}
						break;
					}
					case "NOT_EQUAL": {
						condition = " <> ";
						if (tmpfiltervalue == null) {
							tmpfiltervalue = filtervalue;
						}
						break;
					}
					case "GREATER_THAN": {
						condition = " > ";
						if (tmpfiltervalue == null) {
							tmpfiltervalue = filtervalue;
						}
						break;
					}
					case "LESS_THAN": {
						condition = " < ";
						if (tmpfiltervalue == null) {
							tmpfiltervalue = filtervalue;
						}
						break;
					}
					case "GREATER_THAN_OR_EQUAL": {
						condition = " >= ";
						if (tmpfiltervalue == null) {
							tmpfiltervalue = filtervalue;
						}
						break;
					}
					case "LESS_THAN_OR_EQUAL": {
						condition = " <= ";
						if (tmpfiltervalue == null) {
							tmpfiltervalue = filtervalue;
						}
						break;
					}
					case "STARTS_WITH": {
						condition = " LIKE ";
						tmpfiltervalue = filtervalue + "%";
						break;
					}
					case "ENDS_WITH": {
						condition = " LIKE ";
						tmpfiltervalue = "%" + filtervalue;
						break;
					}
					case "NULL": {
						condition = " IS NULL ";
						tmpfiltervalue = null;
						break;
					}
					case "NOT_NULL": {
						condition = " IS NOT NULL ";
						tmpfiltervalue = null;
						break;
					}
					case "IN": {
						condition = " IN ";
						tmpfiltervalue = filtervalue;
						break;
					}
					case "NOT_IN": {
						condition = " NOT IN ";
						tmpfiltervalue = filtervalue;
						break;
					}
					}
					whereStr.append(" x.");
					whereStr.append(tempColumName);
					whereStr.append(condition);
					if (null != tmpfiltervalue) {
						mapParameters.put(tempColumName + i, tmpfiltervalue);
						whereStr.append(":");
						whereStr.append(tempColumName);
						whereStr.append(i);
					}
					whereStr.append(" ");

					if (i == filterscount - 1) {
						whereStr.append(")");
					}
					tmpfilteroperator = filteroperator;
					tmpdatafield = filterdatafield;
				}
				// build the query.
				builder.append(whereStr);

			}
		}

		if (filters.containsKey("sortdatafield")) {
			String sortfield = (String) filters.get("sortdatafield");
			sortfield = stringChange(sortfield);
			String sortorder = (String) filters.get("sortorder");
			if (!"".equals(sortorder)) {
				builderOrderBy.append(" ORDER BY ");
				builderOrderBy.append(sortfield);
				if ("desc".equals(sortorder)) {
					builderOrderBy.append(" DESC");
				} else if ("asc".equals(sortorder)) {
					builderOrderBy.append(" ASC");
				}
			}
		}

		ret.put("queryStr", builder.toString());
		ret.put("queryOrderBy", builderOrderBy.toString());
		ret.put("parameters", mapParameters);
		return ret;
	}

	@Override
	public long getRows(Map<String, Object> filters) {
		Map<String, Object> ret = getNativeQueryStr(filters);
		@SuppressWarnings("unchecked")
		Map<String, Object> mapParameters = (Map<String, Object>) ret.get("parameters");

		// log.debug("queryStr:=" + queryStr);
		Query query = em.createNativeQuery("SELECT COUNT(*) FROM " + (String) ret.get("queryStr"));
		for (Parameter<?> sqlParam : query.getParameters()) {
			String paramName = sqlParam.getName();
			// log.debug(paramName + mapParameters.get(paramName));
			query.setParameter(paramName, mapParameters.get(paramName));
			paramName = null;
		}
		Number cResults = (Number) query.getSingleResult();
		return cResults.longValue();
	}
    
	@Override
	public List<T> findAllCriteria(Map<String, Object> filters) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> q = cb.createQuery(ei.getJavaType());
		Root<T> root = q.from(ei.getJavaType());
		ParameterExpression<Integer> p = cb.parameter(Integer.class);
		q.where(cb.equal(root.get("organization"), p));
		q.select(root);
		TypedQuery<T> query = em.createQuery(q);
		query.setParameter(p, 1);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllNative(Map<String, Object> filters) {
		int pagenum = Integer.parseInt(null == filters.get("pagenum") ? "0" : (String) filters.get("pagenum"));
		int pagesize = Integer.parseInt(null == filters.get("pagesize") ? "20" : (String) filters.get("pagesize"));
		int start = pagenum * pagesize;
		Map<String, Object> ret = getNativeQueryStr(filters);
		Map<String, Object> mapParameters = (Map<String, Object>) ret.get("parameters");
		Query query = em.createNativeQuery(
				"SELECT * FROM " + (String) ret.get("queryStr") + (String) ret.get("queryOrderBy"), ei.getJavaType());
		for (Parameter<?> sqlParam : query.getParameters()) {
			String paramName = sqlParam.getName();
			query.setParameter(paramName, mapParameters.get(paramName));
			paramName = null;
		}
		if (start > -1) {
			query.setFirstResult(start);
		}
		if (pagesize > 0) {
			query.setMaxResults(pagesize);
		}

		return query.getResultList();
	}

	public String stringChange(String s) {
		if (s.equals("") || s == null)
			return "";
		StringBuilder sb = new StringBuilder();
		int len = s.length();
		char c;
		for (int i = 0; i < len; i++) {
			c = s.charAt(i);
			if (c >= 'A' && c <= 'Z') {
				if (i > 0)
					sb.append('_');
				c = (char) (c + 32);
			}
			sb.append(c);
		}
		return sb.toString();
	}

	public String stringChangeType(String s) {
		if (s.equals("") || s == null)
			return "";
		StringBuilder sb = new StringBuilder();
		int len = s.length();
		char c;

		for (int i = 0; i < len; i++) {
			c = s.charAt(i);
			if (i == 0) {
				if (c >= 'a' && c <= 'z') {
					c = (char) (s.charAt(0) - 32);
				}
			} else {
				if (c >= 'A' && c <= 'Z') {
					if (i > 0)
						break;
				}
			}
			sb.append(c);
		}
		return sb.toString();
	}

	
}
