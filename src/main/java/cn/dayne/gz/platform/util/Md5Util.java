package cn.dayne.gz.platform.util;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;


/**
 * @author  yeqiuming
 * @date Sept 13, 2012
 * @Description 
 */
public class Md5Util {
	
	/**
	 * 加密
	 * @param content 加密内容
	 * @param salt 盐值
	 * @return
	 */
	public static String encrypting(String content,String salt){
		Md5PasswordEncoder md5=new Md5PasswordEncoder();
		return md5.encodePassword(content,salt);
	}
	
	/**
	 * 判断加密内容是否一致
	 * 
	 * @param md5Content 加密后的内容
	 * @param content 原内容
	 * @param salt 盐值
	 * @return
	 */
	public static Boolean isValid(String md5Content,String content,String salt){
		Md5PasswordEncoder md5=new Md5PasswordEncoder();
		return md5.isPasswordValid(md5Content, content, salt);
	}
	
	public static void main(String[]args){
		String content = "123456";
		String key = "NFSCHINA";
		String md5Content = encrypting(content, key);
		System.out.println(md5Content);
		System.out.println(isValid(md5Content, content, key));
	}
}
