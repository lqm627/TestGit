package com.easylotto.core.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.easylotto.commons.util.DateTimeUtil;
import com.easylotto.commons.util.JedisUtil;
import com.easylotto.core.dao.LotteryDao;
import com.easylotto.core.dao.MemberDao;
import com.easylotto.core.dao.NewsDao;
import com.easylotto.core.entity.Activity;
import com.easylotto.core.entity.ILotteryType;
import com.easylotto.core.entity.Member;
import com.easylotto.core.entity.News;
import com.easylotto.core.entity.user.EcpSignIn;
import com.easylotto.core.entity.user.EcpUser;
import com.easylotto.core.entity.user.EcpUserFundLog;
import com.easylotto.core.entity.user.EcpUserKey;
import com.easylotto.core.service.userCenter.EcpUserInfoService;
import com.wechat.webapi.util.Pagination;
import com.wechat.webapi.web.common.ResponseErrorMessage;
import com.wechat.webapi.web.model.ResponseBean;

import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang.StringUtils;

@Service
@Transactional
public class NewsService  implements IClientService{
	@Autowired
	private NewsDao newsDao;
	
	public ResponseBean execute(String request_data,Long memberId,Map<String,String> parameterMap){
		  ResponseBean responseBean = new ResponseBean();
	      List<Map<String, Object>> newsLists =null;  
	      Pagination page=null;   
	      Map<String, Object> map = new HashMap<String, Object>();  
	      try {  
	    	  String pageSize=parameterMap.get("pageSize");
	          String currentPage="";
	          String numPerPage ="";
	          String type="";
	          if("{}".equals(request_data)){

	          }else{
	        	  JSONObject jsonobject = JSONObject.parseObject(request_data);
	        	  if(jsonobject.containsKey("currentPage")){
	                  currentPage  =jsonobject.getString("currentPage");  
	                  System.out.println("currentPage"+currentPage);
	        	  }
	              if(jsonobject.containsKey("numPerPage")){
	                  numPerPage  = jsonobject.getString("numPerPage");   
	                  System.out.println("numPerPage"+numPerPage);
	              }
	              type = jsonobject.getString("type");  
	          }
	          if("".equals(currentPage)||"".equals(numPerPage)){   
//	              News news=newsDao.getNewsBanner(Integer.valueOf(type));
//	              if(news!=null){
//	  				map.put("bannerId", news.getInt_rec_id());
//	  				map.put("banner",news.getVc_name());
//	  				map.put("bannerTitle", news.getVc_title());
//	  				map.put("bannerContent", news.getCb_content());
//	  				map.put("banner",news.getVc_pic_url());
//	  				map.put("bannerMovie", news.getVc_mov_url());
//	  				map.put("bannerMusic", news.getVc_mus_url());
//	  				map.put("bannerDigest", news.getVc_digest());
//	  				map.put("bannerTime", news.getDt_news_date().getTime());
	                page =newsDao.getNews(1, Integer.valueOf(pageSize), Integer.valueOf(type));   
//	              }

	          }else{   
	              page =newsDao.getNews(Integer.valueOf(currentPage), Integer.valueOf(numPerPage), Integer.valueOf(type));      
	          }
	          if(page!=null){
	              List list=page.getResultList();  
	              if(list.size()>0){
	                  newsLists=new ArrayList<Map<String,Object>>();   
	                  for (int i = 0;i<list.size(); i++) {  
	                      Map<String, Object> maps=new HashMap<String, Object>();  
	                      Map mapRe=(Map)list.get(i);  
	                      maps.put("int_rec_id",mapRe.get("INT_REC_ID"));  
	                      maps.put("vc_name", mapRe.get("VC_NAME"));  
	                      maps.put("vc_title", mapRe.get("VC_TITLE"));    
	                      maps.put("cb_content", mapRe.get("CB_CONTENT"));  
	                      maps.put("vc_pic_url", mapRe.get("VC_PIC_URL")); 
	                      maps.put("vc_mov_url", mapRe.get("VC_MOV_URL"));
	                      maps.put("vc_mus_url", mapRe.get("VC_MUS_URL"));
	                      maps.put("vc_href_url", mapRe.get("VC_HREF_URL"));
	                      maps.put("dt_news_date", DateTimeUtil.toDate(mapRe.get("DT_NEWS_DATE").toString()).getTime());
	                      maps.put("int_news_type", mapRe.get("INT_NEWS_TYPE"));
	                      maps.put("vc_digest", mapRe.get("VC_DIGEST"));
	                      newsLists.add(maps);   
	                  }  
	              }

	          }

	          responseBean.setErrorCode(ResponseErrorMessage.SUCCESS);

	      } catch (Exception e1) {   
	          e1.printStackTrace();  
	          responseBean.setErrorCode(ResponseErrorMessage.ERROR);

	      }finally{
	    	  if(null != page){
		          map.put("totalPage", page.getTotalPages());    
		          map.put("currentPage", page.getCurrentPage());    
		          map.put("totalRows", page.getTotalRows());    
		          map.put("numPerPage", page.getNumPerPage());      
	          }
	          map.put("rows", newsLists);    
	          responseBean.setData(map);
	      } 
		return responseBean;
	}
	
	

}
	
	