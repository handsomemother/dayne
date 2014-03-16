/*
 * Copyright (C) 2011 GZ-ISCAS Inc., All Rights Reserved.
 */
package cn.dayne.gz.platform.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * 向 Spring MVC 框架注册自定义的属性编辑器.
 * 
 */
public class DateBindingInitializer implements WebBindingInitializer {

	@Override
	public void initBinder(WebDataBinder binder,
			WebRequest paramWebRequest) {
		// 日期类型的自定义属性编辑器
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

}