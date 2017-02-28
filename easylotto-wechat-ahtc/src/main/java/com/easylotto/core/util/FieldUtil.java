package com.easylotto.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;


public class FieldUtil {

	public Field[] getFields(Class clazz) {
		return clazz.getSuperclass().getDeclaredFields();
	}

//	public static void main(String[] args) throws Exception {
//		EcpAdminAccessLog ecpAdminAccessLog = new EcpAdminAccessLog();
//		ecpAdminAccessLog.setInt_rec_id(456456l);
//		SQLFieldUtil.buildInsert(null, ecpAdminAccessLog, "");
//		System.out.println(ecpAdminAccessLog.getClass().getAnnotation(Table.class).name());
//		System.out.println(ecpAdminAccessLog.getClass().getSimpleName());
		
//		FieldUtil.buildBeanProperty(null, ecpAdminAccessLog);
		
//		Class obj = Class.forName(EcpAdminAccessLog.class.getName());
//		Field[] fields = obj.getSuperclass().getDeclaredFields();
//		for(Field f : fields){
//			System.out.println(f.getName());
//		}
//	}
	
	public static String getTableName(Object entity){
		return entity.getClass().getAnnotation(Table.class).name();
	}

	public static PreparedStatement buildInsert(Connection conn, Object entity) throws Exception {
		return buildInsert(conn, entity, "int_rec_id");
	}
	public static PreparedStatement buildInsert(Connection conn, Object entity, String idField) throws Exception {
		final StringBuffer sql = new StringBuffer("insert into ");
		sql.append(getTableName(entity)).append(" (");
		long i = System.currentTimeMillis();
		Class c = entity.getClass().getSuperclass();
		Field[] fields = c.getDeclaredFields();
		if(null == fields || fields.length == 0){
			fields = entity.getClass().getDeclaredFields();
		}
		Map<Integer, Object> fieldMap = new HashMap<Integer, Object>();
		StringBuffer field = new StringBuffer(idField);
		StringBuffer values = new StringBuffer(") Values (?");
		int index = 1;
		for (int j = 1; j < fields.length; j++) { // 遍历所有属性
			String name = fields[j].getName(); // 获取属性的名字
			if(idField.equals(name) || name.equals("serialVersionUID")) continue;
			String methodName = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
			Method m = entity.getClass().getMethod("get" + methodName);
			String type = m.getGenericReturnType().toString();
			if(type.contains("Set") || type.contains("List") ){
				System.out.println("----------------->>> methodName   " +  methodName  + "  type is :  " + type);
				continue;
			}
			if("dt_create_time".equals(name)){
				field.append(",").append(name);
				values.append(",now()");
				continue;
			}
			fieldMap.put(index + 1, name);
			field.append(",").append(name);
			values.append(",?");
			index++;
		}
		
		sql.append(field).append(values).append(")");
		System.out.println(sql.toString());
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		idField = idField.substring(0, 1).toUpperCase() + idField.substring(1); // 将属性的首字符大写，方便构造get，set方法
		Method method = entity.getClass().getMethod("get" + idField);
		Object obj = method.invoke(entity);
		if(null != obj)
			ps.setObject(1, obj);
		else
			ps.setObject(1, null);
		
		Set<Integer> keys = fieldMap.keySet();
		String name = "";
		for (Integer key : keys) {
			name = (String) fieldMap.get(key);
			if("dt_create_time".equals(name)) continue;
			name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
			Method m = entity.getClass().getMethod("get" + name);
			String type = m.getGenericReturnType().toString();
			if (type.contains("java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
				String value = (String) m.invoke(entity); // 调用getter方法获取属性值
				if(StringUtils.isEmpty(value)){
					ps.setObject(key, null);
				}else
					ps.setString(key, value);
			}else
			if (type.contains("java.lang.Integer")) {
				Integer value = (Integer) m.invoke(entity);
				if(null == value){
					ps.setObject(key, null);
				}else
					ps.setInt(key, value);
			}else
			if (type.contains("int")) {
				Integer value = (Integer) m.invoke(entity);
				if(null == value){
					ps.setObject(key, null);
				}else
					ps.setInt(key, value);
			}else
			if (type.contains("java.lang.Short")) {
				Short value = (Short) m.invoke(entity);
				if(null == value){
					ps.setObject(key, null);
				}else
					ps.setShort(key, value);

			}else
			if (type.contains("java.lang.Double")) {
				Double value = (Double) m.invoke(entity);
				if(null == value){
					ps.setObject(key, null);
				}else
					ps.setDouble(key, value);
			}else
			if (type.contains("double")) {
				Double value = (Double) m.invoke(entity);
				if(null == value){
					ps.setObject(key, null);
				}else
					ps.setDouble(key, value);
			}else
			if (type.contains("java.lang.Boolean")) {
				Boolean value = (Boolean) m.invoke(entity);
				if(null == value){
					ps.setObject(key, null);
				}else
					ps.setBoolean(key, value);
			}else
			if (type.contains("boolean")) {
				Boolean value = (Boolean) m.invoke(entity);
				if(null == value){
					ps.setObject(key, null);
				}else
					ps.setBoolean(key, value);
			}else
			if (type.contains("java.util.Date")) {
				Date value = (Date) m.invoke(entity);
				if (null != value) {
					ps.setTimestamp(key, new java.sql.Timestamp(value.getTime()));
				} else {
					ps.setObject(key, null);
				}
			}else 
			if (type.contains("java.lang.Long")) {
				Long value = (Long) m.invoke(entity);
				if(null == value){
					ps.setObject(key, null);
				}else
					ps.setLong(key, value);
			}
			else if (type.contains("long")) {
				Long value = (Long) m.invoke(entity);
				if(null == value){
					ps.setObject(key, null);
				}else
					ps.setLong(key, value);
			}
		}
		//
		System.out.println(System.currentTimeMillis() - i);
		return ps;
	}
	
	public static PreparedStatement buildInsert(Object entity, Connection conn) throws Exception {
		final StringBuffer sql = new StringBuffer("insert into ");
		sql.append(getTableName(entity)).append(" (");
		long i = System.currentTimeMillis();
		Class c = entity.getClass().getSuperclass();
		Field[] fields = c.getDeclaredFields();
		if(null == fields || fields.length == 0){
			fields = entity.getClass().getDeclaredFields();
		}
		Map<Integer, Object> fieldMap = new HashMap<Integer, Object>();
		StringBuffer field = new StringBuffer();
		StringBuffer values = new StringBuffer(") Values (");
		int index = 0;
		for (int j = 0; j < fields.length; j++) { // 遍历所有属性
			String name = fields[j].getName(); // 获取属性的名字
			if(name.equals("serialVersionUID"))continue;
			fieldMap.put(index + 1, name);
			field.append(name).append(",");
			values.append("?,");
			index++;
		}
		
		sql.append(field.substring(0, field.length() - 1)).append(values.substring(0, values.length() - 1)).append(")");
		System.out.println(sql.toString());
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		Set<Integer> keys = fieldMap.keySet();
		String name = "";
		for (Integer key : keys) {
			name = (String) fieldMap.get(key);
			name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
			Method m = entity.getClass().getMethod("get" + name);
			String type = m.getGenericReturnType().toString();
			if (type.contains("java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
				String value = (String) m.invoke(entity); // 调用getter方法获取属性值
				if(StringUtils.isEmpty(value)){
					ps.setObject(key, null);
				}else
					ps.setString(key, value);
			}else
			if (type.contains("java.lang.Integer")) {
				Integer value = (Integer) m.invoke(entity);
				if(null == value){
					ps.setObject(key, null);
				}else
					ps.setInt(key, value);
			}else
			if (type.contains("int")) {
				Integer value = (Integer) m.invoke(entity);
				if(null == value){
					ps.setObject(key, null);
				}else
					ps.setInt(key, value);
			}else
			if (type.contains("java.lang.Short")) {
				Short value = (Short) m.invoke(entity);
				if(null == value){
					ps.setObject(key, null);
				}else
					ps.setShort(key, value);

			}else
			if (type.contains("java.lang.Double")) {
				Double value = (Double) m.invoke(entity);
				if(null == value){
					ps.setObject(key, null);
				}else
					ps.setDouble(key, value);
			}else
			if (type.contains("double")) {
				Double value = (Double) m.invoke(entity);
				if(null == value){
					ps.setObject(key, null);
				}else
					ps.setDouble(key, value);
			}else
			if (type.contains("java.lang.Boolean")) {
				Boolean value = (Boolean) m.invoke(entity);
				if(null == value){
					ps.setObject(key, null);
				}else
					ps.setBoolean(key, value);
			}else
			if (type.contains("java.util.Date")) {
				Date value = (Date) m.invoke(entity);
				if (null != value) {
					ps.setTimestamp(key, new java.sql.Timestamp(value.getTime()));
				} else {
					ps.setObject(key, null);
				}
			}else
			if (type.contains("java.lang.Long")) {
				Long value = (Long) m.invoke(entity);
				if(null == value){
					ps.setObject(key, null);
				}else
					ps.setLong(key, value);
			}else
			if (type.contains("long")) {
				Long value = (Long) m.invoke(entity);
				if(null == value){
					ps.setObject(key, null);
				}else
					ps.setLong(key, value);
			}
		}
		//
		System.out.println(System.currentTimeMillis() - i);
		return ps;
	}
	
	public static String buildUpdateSQL(Object entityClass, String idField, Object value, List<Object> values, String noupdate) throws Exception {
		final StringBuffer sql = new StringBuffer("update ");
		sql.append(getTableName(entityClass)).append(" set ");
		long i = System.currentTimeMillis();
		Class c = entityClass.getClass().getSuperclass();
		Field[] fields = c.getDeclaredFields();
		StringBuffer field = new StringBuffer("");
		for (int j = 0; j < fields.length; j++) { // 遍历所有属性
			String name = fields[j].getName(); // 获取属性的名字
			if(idField.equals(name) || noupdate.contains(name)){
				continue;
			}
			String methodName = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
			Method m = c.getMethod("get" + methodName);
			if(m.getGenericReturnType().toString().contains("list"))
				continue;
			
			field.append(name).append("=?,");
			values.add(m.invoke(entityClass));
		}
		sql.append(field.substring(0, field.length() - 1));
		sql.append(" where ").append(idField).append("=?");
		values.add(value);
		System.out.println("update sql ----->>   :  "+sql.toString());
		return sql.toString();
	}
	public static String buildUpdateSQL(Object entityClass, String idField, Object value, List<Object> values) throws Exception {
		final StringBuffer sql = new StringBuffer("update ");
		sql.append(getTableName(entityClass)).append(" set ");
		long i = System.currentTimeMillis();
		Class c = entityClass.getClass().getSuperclass();
		Field[] fields = c.getDeclaredFields();
		if(null == fields || fields.length == 0)
			fields = entityClass.getClass().getDeclaredFields();
		StringBuffer field = new StringBuffer("");
		for (int j = 0; j < fields.length; j++) { // 遍历所有属性
			String name = fields[j].getName(); // 获取属性的名字
			if(idField.equals(name)){
				continue;
			}
			String methodName = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
			Method m = entityClass.getClass().getMethod("get" + methodName);
			String type = m.getGenericReturnType().toString();
			if(type.contains("List") || type.contains("Set"))
				continue;
			
			field.append(name).append("=?,");
			values.add(m.invoke(entityClass));
		}
		sql.append(field.substring(0, field.length() - 1));
		sql.append(" where ").append(idField).append("=?");
		values.add(value);
		System.out.println("update sql ----->>   :  "+sql.toString());
		return sql.toString();
	}
	
	public static String buildUpdateSQL(Object entityClass, String[] whereField, List<Object> values) throws Exception {
		final StringBuffer sql = new StringBuffer("update ");
		sql.append(getTableName(entityClass)).append(" set ");
		long i = System.currentTimeMillis();
		Class c = entityClass.getClass().getSuperclass();
		Field[] fields = c.getDeclaredFields();
		StringBuffer field = new StringBuffer("");
		for (int j = 0; j < fields.length; j++) { // 遍历所有属性
			String name = fields[j].getName(); // 获取属性的名字
			for(String str : whereField){
				if(str.equals(name)){
					continue;
				}
			}
			String methodName = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
			Method m = c.getMethod("get" + methodName);
			if(m.getGenericReturnType().toString().contains("list"))
				continue;
			
			field.append(name).append("=?,");
			values.add(m.invoke(entityClass));
		}
		sql.append(field.substring(0, field.length() - 1));
		sql.append(" where 1=1 ");
		for(String str : whereField){
			sql.append(" and ").append(str).append("=? ");
		}
		System.out.println("update sql ----->>   :  "+sql.toString());
		return sql.toString();
	}
	
	public static void buildBeanProperty(ResultSet rs, Object entity) throws Exception {
		Class c = entity.getClass().getSuperclass();
		Field[] fields = c.getDeclaredFields();
		if(null == fields || fields.length == 0)
			fields = entity.getClass().getDeclaredFields();
		String field = "";
		String type = "";
		for (int j = 0; j < fields.length; j++) { // 遍历所有属性
			field = fields[j].getName(); // 获取属性的名字
			type = fields[j].getGenericType().toString();
			String methodName = field.substring(0, 1).toUpperCase() + field.substring(1); // 将属性的首字符大写，方便构造get，set方法
			if (type.contains("java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
				Method m = entity.getClass().getMethod("set" + methodName, String.class);
				String value = rs.getString(field);// 获取属性值
				m.invoke(entity, value);
			}else
			if (type.contains("java.lang.Integer")) {
				Method m = entity.getClass().getMethod("set" + methodName, Integer.class);
				Integer value = rs.getInt(field);// 获取属性值
				m.invoke(entity, value);
			}else
			if (type.contains("java.lang.Short")) {
				Method m = entity.getClass().getMethod("set" + methodName, Short.class);
				Short value = rs.getShort(field);// 获取属性值
				m.invoke(entity, value);
			}else
			if (type.contains("java.lang.Double")) {
				Method m = entity.getClass().getMethod("set" + methodName, Double.class);
				Double value = rs.getDouble(field);// 获取属性值
				m.invoke(entity, value);
			}else
			if (type.contains("java.lang.Boolean")) {
				Method m = entity.getClass().getMethod("set" + methodName, Boolean.class);
				Boolean value = rs.getBoolean(field);// 获取属性值
				m.invoke(entity, value);
			}else
			if (type.contains("java.util.Date")) {
				Method m = entity.getClass().getMethod("set" + methodName, Date.class);
				java.sql.Date value = rs.getDate(field);// 获取属性值
				if (null != value) {
					m.invoke(entity, new Date(value.getTime()));
				}
			}else
			if (type.contains("java.lang.Long")) {
				Method m = entity.getClass().getMethod("set" + methodName, Long.class);
				Long value = rs.getLong(field);// 获取属性值
				m.invoke(entity, value);
			}else if(type.contains("java.lang.long")) {
				Method m = entity.getClass().getMethod("set" + methodName, long.class);
				long value = rs.getLong(field);// 获取属性值
				m.invoke(entity, value);
			}else if(type.contains("java.lang.int")) {
				Method m = entity.getClass().getMethod("set" + methodName, int.class);
				int value = rs.getInt(field);// 获取属性值
				m.invoke(entity, value);
			}else{
				System.out.println(" ------------------------->> Property Type: " + type);
			}
			
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<?> buildBeanProperty(List<Map<String, Object>> list, Object entity){
		List beans = new ArrayList(0);
		for(int i = 0, len = list.size(); i < len; i++){
			try {
				Object obj = entity;
				beans.add(obj);
				buildBeanProperty(list.get(i), beans.get(i));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return beans;
	}
	
	
	public static void buildBeanProperty(Map<String, Object> map, Object entity) throws Exception {
		Field[] fields = entity.getClass().getSuperclass().getDeclaredFields();
		if(null == fields || fields.length == 0)
			fields = entity.getClass().getDeclaredFields();
		String field = "";
		String type = "";
		String _value = "";
		Object value = null;
		for (int j = 0; j < fields.length; j++) { // 遍历所有属性
			field = fields[j].getName(); // 获取属性的名字
			value = map.get(field);// 获取属性值
			String methodName = field.substring(0, 1).toUpperCase() + field.substring(1); // 将属性的首字符大写，方便构造get，set方法
			if(null == value){continue;}
			_value = value.toString();
			type = fields[j].getGenericType().toString();
			if (type.contains("String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
				Method m = entity.getClass().getMethod("set" + methodName, String.class);
				m.invoke(entity, _value);
			}else
			if (type.contains("Integer")) {
				Method m = entity.getClass().getMethod("set" + methodName, Integer.class);
				m.invoke(entity, Integer.parseInt(_value));
			}else
			if (type.contains("Short")) {
				Method m = entity.getClass().getMethod("set" + methodName, Short.class);
				m.invoke(entity, Short.parseShort(_value));
			}else
			if (type.contains("Double")) {
				Method m = entity.getClass().getMethod("set" + methodName, Double.class);
				m.invoke(entity, Double.parseDouble(_value));
			}else
			if (type.contains("Boolean")) {
				Method m = entity.getClass().getMethod("set" + methodName, Boolean.class);
				m.invoke(entity, Boolean.parseBoolean(_value));
			}else
			if (type.contains("Date")) {
				Method m = entity.getClass().getMethod("set" + methodName, Date.class);
				java.sql.Timestamp date = (java.sql.Timestamp) map.get(field);// 获取属性值
				m.invoke(entity, new Date(date.getTime()));
			}else
			if (type.contains("Long")) {
				Method m = entity.getClass().getMethod("set" + methodName, Long.class);
				m.invoke(entity, Long.parseLong(_value));
			}else if(type.contains("long")) {
				Method m = entity.getClass().getMethod("set" + methodName, long.class);
				m.invoke(entity, Long.parseLong(_value));
			}else if(type.contains("int")) {
				Method m = entity.getClass().getMethod("set" + methodName, int.class);
				m.invoke(entity, Integer.parseInt(_value));
			}else{
				System.out.println(" ------------------------->> Property Type: " + type);
			}
		}
	}
}
