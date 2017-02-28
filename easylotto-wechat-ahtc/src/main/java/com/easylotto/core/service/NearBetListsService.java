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
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.easylotto.commons.util.DateTimeUtil;
import com.easylotto.commons.util.JedisUtil;
import com.easylotto.core.dao.LotteryDao;
import com.easylotto.core.dao.MemberDao;
import com.easylotto.core.dao.NearBetMapDao;
import com.easylotto.core.dao.NewsDao;
import com.easylotto.core.entity.Activity;
import com.easylotto.core.entity.EcpAccessLog;
import com.easylotto.core.entity.ILotteryType;
import com.easylotto.core.entity.Member;
import com.easylotto.core.entity.NearBetInfo;
import com.easylotto.core.entity.News;
import com.easylotto.core.entity.user.EcpSignIn;
import com.easylotto.core.entity.user.EcpUser;
import com.easylotto.core.entity.user.EcpUserFundLog;
import com.easylotto.core.entity.user.EcpUserKey;
import com.easylotto.core.service.userCenter.EcpUserInfoService;
import com.easylotto.core.util.HttpInvoker;
import com.wechat.webapi.util.IPAddrUtil;
import com.wechat.webapi.util.Pagination;
import com.wechat.webapi.util.SphericalDistance;
import com.wechat.webapi.web.common.ResponseErrorMessage;
import com.wechat.webapi.web.model.ResponseBean;
import com.wechat.webapi.web.thread.UserAccessThread;

import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang.StringUtils;

@Service
@Transactional
public class NearBetListsService  implements IClientService{
	@Autowired
	private NearBetMapDao nearBetMapDao;;
	
	public ResponseBean execute(String request_data,Long memberId,Map<String,String> parameterMap){
		  ResponseBean responseBean = new ResponseBean();
		  Map<String, Object> map = new HashMap<String, Object>();
			String longitude = "";
			String latitude = "";
			String resultbd09ll = "";
			JSONObject jsonObject;
			try {
                String nearbetPageSize=parameterMap.get("nearbetPageSize");
				if ("{}".equals(request_data)) {
					map.put("locationState", "未获取到您所在位置的坐标！");
					responseBean.setData(map);
					responseBean.setErrorCode(ResponseErrorMessage.SUCCESS);
					return responseBean;
				} else {
					jsonObject = JSONObject.parseObject(request_data);
					if (!jsonObject.containsKey("longitude") || !jsonObject.containsKey("latitude")) {
						map.put("locationState", "未获取到您所在位置的坐标！");
						responseBean.setData(map);
						responseBean.setErrorCode(ResponseErrorMessage.SUCCESS);
						return responseBean;
					} else {
						// 获得前端发送过来的GPS经纬度
						longitude = jsonObject.getString("longitude");
						latitude = jsonObject.getString("latitude");
						// 将GPS经纬度转换成百度坐标
						String requestUrl = "http://api.map.baidu.com/geoconv/v1/?ak=VNZgsCc8arIRZysYkRRwI34e&coords="
								+ longitude + "," + latitude + "&from=1";
						String result = HttpInvoker.httpGet(requestUrl);
						jsonObject = JSONObject.parseObject(result);
						resultbd09ll = jsonObject.getString("result");
						JSONArray jsonObject1 = JSONArray.parseArray(resultbd09ll);
						resultbd09ll = jsonObject1.getString(0);
						jsonObject = JSONObject.parseObject(resultbd09ll);
						// 获得转换后的百度坐标
						String longitudebd09ll = jsonObject.getString("x");
						String latitudebd09ll = jsonObject.getString("y");
						List<NearBetInfo> lists = nearBetMapDao.getNearBetList(longitudebd09ll, latitudebd09ll,
								Integer.valueOf(nearbetPageSize));
						for (int i = 0; i < lists.size(); i++) {
							lists.get(i)
									.setDistance((int) (SphericalDistance.GetDistance(Double.valueOf(longitudebd09ll),
											Double.valueOf(latitudebd09ll), Double.valueOf(lists.get(i).getVc_longitude()),
											Double.valueOf(lists.get(i).getVc_latitude()))));
						}
						map.put("rows", lists);
						responseBean.setData(map);
						responseBean.setErrorCode(ResponseErrorMessage.SUCCESS);
						return responseBean;
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		return responseBean;
	}
	

	
	

}
	
	