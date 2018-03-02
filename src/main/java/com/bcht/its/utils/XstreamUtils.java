package com.bcht.its.utils;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Created by zyq on 2017/4/13.
 */
public class XstreamUtils {
      /**
     * 将xml转换为bean
     * @param <T> 泛型
     * @param xml 要转换为bean的xml
     * @param cls bean对应的Class
     * @return xml转换为bean
     */

    public static <T> T xmlToObject(String xml, Class<T> cls){
        XStream xstream = new XStream(new DomDriver());
        //xstream使用注解转换
        xstream.processAnnotations(cls);
        //xstream.autodetectAnnotations(true);
        return (T) xstream.fromXML(xml);
    }

   /**
     * 将bean转换为xml
     * @param obj 转换的bean
     * @return bean转换为xml
     */

    public static String objectToXml(Object obj) {
        XStream xStream = new XStream();
        //xstream使用注解转换
        xStream.processAnnotations(obj.getClass());
        //xStream.addImplicitCollection(DeviceState.class, "xjList");
        return xStream.toXML(obj);
    }
}
