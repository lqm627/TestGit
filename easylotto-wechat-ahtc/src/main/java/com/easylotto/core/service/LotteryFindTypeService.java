package com.easylotto.core.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.easylotto.commons.util.DateTimeUtil;
import com.easylotto.core.dao.LotteryDao;
import com.easylotto.core.dao.MemberDao;
import com.easylotto.core.entity.Activity;
import com.easylotto.core.entity.ILottery;
import com.easylotto.core.entity.Member;
import com.easylotto.core.entity.user.EcpSignIn;
import com.easylotto.core.entity.user.EcpUser;
import com.easylotto.core.entity.user.EcpUserFundLog;
import com.easylotto.core.entity.user.EcpUserKey;
import com.easylotto.core.service.userCenter.EcpUserInfoService;
import com.wechat.webapi.util.Pagination;
import com.wechat.webapi.web.common.ResponseErrorMessage;
import com.wechat.webapi.web.model.ResponseBean;

import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Service
@Transactional
public class LotteryFindTypeService  implements IClientService{
	@Autowired
	private LotteryDao lotteryDao;	
	
	public ResponseBean execute(String request_data,Long memberId,Map<String,String> parameterMap){
		ResponseBean responseBean = new ResponseBean();		
		try{
			String lotteryPageSize=parameterMap.get("lotteryPageSize");
			String[] str=parameterMap.get("serkey").split("_");
			String type=str[1];
			responseBean=data(type, request_data,responseBean, lotteryPageSize);
		}catch(Exception e){
			responseBean.setErrorCode(ResponseErrorMessage.ERROR);
			e.printStackTrace();
		}
		return responseBean;
	}
	
	public ResponseBean data(String type,String request_data, ResponseBean responseBean,String lotteryPageSize){
		  Pagination page = null;
	      Map<String, Object> map = new HashMap<String, Object>();  
	      try {  
	          String currentPage="";
	          String numPerPage ="";
//  		  String id="";
//  		  String beforeId="";
	          if("{}".equals(request_data)){
//	        	  if(type.equals("27")){
//	      			id=0+"";
//	      			List<ILottery> list=lotteryDao.findQuickthreeOpenRusult(pageSize,Integer.valueOf(id));
//	      			if(list!=null&&list.size()!=0){
//						int lastId=Integer.valueOf(list.get(list.size()-1).getId());
//						map.put("rows", list);
//						map.put("id", lastId);
//						responseBean.setData(map);
//						responseBean.setErrorCode(ResponseErrorMessage.SUCCESS);
//		      			return responseBean;
//					}
//	        	  }

	          }else{
	        	  JSONObject jsonobject = JSONObject.parseObject(request_data);
//	        	  if(type.equals("27")){
//	  				if(!jsonobject.containsKey("id")  || jsonobject.getString("id").equals(beforeId)){
//						
//					}else{
//						id=jsonobject.getString("id");	
//						beforeId=id;
//						List<ILottery> list=new ArrayList<ILottery>();
//							 list=lotteryDao.findQuickthreeOpenRusult(pageSize,Integer.valueOf(id));
//						if(list!=null&&list.size()!=0){
//							int lastId=Integer.valueOf(list.get(list.size()-1).getId());
//							map.put("rows", list);
//							map.put("id", lastId);
//							responseBean.setData(map);
//							responseBean.setErrorCode(ResponseErrorMessage.SUCCESS);
//							return responseBean;
//						}
//					}
//	        	  }else{
	              	  if(jsonobject.containsKey("currentPage")){
		                  currentPage  =jsonobject.getString("currentPage");  
		                  System.out.println("currentPage"+currentPage);
		        	  }
		              if(jsonobject.containsKey("numPerPage")){
		                  numPerPage  = jsonobject.getString("numPerPage");   
		                  System.out.println("numPerPage"+numPerPage);
		              }
//		          }

	        	  }
	          if(!type.equals("27")){
		          if("".equals(currentPage)||"".equals(numPerPage)){   
		              page =findOpenRusult(1, Integer.valueOf(lotteryPageSize), Integer.valueOf(type));   
		          }else{   
		              page =findOpenRusult(Integer.valueOf(currentPage), Integer.valueOf(numPerPage), Integer.valueOf(type));      
		          }
		    	  if(null != page){
			          map.put("totalPage", page.getTotalPages());    
			          map.put("currentPage", page.getCurrentPage());    
			          map.put("totalRows", page.getTotalRows());    
			          map.put("numPerPage", page.getNumPerPage());    
			          map.put("rows", page.getResultList());    
			          responseBean.setData(map);
			          responseBean.setErrorCode(ResponseErrorMessage.SUCCESS); 
			          return responseBean;
		          }

	          }


	      } catch (Exception e1) {   
	          e1.printStackTrace();  
	          responseBean.setErrorCode(ResponseErrorMessage.ERROR);

	      } 	
	      return responseBean;
	}
	
