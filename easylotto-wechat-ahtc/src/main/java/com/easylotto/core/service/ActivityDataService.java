/**
 * 
 */
package com.easylotto.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springside.modules.mapper.JsonMapper;

import com.easylotto.commons.util.JedisUtil;
import com.easylotto.core.dao.ActivityDataDao;
import com.easylotto.core.entity.ActivityLog;
import com.easylotto.core.util.ActivityDataFormat;
import com.wechat.webapi.web.config.SpringContext;

/**
 * @author CreateName: UpdateName:
 * @see QQ：
 * @see E-MAIL：
 * @see Function: TODO
 * @see ActivityDataService
 * @see CreateDate: 2016年6月3日 下午12:41:12 UpdateDate: 2016年6月3日 下午12:41:12
 * @see Copyright
 * @since JDK1.7.*
 * @version 1.0
 */
@Service
public class ActivityDataService {

	@Autowired
	private ActivityDataDao activityDataDao;

	
	public void export(final HttpServletRequest request, final HttpServletResponse response, final String type){
		if("1".equals(type)){
			List<ActivityLog> list = activityDataDao.findBy1();
			ActivityDataFormat.export(request, response, list, type);
		}else if("2".equals(type)){
			List<ActivityLog> list = activityDataDao.findBy2();
			ActivityDataFormat.export(request, response, list, type);
		}else if("3".equals(type)){
			List<ActivityLog> list = activityDataDao.findBy();
			ActivityDataFormat.export(request, response, list);
		}else if("6".equals(type)){
			List<ActivityLog> list = activityDataDao.findBy6();
			ActivityDataFormat.export(request, response, list, type);
		}else if("29".equals(type)){
			 List<ActivityLog> list = activityDataDao.findBy29();
			 ActivityDataFormat.export(request, response, list, type);
		}else if("30".equals(type)){
			 List<ActivityLog> list = activityDataDao.findBy30();
			 ActivityDataFormat.export(request, response, list, type);
		}else if("31".equals(type)){
			 List<ActivityLog> list = activityDataDao.findBy31();
			 ActivityDataFormat.export(request, response, list, type);
		}
	}
	
	
	public void export(String type, HttpServletResponse response){
		try {
			List<Map<String, Object>> list = this.activityDataDao.find(type);
			List<Map<String, Object>> values = new ArrayList<Map<String,Object>>(2);
			values.add(list.get(list.size()-2));
			list = this.activityDataDao.find2(type);
			values.add(list.get(list.size()-2));
			response.getWriter().write(JsonMapper.nonEmptyMapper().toJson(values));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
