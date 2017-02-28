package com.wechat.webapi.web.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springside.modules.constants.MediaTypes;

import com.alibaba.fastjson.JSONArray;
import com.easylotto.commons.util.DateTimeUtil;
import com.easylotto.core.entity.ActivityLog;
import com.easylotto.core.entity.EcpAccessLog;
import com.easylotto.core.entity.UploadFile;
import com.easylotto.core.entity.VoteCumulative;
import com.easylotto.core.entity.user.EcpUserKey;
import com.easylotto.core.service.ActivityLogService;
import com.easylotto.core.service.userCenter.EcpUserInfoService;
import com.easylotto.core.util.UploadUtil;
import com.wechat.webapi.util.IPAddrUtil;
import com.wechat.webapi.web.common.ResponseErrorMessage;
import com.wechat.webapi.web.model.ResponseBean;
import com.wechat.webapi.web.thread.UserAccessThread;

import net.sf.json.JSONObject;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;

@RestController
@RequestMapping("/activity")
public class ActivityController extends BaseController {

	@Autowired
	private ActivityLogService activityLogService;

	@Autowired
	private EcpUserInfoService ecpUserInfoService;

	private Lock lock = new ReentrantLock();

	@Value("${ahwechat.test.state}")
	private boolean state;
	
	@Value("${upload.url}")
	private String uploadUrl;

