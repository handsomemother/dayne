/**
 * Copyright (C) 2012 GZ-ISCAS Inc., All Rights Reserved.
 */
package cn.dayne.gz.platform.util;

/**
 * 系统常量类
 * 
 * @author yeqiuming
 *
 */
public class CommonConstant {
	
	/**
	 * 用于MD5加密的盐值
	 */
	public static final String SALT = "NFSCHINA";
	
	/**
	 * session中用户信息的key
	 */
	public static final String USER_INFO = "USER_INFO";
	
	/**
	 * 监控级别高
	 */
	public static final int MONITORING_LEVEL_HIGHT = 1;
	
	/**
	 * 监控级别中
	 */
	public static final int MONITORING_LEVEL_MIDDLE = 2;
	
	/**
	 * 监控级别低
	 */
	public static final int MONITORING_LEVEL_LOW = 3;
	
	/**
	 * 电信接口
	 */
	public static final int TYPE_TELECOM = 1;
	
	/**
	 * REST接口
	 */
	public static final int TYPE_REST = 2;
	
	/**
	 * WS接口
	 */
	public static final int TYPE_WS = 3;
	
	/**
	 * 数据库
	 */
	public static final int TYPE_DATABASE = 4;
	
	/**
	 * 网页
	 */
	public static final int TYPE_WEB = 5;
	
	/**
	 * 智慧家庭服务平台
	 */
	public static final int SYS_PLATFORM = 1;
	
	/**
	 * 智慧家居控制平台
	 */
	public static final int SYS_TERMINAL = 2;
	
	/**
	 * 数据源状态正常
	 */
	public static final int STATUS_NORMAL = 1;
	
	/**
	 * 数据源状态异常
	 */
	public static final int STATUS_UNUSUAL = 0;
	
	
	/**
	 * 高频触发器名字
	 */
	public static final String HIGH_CRONTRIGGER = "highCronTrigger";
	
	
	/**
	 * 中频触发器名字
	 */
	public static final String MIDDLE_CRONTRIGGER = "middleCronTrigger";
	
	
	/**
	 * 低频触发器名字
	 */
	public static final String LOW_CRONTRIGGER = "lowCronTrigger";
	
	
	/**
	 * 电信接口成功码
	 */
	public static final String TELECOM_NORMAL_RESULT = "200";
	
	/**
	 * post请求
	 */
	public static final String REQUEST_TYPE_POST = "post";
	
	/**
	 * get请求
	 */
	public static final String REQUEST_TYPE_GET = "get";
}
