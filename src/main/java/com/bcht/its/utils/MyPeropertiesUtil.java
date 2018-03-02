package com.bcht.its.utils;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;

public class MyPeropertiesUtil {
	protected static Properties properties = null;
	static{
		try {
			properties = PropertiesLoaderUtils.loadAllProperties("application.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getValue(String key){
		return properties.getProperty(key);
	}
	
	public static Integer getInt(String key){
		return Integer.valueOf(properties.getProperty(key));
	}
	
	public static Long getLong(String key){
		return Long.valueOf(properties.getProperty(key));
	}
	
	public static void main(String[] args) {
		System.out.println(MyPeropertiesUtil.getValue("deptVisible"));
	}
}
