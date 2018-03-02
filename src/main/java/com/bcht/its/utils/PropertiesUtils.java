package com.bcht.its.utils;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *  配置文件工具
 * @author 陶诗德
 *
 */
public class PropertiesUtils {
	private  static Properties properties = null;
	static {
		try {
			properties = PropertiesLoaderUtils.loadAllProperties("config.properties");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
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
}