	public Pagination findOpenRusult(Integer currentPage, Integer numPerPage, int lotterytType) {
		Pagination page = null;
		List<Map<String, Object>> Lists = new ArrayList<Map<String, Object>>();
		String[] test = new String[7];
		String[] test2 = new String[4];
		page = lotteryDao.findOpenRusult(currentPage, numPerPage, lotterytType);
		if (page != null) {
			List list = page.getResultList();
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> maps = new HashMap<String, Object>();
					Map mapRe = (Map) list.get(i);
					maps.put("id", mapRe.get("id"));
					maps.put("term", mapRe.get("term"));
					maps.put("result", mapRe.get("result"));
//					maps.put("prize_content", mapRe.get("prize_content"));
					String prize_content = mapRe.get("prize_content").toString();
					test = prize_content.split("@");
					for (int j = 0; j < test.length; j++) {
						if (j == 0) {
							test2 = test[0].split("\\|");
							if (test2.length == 4) {
                                if(lotterytType==13){
									maps.put("pl3zhxCount", test2[1]);
									maps.put("pl3zhxPrize", test2[3]);
								}else{
									maps.put("firstCount", test2[1]);
									maps.put("firstPrize", test2[3]);
								}

							}
		
						} else if (j == 1) {
							test2 = test[1].split("\\|");
							if (test2.length == 4) {
								if(lotterytType==13){
									maps.put("pl3zx3Count", test2[1]);
									maps.put("pl3zx3Prize", test2[3]);
								}else{
									maps.put("secCount", test2[1]);
									maps.put("secPrize", test2[3]);	
								}

							}
						} else if (j == 2) {
							test2 = test[2].split("\\|");
							if (test2.length == 4) {
								if(lotterytType==1){
									maps.put("r9firstCount", test2[1]);
									maps.put("r9firstPrize", test2[3]);
								}else if(lotterytType==13){
									maps.put("pl3zx6Count", test2[1]);
									maps.put("pl3zx6Prize", test2[3]);
								}else{
									maps.put("thirdCount", test2[1]);
									maps.put("thirdPrize", test2[3]);
								}
							}
						} else if (j == 3) {
							test2 = test[3].split("\\|");
							if (test2.length == 4) {
								if(lotterytType==1){
									maps.put("klyjCount", test2[1]);
									maps.put("klyjPrize", test2[3]);
								}else if(lotterytType==13){
									maps.put("pl5zxCount", test2[1]);
									maps.put("pl5zxPrize", test2[3]);
								}else{
									maps.put("forthCount", test2[1]);
									maps.put("forthPrize", test2[3]);
								}

							}
						} else if (j == 4) {
							test2 = test[4].split("\\|");
							if (test2.length == 4) {
								if(lotterytType==13){
									maps.put("pl5zxCount", test2[1]);
									maps.put("pl5zxPrize", test2[3]);
								}else{
									maps.put("fifthCount", test2[1]);
									maps.put("fifthPrize", test2[3]);
								}

							}
						} else if (j == 5) {
							test2 = test[5].split("\\|");
							if (test2.length == 4) {
									maps.put("sixCount", test2[1]);
									maps.put("sixPrize", test2[3]);

							}
						} else if (j == 6) {
							test2 = test[6].split("\\|");
							if (test2.length == 4) {
								if(lotterytType==23){
									maps.put("addfirstCount", test2[1]);
									maps.put("addfirstPrize", test2[3]);
								}

							}
						}else if (j == 7) {
							test2 = test[7].split("\\|");
							if (test2.length == 4) {
								if(lotterytType==23){
									maps.put("addsecCount", test2[1]);
									maps.put("addsecPrize", test2[3]);
								}
							}
						}else if (j == 8) {
							test2 = test[8].split("\\|");
							if (test2.length == 4) {
								if(lotterytType==23){
									maps.put("addthirdCount", test2[1]);
									maps.put("addthirdPrize", test2[3]);
								}
							}
						}else if (j == 9) {
							test2 = test[9].split("\\|");
							if (test2.length == 4) {
								if(lotterytType==23){
									maps.put("addforthCount", test2[1]);
									maps.put("addforthPrize", test2[3]);
								}
							}
						}else if (j == 10) {
							test2 = test[10].split("\\|");
							if (test2.length == 4) {
								if(lotterytType==23){
									maps.put("addfifthCount", test2[1]);
									maps.put("addfifthPrize", test2[3]);
								}
							}
						}else if (j == 11) {
							test2 = test[11].split("\\|");
							if (test2.length == 4) {
								if(lotterytType==23){
									maps.put("luckyCount", test2[1]);
									maps.put("luckyPrize", test2[3]);
								}
							}
						}
					}
					maps.put("open_time", DateTimeUtil.toDate(mapRe.get("open_time").toString()).getTime());
					maps.put("pool_award", mapRe.get("pool_award"));
					Lists.add(maps);
				}
			}
		}
		page.setResultList(Lists);
		return page;
	}

}



	
	