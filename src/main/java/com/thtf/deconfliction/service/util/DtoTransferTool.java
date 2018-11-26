package com.thtf.deconfliction.service.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DtoTransferTool {
	private static final Logger log = LoggerFactory.getLogger(DtoTransferTool.class);

	private DtoTransferTool() {

	}

	/**
	 * 转换成dto 忽略set集合等属性（set集合不转换）。
	 * 
	 * @param list
	 * @param dtoClass
	 * @return
	 */
	public static <Entity, EntityDTO> List<EntityDTO> toDtoIgnoreCollection(List<Entity> list,
			Class<EntityDTO> dtoClass) {
		List<EntityDTO> nList = new ArrayList<>();
		try {
			for (Entity entity : list) {
				nList.add(toDtoIgnoreCollection(entity, dtoClass));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return nList;
	}

	/**
	 * 转换成dto 忽略set集合等属性（set集合不转换）。
	 * 
	 * @param t
	 * @param dtoClass
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public static <Entity, EntityDTO> EntityDTO toDtoIgnoreCollection(Entity t, Class<EntityDTO> dtoClass)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Class c = t.getClass();
		EntityDTO dto = dtoClass.newInstance();
		Field[] fields = c.getDeclaredFields();
		for (Field field : fields) {
			if (field.getClass().isAssignableFrom(Set.class)) {
				continue;
			}
			String gname = "get" + captureName(field.getName());
			String sname = "set" + captureName(field.getName());
			try {
				Method gm = c.getMethod(gname, null);
				Method sm = dtoClass.getMethod(sname, field.getType());
				sm.invoke(dto, gm.invoke(t, null));
			} catch (NoSuchMethodException e) {

			}
		}
		return dto;
	}

	/**
	 * 转换成Dto
	 * 
	 * @param list
	 * @param dtoClass
	 * @param ignoreProps
	 *            不需要转换成dto的属性集
	 * @return
	 */
	public static <Entity, EntityDTO> List<EntityDTO> toDto(List<Entity> list, Class<EntityDTO> dtoClass,
			String... ignoreProps) {
		List<EntityDTO> nList = new ArrayList<>();
		try {
			for (Entity entity : list) {
				nList.add(toDto(entity, dtoClass, ignoreProps));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return nList;
	}

	/**
	 * 转换成Dto
	 * 
	 * @param list
	 * @param dtoClass
	 * @param ignoreProps
	 *            不需要转换成dto的属性集
	 * @return
	 */
	public static <Entity, EntityDTO> EntityDTO toDto(Entity t, Class<EntityDTO> dtoClass, String... ignoreProps)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Class c = t.getClass();
		EntityDTO dto = dtoClass.newInstance();
		Field[] fields = c.getDeclaredFields();
		for (Field field : fields) {
			boolean isContinue = true;
			for (String prop : ignoreProps) {
				if (field.getName().equals(prop)) {
					isContinue = false;
					break;
				}
			}
			if (!isContinue) {
				continue;
			}
			String gname = "get" + captureName(field.getName());
			String sname = "set" + captureName(field.getName());
			try {
				Method gm = c.getMethod(gname, null);
				Method sm = dtoClass.getMethod(sname, field.getType());
				sm.invoke(dto, gm.invoke(t, null));
			} catch (NoSuchMethodException e) {

			}
		}
		return dto;
	}

	public static String captureName(String name) {
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		return name;
	}
}
