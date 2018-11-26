package com.thtf.deconfliction.service.util;

import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TreeTool {

	private static final Logger log = LoggerFactory.getLogger(TreeTool.class);

	private TreeTool() {

	}
	
	
	/**
	 * 根据id 获取其子树包含当前节点
	 * 
	 * @param repository     eg:dictionaryRepository
	 * @param all			 eg:List<Dictionary>
	 * @param id             eg:1
	 * @param entityClass    eg: Dictionary.class
	 * @param uidName        eg："upperId"
	 * @param idName         eg: "id"
	 */
	public static <Entity, Repository> void getChildTreeContainedMe(Repository repository, List<Entity> all, Long id,
			Class entityClass, String uidName, String idName){
		String repositoryMethodName = "findBy" + firstUpcase(idName);
		Method repositoryMethod;
		try {
			repositoryMethod = getMethod(repository.getClass(), repositoryMethodName, Long.class);
			List<Entity> list = (List<Entity>) repositoryMethod.invoke(repository, id);
			all.addAll(list);
			getChildTree(repository,all,id,entityClass,uidName,idName);
		} catch (Exception e) {
			log.error("获取子树列表异常：{}", e);
		}
		
	}

	/**
	 * 根据id 获取其子树
	 * 
	 * @param repository     eg:dictionaryRepository
	 * @param all			 eg:List<Dictionary>
	 * @param id             eg:1
	 * @param entityClass    eg: Dictionary.class
	 * @param uidName        eg："upperId"
	 * @param idName         eg: "id"
	 */
	public static <Entity, Repository> void getChildTree(Repository repository, List<Entity> all, Long id,
			Class entityClass, String uidName, String idName) {
		try {
			String repositoryMethodName = "findBy" + firstUpcase(uidName);
			Method repositoryMethod = getMethod(repository.getClass(), repositoryMethodName, Long.class);
			List<Entity> list = (List<Entity>) repositoryMethod.invoke(repository, id);
			all.addAll(list);
			String methodName = "get" + firstUpcase(idName);
			Method method = getMethod(entityClass, methodName, null);
			for (int i = 0; i < list.size(); i++) {
				Long upId = (Long) method.invoke(list.get(i), null);
				getChildTree(repository, all, upId, entityClass, uidName, idName);
			}
		} catch (Exception e) {
			log.error("获取子树列表异常：{}", e);
		}
	}

	private static String firstUpcase(String uidName) {
		return uidName.substring(0, 1).toUpperCase() + uidName.substring(1);
	}

	private static Method getMethod(Class entityClass, String methodName, Class... paramType)
			throws NoSuchMethodException {
		Method method = null;
		if (paramType != null) {
			method = entityClass.getMethod(methodName, paramType);
		} else {
			method = entityClass.getMethod(methodName);
		}

		return method;
	}

}
