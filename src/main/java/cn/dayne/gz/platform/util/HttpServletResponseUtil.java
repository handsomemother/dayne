/*
 * Copyright (C) 2011 GZ-ISCAS Inc., All Rights Reserved.
 */
package cn.dayne.gz.platform.util;

import java.io.FileReader;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 跟HttpServletResponse相关的util类
 * 
 * @author yeqiuming
 * @version 1.0, 2012-9-3
 */
public class HttpServletResponseUtil {
	
	/**
	 * 使用重用ObjectMapper方式
	 */
	private static ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * 日志
	 */
	private static Logger log = Logger.getLogger(HttpServletResponseUtil.class);
	
	/*
	static {
		//设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性  
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		//不显示对象中的null值
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		//设置json日期格式
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd")); 
	}
	*/
	
	/**
	 * 把object转换成字符串并以utf8编码写入到response中.
	 * 
	 * @date 2010-09-25
	 * @param response HttpServletResponse
	 * @param object 需要转换的对象
	 */
	public static void writeJson2Response(HttpServletResponse response,Object object){
		try {
			response.setCharacterEncoding("UTF-8");
			mapper.writeValue(response.getWriter(), object);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			log.error(e);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			log.error(e);
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e);
		}
	}
	
	/**
	 * 把object转换成JsonString
	 * 
	 * @date 2010-09-25
	 * @param obj 需要转换成json的对象
	 * @return
	 */
	public static String object2JsonString(Object obj){ 
		String jsonString = null;
	    try {
	    	jsonString = mapper.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			log.error(e);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			log.error(e);
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e);
		} 
		return jsonString;
	}  
	
	/**
	 * 简单转换，把json字符串转换为OPJO
	 * 
	 * @date 2010-09-25
	 * @param <T>
	 * @param jsonAsString json字符串
	 * @param pojoClass  对象Class
	 * @return
	 */
	public static <T> Object jsonAsString2Object(String jsonAsString, Class<T> pojoClass){
		Object object = null;
        try {
        	object = mapper.readValue(jsonAsString, pojoClass);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return object;
    }

    /**
     * 简单转换，把json字符串转换为OPJO
     * 
     * @date 2010-09-25
     * @param <T>
     * @param fr
     * @param pojoClass
     * @return
     * @throws JsonParseException
     * @throws IOException
     */
    public static <T> Object jsonAsFileReader2Object(FileReader fr, Class<T> pojoClass) throws JsonParseException, IOException {
        return mapper.readValue(fr, pojoClass);
    }
    
    /**
     * 把json字符串转换成List、Map等
     * 
     * Demo:List<Article> list = (List<Article>)HttpServletResponseUtil.jsonAsString2Object(jsonString, new TypeReference<List<Article>>(){});
     * 
     * @date 2010-09-25
     * @param jsonAsString
     * @param typeReference
     * @return
     * @throws Exception
     */
    public static Object jsonAsString2Object(String jsonAsString, TypeReference<?> typeReference) throws Exception {
        return mapper.readValue(jsonAsString, typeReference);
    }

    /**
     * 将object转换成字符串并以utf8编码写入到response中.
     * 
     * @date 2010-12-31
     * @param response
     * @param object
     * @throws IOException
     */
    public static void writeObject2Response(HttpServletResponse response, Object object) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(object.toString());
    }
    
    /**
     * 将array转换成json字符串并以utf8编码写入到response中.
     * 
     * 使用json-lib解析对象，建议使用上面jackson方式
     * 
     * @date 2010-12-31
     * @param response
     * @param object
     * @throws IOException
     */
    @Deprecated
    public static void writeArrayJSON2Response(HttpServletResponse response, Object object) throws IOException {
        JSONArray jsonArray = JSONArray.fromObject(object);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(jsonArray.toString());
    }
    
    /**
     * 将object转换成json字符串并以utf8编码写入到response中.
     * 
     * 使用json-lib解析对象，建议使用上面jackson方式
     * 
     * @date 2010-12-31
     * @param response
     * @param object
     * @throws IOException
     */
    @Deprecated
    public static void writeObjectJSON2Response(HttpServletResponse response, Object object) throws IOException {
        JSONObject jsonObject = JSONObject.fromObject(object);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(jsonObject.toString());
    }
    
    
	/**
	 * 简单转换，把json字符串转换为OPJO
	 * 
	 * @date 2010-09-25
	 * @param <T>
	 * @param jsonAsString
	 *            json字符串
	 * @param pojoClass
	 *            对象Class
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public static <T> Object jsonAsString2ObjectForTelecom(String jsonAsString,
			Class<T> pojoClass) throws JsonParseException,
			JsonMappingException, IOException {
		Object object = null;
		object = mapper.readValue(jsonAsString, pojoClass);
		return object;
	}
}
