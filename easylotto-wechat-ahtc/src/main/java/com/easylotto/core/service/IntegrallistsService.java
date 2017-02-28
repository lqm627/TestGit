package com.easylotto.core.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.easylotto.commons.util.DateTimeUtil;
import com.easylotto.core.dao.MemberDao;
import com.wechat.webapi.util.Pagination;
import com.wechat.webapi.web.common.ResponseErrorMessage;
import com.wechat.webapi.web.model.ResponseBean;

@Service
@Transactional
public class IntegrallistsService  implements IClientService{
	@Autowired
	private MemberDao memberDao;	
	
	public ResponseBean execute(String request_data,Long memberId,Map<String,String> parameterMap){
		  ResponseBean responseBean=new ResponseBean(); 
		  String pageSize=parameterMap.get("pageSize");
	      List<Map<String, Object>> colorBeanLists =null;  
	      Pagination page=null;   
	      Map<String, Object> map = new HashMap<String, Object>();  
	      
	      try {  
	          String currentPage="";
	          String numPerPage ="";
	          System.out.println(request_data);
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
	          }
	          if("".equals(currentPage)||"".equals(numPerPage)){   
	              page =memberDao.getColorBeanLists(1, Integer.valueOf(pageSize), memberId.intValue());  
	          }else{   
	              page =memberDao.getColorBeanLists(Integer.valueOf(currentPage), Integer.valueOf(numPerPage), memberId.intValue());    
	          }   
	          List list=page.getResultList();  
	          colorBeanLists=new ArrayList<Map<String,Object>>();   
	          for (int i = 0;i<list.size(); i++) {  
	              Map<String, Object> maps=new HashMap<String, Object>();  
	              Map mapRe=(Map)list.get(i);  
	              maps.put("int_account_id",mapRe.get("INT_ACCOUNT_ID"));  
	              maps.put("dec_lottery_bean", mapRe.get("dec_lottery_bean"));  
	              maps.put("dec_amount", mapRe.get("DEC_AMOUNT"));    
	              maps.put("dec_balance", mapRe.get("DEC_BALANCE"));
	              String int_oper_type=mapRe.get("INT_OPER_TYPE").toString();
	              if(int_oper_type.equals("0")){
	            	  maps.put("int_oper_type", "购彩");  
	              }else if(int_oper_type.equals("1")){
	            	  maps.put("int_oper_type", "充值"); 
	              }else if(int_oper_type.equals("2")){
	            	  maps.put("int_oper_type", "提现"); 
	              }else if(int_oper_type.equals("3")){
	            	  maps.put("int_oper_type", "派奖"); 
	              }else if(int_oper_type.equals("4")){
	            	  maps.put("int_oper_type", "退款"); 
	              }else if(int_oper_type.equals("5")){
	            	  maps.put("int_oper_type", "出账成功"); 
	              }else if(int_oper_type.equals("6")){
	            	  maps.put("int_oper_type", "出账失败"); 
	              }else if(int_oper_type.equals("7")){
	            	  maps.put("int_oper_type", "赠送"); 
	              }else if(int_oper_type.equals("8")){
	            	  maps.put("int_oper_type", "签到"); 
	              }else if(int_oper_type.equals("9")){
	            	  maps.put("int_oper_type", "开奖信息订阅"); 
	              }else if(int_oper_type.equals("10")){
	            	  maps.put("int_oper_type", "幸运大转盘"); 
	              }
	              else if(int_oper_type.equals("11")){
	            	  maps.put("int_oper_type", "彩豆购彩"); 
	              }
	              else{
	            	  maps.put("int_oper_type", int_oper_type);  
	              }              
	              maps.put("dt_oper_time", DateTimeUtil.toDate(mapRe.get("DT_OPER_TIME").toString()).getTime());
	              colorBeanLists.add(maps);   
	          }  
	          responseBean.setErrorCode(ResponseErrorMessage.SUCCESS);
	      } catch (Exception e1) {   
	          e1.printStackTrace();  
	          responseBean.setErrorCode(ResponseErrorMessage.ERROR);

	      }finally{    
	          map.put("totalPage", page.getTotalPages());    
	          map.put("currentPage", page.getCurrentPage());    
	          map.put("totalRows", page.getTotalRows());    
	          map.put("numPerPage", page.getNumPerPage());      
	          map.put("rows", colorBeanLists);    
	          responseBean.setData(map);
	      }  
	      
		return responseBean;
	}
	


}
	
	