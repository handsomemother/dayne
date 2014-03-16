package cn.dayne.gz.platform.web;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.dayne.gz.platform.dto.SourceDTO;
import cn.dayne.gz.platform.query.SourceCriteria;
import cn.dayne.gz.platform.service.SourceService;
import cn.dayne.gz.platform.util.CommonConstant;
import cn.dayne.gz.platform.util.HttpServletResponseUtil;

@Controller
@RequestMapping("/monitoring.do")
public class MonitoringController {
	
//	private final static String VIEW = "jsp/monitor/monitoringview/monitoringview";
//	private Logger log = Logger.getLogger(MonitoringController.class);
	
	private final  String DIV_PRE = "id_";
	@Resource
	private SourceService sourceService;
	
	
	/**
	 * 类似servlet，直接向客户端返回拼装后网页
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(method = RequestMethod.GET)
	public void view(HttpServletRequest request,HttpServletResponse response,SourceCriteria criteria) throws IOException{
		String contextPath = request.getContextPath();
		criteria.setRp(9);
		List<SourceDTO>list = sourceService.queryPageByCriteria(criteria);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		//五分钟后自动刷新
		sb.append("<meta http-equiv=\"refresh\" content=\"300\"> ");
		//引入css
		sb.append(buildIncludeCss(contextPath));
		//引入js
		sb.append(buildIncludeJs(contextPath));
		sb.append("</head>");
		
		sb.append("<body>");
		
		sb.append("<div id=\"mainDiv\" class=\"pr10\">");
		sb.append("<h3 class=\"caption\">数据源状态监控</h3>");
		sb.append("<div class=\"hr\"></div>");
		 
		sb.append("<div class=\"sbox linb mb10\">");
			
			sb.append("<div class=\"sbox_head\">");
			sb.append("<span class=\"fl\">详细状态</span>");
			sb.append("</div>");
			
			sb.append("<div class=\"sbox_body\">");
			
			sb.append("<table width=\"800px;\" align=\"center\">");
			int count = 0;
			for(SourceDTO s : list){
				if(count%3==0){
					sb.append("<tr>");
				}
				sb.append("<td>");
				String reckeckUrl = contextPath + "/validate.do?action=recheck&id="+s.getId()+"&page="+criteria.getPage();
				//建立仪表盘div
				sb.append(buildHtml(DIV_PRE + s.getId(),s.getStatus(),s.getRecentlyResponseTime(),s.getResponseTime(),reckeckUrl));
				sb.append("</td>");
				count++;
				if((count)%3==0||count>list.size()){
					sb.append("</tr>");
				}
			}
			
			sb.append("</table>");
			
			//构建分页
			long pre =criteria.getPage()-1<1?1:criteria.getPage()-1;
			long next = criteria.getPage()+1>criteria.getTotalPage()?criteria.getTotalPage():criteria.getPage()+1;
			sb.append("<div style=\"height: 30px;\"></div>");
			sb.append("<div align=\"center\">");
			sb.append("当前第"+criteria.getPage()+"页 ");
			if(criteria.getPage()>1){
				sb.append("<a href='"+contextPath+"/monitoring.do?page=1'>首  页</a> ");
				sb.append("<a href='"+contextPath+"/monitoring.do?page="+pre+"'>上一页</a> ");
			}else{
				sb.append("首  页 ");
				sb.append("上一页 ");
			}
			if(criteria.getPage()<criteria.getTotalPage()){
				sb.append("<a href='"+contextPath+"/monitoring.do?page="+next+"'>下一页</a> ");
				sb.append("<a href='"+contextPath+"/monitoring.do?page="+criteria.getTotalPage()+"'>末  页</a> ");
			}else{
				sb.append("下一页 ");
				sb.append("末  页 ");
			}
			sb.append(" 共"+criteria.getTotalPage()+"页");
			sb.append("</div>");
			
			
			sb.append("</div>");
			
			//构建js
			sb.append("<script>");
			
			//渲染页面是初始化仪表盘
			sb.append("window.onload = function(){");
			//初始化仪表盘js
			for(SourceDTO s:list){
				sb.append(buildJs(DIV_PRE + s.getId(),s.getSourceName(),s.getRecentlyResponseTime()*1000,s.getResponseTime(),s.getStatus()));
			}	
			sb.append("};");
			
			sb.append("</script>");
		
		sb.append("</div>");
		
		sb.append("</body>");
		sb.append("</html>");
		HttpServletResponseUtil.writeObject2Response(response, sb.toString());
	}
	
	/**
	 * 构建div
	 * 
	 * @param divId
	 * @return
	 */
	private String buildHtml(String divId,int status,float recentlyResponseTime,float responseTime,String recheckUrl){
		StringBuilder sb = new StringBuilder();
		sb.append("<div id=\"");
		sb.append(divId);
		sb.append("\" class=\"justGageDiv\">");
		if(status==CommonConstant.STATUS_NORMAL){
			if(recentlyResponseTime==0){
				sb.append("<a href='"+recheckUrl+"'>启动监控并检测</a>");
			}else if(recentlyResponseTime<responseTime){
				sb.append("接口状态:<font color=\"#9CC400\">正常<font>");
			}else{
				sb.append("接口状态:<font color=\"#FF7200\">正常但较预期慢<font>");
				sb.append("  <a href='"+recheckUrl+"'>重检</a>");
			}
			
		}else{
			sb.append("接口状态:<font color=\"red\">异常已发邮件<font>");
		}
		sb.append("</div>");
		return sb.toString();
	}
	
	/**
	 * 需要引入的css
	 * 
	 * @return
	 */
	private String buildIncludeCss(String contextPath){
		StringBuilder sb = new StringBuilder();
		sb.append("<link href=\""+contextPath+"/css/justGage.css\" rel=\"stylesheet\" media=\"all\" />");
		sb.append("<link href=\""+contextPath+"/css/default.css\" rel=\"stylesheet\" media=\"all\" />");
		sb.append("<link href=\""+contextPath+"/css/base.css\" rel=\"stylesheet\" media=\"all\" />");
		sb.append("<link href=\""+contextPath+"/css/skin1.css\" rel=\"stylesheet\" media=\"all\" />");
		return sb.toString();
	}
	
	/**
	 * 需要引入的js
	 * 
	 * @return
	 */
	private String buildIncludeJs(String contextPath){
		StringBuilder sb = new StringBuilder();
		sb.append("<script src=\""+contextPath+"/js/justGage/raphael.2.1.0.min.js\"></script>");
		sb.append("<script src=\""+contextPath+"/js/justGage/justgage.1.0.1.min.js\"></script>");
		sb.append("<script src=\""+contextPath+"/js/jquery-1.7.1.min.js\"></script>");
		return sb.toString();
	}
	
	/**
	 * 创建方法
	 * 
	 * @return
	 */
	private String buildJs(String id,String title,float time,float max,int status){
		StringBuilder sb = new StringBuilder();
		sb.append("var "+id+" = new JustGage({");
		sb.append("id: \""+id+"\",");
		sb.append("value:"+time/1000+",");
		sb.append("min: 0,");
		sb.append("max: "+max+",");
		sb.append("title: \""+title+"\",");
		sb.append("label: \"访问用时/S\",");
		if(status == CommonConstant.STATUS_UNUSUAL){
			sb.append("levelColors: [\"#000\"],");
		}
		sb.append("});");
		if(status == CommonConstant.STATUS_NORMAL){
			sb.append("setInterval(function() {");
			sb.append(id+".refresh(getRandomInt(parseInt("+time/2+"),"+time+")/1000);");
			sb.append("}, 2000);");
		}
		return sb.toString();
	}
}