	@RequestMapping(value = "/voteActivityFrom", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	public ResponseBean voteActivityFrom(@RequestParam String request_data, HttpServletRequest request,
			HttpServletResponse response) {
		ResponseBean responseBean = new ResponseBean();
		try {
			lock.lock();
			EcpUserKey ecpUserKey = super.getEcpUserKey(request, null);
			if (ecpUserKey != null) {
				String ua = request.getHeader("User-Agent");
				UserAgent userAgent = UserAgent.parseUserAgentString(ua);
				OperatingSystem os = userAgent.getOperatingSystem();
				ecpUserInfoService.updateUserOS(ecpUserKey.getId(), os.getName());
			} else {
				// TODO
				// responseBean.setErrorCode(ResponseErrorMessage.TIMEOUT);
				// return super.handleResponse(responseBean, response);
			}
			if ("{}".equals(request_data)) {
				responseBean.error();
			} else {
				Map<String, Object> map = new HashMap<String, Object>();
				JSONObject jsonobject = JSONObject.fromObject(request_data);

				// TODO
				activity29(responseBean, map, ecpUserKey, 29, jsonobject);
			}

		} catch (Exception e) {
			e.printStackTrace();
			responseBean.error();
		} finally {
			lock.unlock();
		}

		return super.handleResponse(responseBean, response);
	}

	@RequestMapping(value = "/coupletsActivityFrom", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	public ResponseBean coupletsActivityFrom(@RequestParam String request_data, HttpServletRequest request,
			HttpServletResponse response) {
		ResponseBean responseBean = new ResponseBean();
		try {
			lock.lock();
			EcpUserKey ecpUserKey = super.getEcpUserKey(request, null);
			if (ecpUserKey != null) {
				String ua = request.getHeader("User-Agent");
				UserAgent userAgent = UserAgent.parseUserAgentString(ua);
				OperatingSystem os = userAgent.getOperatingSystem();
				ecpUserInfoService.updateUserOS(ecpUserKey.getId(), os.getName());
			} else {
				// TODO
				responseBean.setErrorCode(ResponseErrorMessage.TIMEOUT);
				return super.handleResponse(responseBean, response);
			}
			if ("{}".equals(request_data)) {
				responseBean.error();
			} else {
				Map<String, Object> map = new HashMap<String, Object>();
				JSONObject jsonobject = JSONObject.fromObject(request_data);

				// TODO
				activity30(responseBean, map, ecpUserKey, 30, jsonobject);
			}

		} catch (Exception e) {
			e.printStackTrace();
			responseBean.error();
		} finally {
			lock.unlock();
		}

		return super.handleResponse(responseBean, response);
	}

	@RequestMapping(value = "/getVoteLists", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	public ResponseBean getVoteLists(HttpServletRequest request, HttpServletResponse response) {
		ResponseBean responseBean = new ResponseBean();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<VoteCumulative> list = activityLogService.getVoteCumulativelist();
			map.put("voteLists", list);
			responseBean.setData(map);
			responseBean.setErrorCode(ResponseErrorMessage.SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setErrorCode(ResponseErrorMessage.ERROR);
		}

		return super.handleResponse(responseBean, response);
	}
	
	@RequestMapping(value = "/tggjsjActivityFrom", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	public ResponseBean tggjsjActivityFrom( HttpServletRequest request,
			HttpServletResponse response) {
		ResponseBean responseBean = new ResponseBean();
		try {
			lock.lock();
			EcpUserKey ecpUserKey = super.getEcpUserKey(request, null);
			if (ecpUserKey != null) {
				String ua = request.getHeader("User-Agent");
				UserAgent userAgent = UserAgent.parseUserAgentString(ua);
				OperatingSystem os = userAgent.getOperatingSystem();
				ecpUserInfoService.updateUserOS(ecpUserKey.getId(), os.getName());
			} else {
				// TODO
				responseBean.setErrorCode(ResponseErrorMessage.TIMEOUT);
				return super.handleResponse(responseBean, response);
			}
//			if ("{}".equals(request_data)) {
//				responseBean.error();
//			} else {
				Map<String, Object> map = new HashMap<String, Object>();
//				JSONObject jsonobject = JSONObject.fromObject(request_data);

				// TODO
				activity31(responseBean, map, ecpUserKey, 31,request);
//			}

		} catch (Exception e) {
			e.printStackTrace();
			responseBean.error();
		} finally {
			lock.unlock();
		}

		return super.handleResponse(responseBean, response);
	}

	// 检测用户是否已经参加了某活动
	@RequestMapping(value = "/findActivityMember", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	public ResponseBean findActivityMember(@RequestParam String request_data, HttpServletRequest request,
			HttpServletResponse response) {
		ResponseBean responseBean = new ResponseBean();
		try {
			logger.info("request_data ---------->>>    " + request_data);
			EcpUserKey ecpUserKey = super.getEcpUserKey(request, null);
			if (null == ecpUserKey) {
				// responseBean.setErrorCode(ResponseErrorMessage.TIMEOUT);
				// return super.handleResponse(responseBean, response);
			}
			if ("{}".equals(request_data)) {
				responseBean.error();
			} else {
				Map<String, Object> map = new HashMap<String, Object>();
				JSONObject jsonobject = JSONObject.fromObject(request_data);

				int activityId = Integer.valueOf(jsonobject.getString("int_activity_id"));
				if (activityId == 29) {
					activity29(responseBean, map, ecpUserKey, activityId, null);
				} else if (activityId == 30) {
					List<ActivityLog> list = null;
					if (null != ecpUserKey) {
						list = activityLogService.findCoupletsAccountJoinTimes(ecpUserKey.getId(), activityId);
					}
					if (null != list && list.size() >= 2) {
						map.put("resultState", 2);
						map.put("message", "您已提交满两副作品，感谢您的参与！");
						responseBean.setData(map);
						responseBean.success();
					} else {
						activity30(responseBean, map, ecpUserKey, activityId, null);
					}
				} else if (activityId == 31) {
					int joinTimes=activityLogService.findActivityAccountJoinTimes(ecpUserKey.getId(), activityId);
					if(joinTimes>0){
						    ActivityLog activityLog=activityLogService.getActivityLog(ecpUserKey.getId(), activityId);
							map.put("vc_name", activityLog.getVc_name());
							map.put("vc_phone", activityLog.getVc_phone());
							map.put("vc_card_no", activityLog.getVc_card_no());
//							responseBean.setData(map);
//							responseBean.success();
					}
				    map.put("joinTimes", joinTimes);
					activity31(responseBean, map, ecpUserKey, activityId,request);
				}

				EcpAccessLog accessLog = new EcpAccessLog();
				if (ecpUserKey != null) {
					long userId = ecpUserKey.getId();
					accessLog.setInt_account_id(userId);
				}
				String ip = IPAddrUtil.getIpAddress(request);
				Date accTime = new Date(System.currentTimeMillis());
				accessLog.setInt_model_type(2); // 2表示活动模块
				accessLog.setInt_model_id(Long.valueOf(activityId)); // 活动ID
				accessLog.setVc_ip(ip);
				accessLog.setDt_read_time(accTime);
				new Thread(new UserAccessThread(accessLog)).start();
			}

		} catch (Exception e) {
			responseBean.setErrorCode(ResponseErrorMessage.ERROR);
			logger.error("", e);
		}
		return super.handleResponse(responseBean, response);
	}

	private void activity29(ResponseBean responseBean, Map<String, Object> map, EcpUserKey ecpUserKey, int activityId,
			JSONObject jsonobject) {

		java.util.Date todayStartTime = DateTimeUtil.toDate("2016-11-07 00:00:00");
		java.util.Date todayEndTime = DateTimeUtil.toDate("2016-11-20 23:59:59");
		String dataBaseTime = "";
		if (state) {
			dataBaseTime = "2016-10-27 00:00:00";
		} else {
			dataBaseTime = activityLogService.queryDateTime();
		}

		java.util.Date dataBaseTimeNow = DateTimeUtil.toDate(dataBaseTime);
		if (dataBaseTimeNow.compareTo(todayStartTime) == 1 && dataBaseTimeNow.compareTo(todayEndTime) == -1) {
			// int
			// joinTimes=activityLogService.findActivityAccountJoinTimes(ecpUserKey.getId(),
			// activityId);
			// if(joinTimes>=9){
			// map.put("resultState", joinTimes);
			// map.put("message", "您的投票限额已满！");
			// responseBean.setData(map);
			// responseBean.success();
			// }
			if (null != jsonobject) {
				int sunbmitState = 0;
				String message = "";
				JSONArray jsonArray = JSONArray.parseArray(jsonobject.getString("voteStr"));
				Iterator<Object> it = jsonArray.iterator();
				List<VoteCumulative> list = activityLogService.getVoteCumulativelist();
				while (it.hasNext()) {
					Long voteId = Long.valueOf(it.next().toString());
					ActivityLog activityLog = new ActivityLog();
					activityLog.setInt_account_id(Long.valueOf(0));
					activityLog.setInt_activity_id(Long.valueOf(29));
					activityLog.setInt_vote_cumulative_id(voteId);
					activityLogService.save(activityLog);

					VoteCumulative voteCumulative = new VoteCumulative();
					voteCumulative.setInt_rec_id(voteId);
					voteCumulative.setInt_cumulative(list.get(voteId.intValue() - 1).getInt_cumulative() + 1);
					activityLogService.updateVoteCumuative(voteCumulative);

					message += "作品" + voteId + "投票成功,";
					sunbmitState++;
				}
				message = message.substring(0, message.length() - 1);
				if (sunbmitState == 3) {
					map.put("message", message);
					map.put("sunbmitState", sunbmitState);
				} else {
					map.put("message", "投票失败！");
					map.put("sunbmitState", sunbmitState);
				}
				responseBean.setData(map);
				responseBean.success();
			} else {
				responseBean.setData(null);
				responseBean.success();
			}
		} else if (dataBaseTimeNow.compareTo(todayStartTime) == -1) {
			map.put("dataBaseTimeResult", -1);
			map.put("message", "活动未开始！");
			responseBean.setData(map);
			responseBean.success();
		} else if (dataBaseTimeNow.compareTo(todayEndTime) == 1) {
			map.put("dataBaseTimeResult", 1);
			map.put("message", "活动已结束！");
			responseBean.setData(map);
			responseBean.success();
		}
	}

	private void activity30(ResponseBean responseBean, Map<String, Object> map, EcpUserKey ecpUserKey, int activityId,
			JSONObject jsonobject) {
		String firstUpperCouplet = "";
		String firstLowerCouplet = "";
		String firstCoupletRyhming = "";

		String secondUpperCouplet = "";
		String secondLowerCouplet = "";
		String secondCoupletRyhming = "";

		String firstSunbmit = "";
		String secondSunbmit = "";
		String message = "";

		boolean firstFlag = false;
		boolean secondFlag = false;

		List<ActivityLog> list = null;

		java.util.Date todayStartTime = DateTimeUtil.toDate("2016-11-25 00:00:00");
		java.util.Date todayEndTime = DateTimeUtil.toDate("2016-12-15 23:59:59");
		String dataBaseTime = "";
		if (state) {
			dataBaseTime = "2016-11-26 00:00:00";
		} else {
			dataBaseTime = activityLogService.queryDateTime();
		}

		java.util.Date dataBaseTimeNow = DateTimeUtil.toDate(dataBaseTime);
		if (dataBaseTimeNow.compareTo(todayStartTime) == 1 && dataBaseTimeNow.compareTo(todayEndTime) == -1) {
			if (null != ecpUserKey) {
				list = activityLogService.findCoupletsAccountJoinTimes(ecpUserKey.getId(), 30);
			}
			if (null != jsonobject) {
				int result = activityLogService.findActivityMemberPhoneOnlyOneWithoutMyself(
						jsonobject.getString("vc_phone"), activityId, ecpUserKey.getId().intValue());
				if (null != list && list.size() >= 2) {
					map.put("resultState", 2);
					message = "您已提交满两副作品，感谢您的参与！";
					map.put("message", message);
					responseBean.setData(map);
				} else if (result > 0) {
					map.put("resultState", 0);
					message = "您已参与过活动，不可重复参与";
					map.put("message", message);
					responseBean.setData(map);
				} else {
					// 第一副春联
					if (jsonobject.containsKey("vc_upper_couplet_first")
							&& !jsonobject.getString("vc_upper_couplet_first").equals("")) {
						firstUpperCouplet = jsonobject.getString("vc_upper_couplet_first");
					}
					if (jsonobject.containsKey("vc_lower_couplet_first")
							&& !jsonobject.getString("vc_lower_couplet_first").equals("")) {
						firstLowerCouplet = jsonobject.getString("vc_lower_couplet_first");
					}
					if (jsonobject.containsKey("vc_couplet_ryhming_first")
							&& !jsonobject.getString("vc_couplet_ryhming_first").equals("")) {
						firstCoupletRyhming = jsonobject.getString("vc_couplet_ryhming_first");
					}
					// 第二副春联
					if (jsonobject.containsKey("vc_upper_couplet_second")
							&& !jsonobject.getString("vc_upper_couplet_second").equals("")) {
						secondUpperCouplet = jsonobject.getString("vc_upper_couplet_second");
					}
					if (jsonobject.containsKey("vc_lower_couplet_second")
							&& !jsonobject.getString("vc_lower_couplet_second").equals("")) {
						secondLowerCouplet = jsonobject.getString("vc_lower_couplet_second");
					}
					if (jsonobject.containsKey("vc_couplet_ryhming_second")
							&& !jsonobject.getString("vc_couplet_ryhming_second").equals("")) {
						secondCoupletRyhming = jsonobject.getString("vc_couplet_ryhming_second");
					}

					if (!firstUpperCouplet.equals("") && !firstLowerCouplet.equals("")
							&& !firstCoupletRyhming.equals("")) {
						firstFlag = true;
					}
					if (!secondUpperCouplet.equals("") && !secondLowerCouplet.equals("")
							&& !secondCoupletRyhming.equals("")) {
						secondFlag = true;
					}
					if (firstFlag) {
						if (null != list && list.size() == 0) {
							ActivityLog activityLog = new ActivityLog();
							activityLog.setInt_activity_id(Long.valueOf(30));
							activityLog.setInt_account_id(ecpUserKey.getId());
							activityLog.setVc_name(jsonobject.getString("vc_name"));
							activityLog.setVc_phone(jsonobject.getString("vc_phone"));
							activityLog.setInt_vote_cumulative_id(Long.valueOf(0));
							activityLog.setVc_upper_couplet(firstUpperCouplet);
							activityLog.setVc_lower_couplet(firstLowerCouplet);
							activityLog.setVc_couplet_ryhming(firstCoupletRyhming);
							activityLogService.save(activityLog);
							message = "春联一提交成功";
							firstSunbmit = "1";
							map.put("message", message);
							responseBean.setData(map);
						}
					}

					if (secondFlag) {
						ActivityLog activityLog = new ActivityLog();
						activityLog.setInt_activity_id(Long.valueOf(30));
						activityLog.setInt_account_id(ecpUserKey.getId());
						activityLog.setVc_name(jsonobject.getString("vc_name"));
						activityLog.setVc_phone(jsonobject.getString("vc_phone"));
						activityLog.setInt_vote_cumulative_id(Long.valueOf(0));
						activityLog.setVc_upper_couplet(secondUpperCouplet);
						activityLog.setVc_lower_couplet(secondLowerCouplet);
						activityLog.setVc_couplet_ryhming(secondCoupletRyhming);
						activityLogService.save(activityLog);
						if (firstSunbmit.equals("1")) {
							message = message + ",春联二提交成功";
						} else {
							message = "春联二提交成功";
						}
						secondSunbmit = "1";
						map.put("message", message);
						responseBean.setData(map);
					}
					if (firstSunbmit.equals("1") || secondSunbmit.equals("1")) {
						List<ActivityLog> newlist = activityLogService.findCoupletsAccountJoinTimes(ecpUserKey.getId(),
								30);
						message = message + ",您已提交" + newlist.size() + "副作品,还可以提交" + (2 - newlist.size()) + "副作品";
						map.put("message", message);
						map.put("submittedResult", 1);
						responseBean.setData(map);
					} else {
						message = "提交失败";
						map.put("message", message);
						map.put("submittedResult", 0);
						responseBean.setData(map);
					}

				}

			} else {
				if (list != null && list.size() != 0) {
					map.put("rows", list);
					responseBean.setData(map);
				} else {
					responseBean.setData(null);
				}
			}
			responseBean.success();
		} else if (dataBaseTimeNow.compareTo(todayStartTime) == -1) {
			map.put("dataBaseTimeResult", -1);
			map.put("message", "活动未开始！");
			responseBean.setData(map);
			responseBean.success();
		} else if (dataBaseTimeNow.compareTo(todayEndTime) == 1) {
			map.put("dataBaseTimeResult", 1);
			map.put("message", "活动已结束！");
			responseBean.setData(map);
			responseBean.success();
		}
	}

	private void activity31(ResponseBean responseBean, Map<String, Object> map, EcpUserKey ecpUserKey, int activityId,HttpServletRequest request) {

		java.util.Date todayStartTime = DateTimeUtil.toDate("2017-01-12 00:00:00");
		java.util.Date todayEndTime = DateTimeUtil.toDate("2017-02-17 23:59:59");
		String dataBaseTime = "";
		if (state) {
			dataBaseTime = "2017-01-13 00:00:00";
		} else {
			dataBaseTime = activityLogService.queryDateTime();
		}      
		java.util.Date dataBaseTimeNow = DateTimeUtil.toDate(dataBaseTime);
	    String vcPhone=request.getParameter("vc_phone");
	    String vcName=request.getParameter("vc_name");
	    String vcCardNo=request.getParameter("vc_card_no");
	    String vcSerialNmuber=request.getParameter("vc_serial_number");
		if (dataBaseTimeNow.compareTo(todayStartTime) == 1 && dataBaseTimeNow.compareTo(todayEndTime) == -1) {
			if (vcPhone!=null&&vcName!=null&&vcCardNo!=null&&vcSerialNmuber!=null) {
			    String vcSerialNumberOnlyone=activityLogService.findSerialNumberOnlyone(vcSerialNmuber,activityId);
				int result = activityLogService.findActivityMemberPhoneOnlyOneWithoutMyself(
						vcPhone, activityId, ecpUserKey.getId().intValue());
				if(vcSerialNumberOnlyone!=null){
        			map.put("resultState", "0");
        			map.put("message", "彩票序列号已存在！");
				}else if(result>0){
        			map.put("resultState", "1");
        			map.put("message", "您的手机号已被使用！");
                }else{
                	UploadFile file = UploadUtil.upload(request, "", uploadUrl, null);
            		if (file != null && file.isUploadSuccess()) {
            			System.out.println(file.getPath());
            			ActivityLog activityLog=new ActivityLog();
                    	activityLog.setInt_activity_id(Long.valueOf(activityId));
                    	activityLog.setInt_account_id(ecpUserKey.getId());
                    	activityLog.setVc_phone(vcPhone);
                    	activityLog.setVc_name(vcName);
                    	activityLog.setVc_card_no(vcCardNo);
                    	activityLog.setVc_pic_url(file.getPath());
                    	activityLog.setVc_serial_number(vcSerialNmuber);
                    	activityLog.setInt_vote_cumulative_id(Long.valueOf(0));
                    	activityLogService.save(activityLog);
                    	
    					map.put("sunbmitState", "1");
                    	map.put("message", "图片上传成功及信息提交成功！");
            		}else if(file != null &&file.isUploadSuccess()==false){
    					map.put("sunbmitState", "0");
                    	map.put("message", "图片上传失败及信息提交失败！");
            		}else if(file==null){
    					map.put("sunbmitState", "2");
                    	map.put("message", "上传图片大于8M或未选择图片！");
            		}
                	
                }

			}
			responseBean.setData(map);
			responseBean.success();
		} else if (dataBaseTimeNow.compareTo(todayStartTime) == -1) {
			map.put("dataBaseTimeResult", -1);
			map.put("message", "活动未开始！");
			responseBean.setData(map);
			responseBean.success();
		} else if (dataBaseTimeNow.compareTo(todayEndTime) == 1) {
			map.put("dataBaseTimeResult", 1);
			map.put("message", "活动已结束！");
			responseBean.setData(map);
			responseBean.success();
		}
	}
	
	/**
     * 异常处理
     * @param ex
     * @param request
     * @Description:
     */
//    @ExceptionHandler(Exception.class)
//    public void handleException(Exception ex,HttpServletRequest request,HttpServletResponse response){
//        StringBuffer sb = new StringBuffer();
//        sb.append("<script language='javascript'>history.go(-1);alert('");
//        if (ex instanceof org.springframework.web.multipart.MaxUploadSizeExceededException){
//            sb.append("文件大小不应大于"+((MaxUploadSizeExceededException)ex).getMaxUploadSize()/1000+"kb");
//         } else{
//             sb.append("上传异常！");
//        }
//        sb.append("！');</script>");
//        try {
//            System.out.println(sb.toString());
//            response.setContentType("text/html; charset=utf-8");  
//            response.getWriter().println(sb.toString());
//            response.getWriter().flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return;
//    }

	@RequestMapping(value = "/getDateBaseTime", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	public ResponseBean getDateBaseTime(HttpServletRequest request, HttpServletResponse response) {
		ResponseBean responseBean = new ResponseBean();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("dataBaseTime", activityLogService.queryDateTime());
			responseBean.setData(map);
			responseBean.setErrorCode(ResponseErrorMessage.SUCCESS);

		} catch (Exception e) {
			responseBean.setErrorCode(ResponseErrorMessage.ERROR);
			e.printStackTrace();
		}
		return super.handleResponse(responseBean, response);
	}

}