package com.easylotto.core.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.easylotto.commons.util.DateTimeUtil;
import com.easylotto.commons.util.HttpUtil;
import com.easylotto.commons.util.JedisUtil;
import com.easylotto.core.dao.BaseDao;
import com.easylotto.core.dao.EcpRichLotteryDAO;
import com.easylotto.core.dao.LotteryTermDao;
import com.easylotto.core.entity.EcpLottery;
import com.easylotto.core.entity.EcpLotteryOpenResult;
import com.easylotto.core.entity.EcpRichLottery;
import com.easylotto.core.entity.LotteryTypeDef;
import com.wechat.config.AhjcWxConfig;
import com.wechat.config.EmailConfig;
import com.wechat.webapi.util.EmailUtil;
import com.wechat.webapi.web.handler.LotteryResultHandler;
import com.wechat.webapi.web.model.ResponseBean;

/**
 * 彩期查询
 * @author wucx
 *
 */
@Service
@Transactional
@EnableConfigurationProperties(EmailConfig.class)
public class LotteryTermService extends BaseDao<EcpRichLottery> {
	
	@Autowired private EcpRichLotteryDAO ecpRichLotteryDAO;
	@Autowired private CommonService commonService;
	@Autowired private LotteryTermDao lotteryTermDao;
	@Autowired private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	
	@Value("${lottery.result.push.url}")
	private String url;
	/**
	 * 查询所有彩种的彩期信息
	 * @return
	 */
	public ResponseBean findAll(){
		ResponseBean rb = new ResponseBean();

		List<EcpLottery> list = new ArrayList<EcpLottery>();
		List<EcpRichLottery> tempList = ecpRichLotteryDAO.getAllOnSaleTerm();
		if (tempList != null) {
			for (EcpRichLottery ecpRichLottery : tempList) {
				EcpLottery ecpLottery = new EcpLottery();
				ecpLottery.setLotteryType(ecpRichLottery.getInt_lottery_type()+"");
				ecpLottery.setTerm(ecpRichLottery.getVc_term());
				ecpLottery.setDeadline(DateFormatUtils.format(ecpRichLottery.getDt_deadline(), "yyyy-MM-dd HH:mm:ss"));
				ecpLottery.setSysDateTime("");
				list.add(ecpLottery);
			}
			rb.setErrorCode("success");
			rb.setData(list);
		}else{//找不到数据
			rb.setErrorCode("error");
			rb.setErrorMsg("找不到数据！");
		}
		return rb;
	}
	
	/**
	 * 查询单个彩种的彩期信息
	 * @param lottery
	 * @return
	 */
	public ResponseBean find(int lottery){
		ResponseBean rb = new ResponseBean();
		
		//int lotteryType = Integer.parseInt(lottery);
		//EcpRichLottery ecpRichLottery = lotteryService.getOnSaleTerm(lottery);
		JSONObject respObject = findJSONObject(lottery);
		if (respObject != null) {
//			respObject.put("lotteryType",  ecpRichLottery.getInt_lottery_type()+"");
//			respObject.put("term",  ecpRichLottery.getVc_term());
//			respObject.put("deadline",  DateFormatUtils.format(ecpRichLottery.getDt_deadline(), "yyyy-MM-dd HH:mm:ss"));
//			respObject.put("sysDateTime", DateFormatUtils.format(ecpRichLottery.getSysdate(), "yyyy-MM-dd HH:mm:ss"));
//			respObject.put("maxTerm", commonService.getMaxTerm(ecpRichLottery.getInt_lottery_type()));//彩种当前最大彩期
			
			rb.setErrorCode("success");
			rb.setData(respObject);
		}else{
			rb.setErrorCode("error");
			rb.setErrorMsg("找不到数据！");
		}
		return rb;
	}
	
	public JSONObject findJSONObject(int lottery){
		EcpRichLottery ecpRichLottery = ecpRichLotteryDAO.getOnSaleTerm(lottery);
		JSONObject respObject = new JSONObject();
		if (ecpRichLottery != null) {
			respObject.put("lotteryType",  ecpRichLottery.getInt_lottery_type()+"");
			respObject.put("term",  ecpRichLottery.getVc_term());
			respObject.put("deadline",  DateFormatUtils.format(ecpRichLottery.getDt_deadline(), "yyyy-MM-dd HH:mm:ss"));
			respObject.put("sysDateTime", DateFormatUtils.format(ecpRichLottery.getSysdate(), "yyyy-MM-dd HH:mm:ss"));
			respObject.put("maxTerm", commonService.getMaxTerm(ecpRichLottery.getInt_lottery_type()));//彩种当前最大彩期
			respObject.put("restTime", getRestSecond(ecpRichLottery.getDt_deadline(), ecpRichLottery.getSysdate()));
			
			return respObject;
		}
		return null;
	}
	
	/**
	 * 获取两个时间之间剩余的秒数
	 * @param deadline
	 * @param cur
	 * @return
	 */
	public long getRestSecond(Date deadline, Date cur){
		if(cur.before(deadline)){
			long rest = deadline.getTime() - cur.getTime();
			return rest/1000;
		}
		return 0;
	}
	
	
	public EcpLotteryOpenResult findByTermAndType(String term, Integer lotteryType) {
		return this.lotteryTermDao.findByTermAndType(term, lotteryType);
	}
	
	public EcpRichLottery findByExample(String term, Integer lotteryType) {
		return this.lotteryTermDao.findByExample(term, lotteryType);
	}
	
	@Autowired private EmailConfig config;
	
	public boolean checkAndSave(EcpLotteryOpenResult bean, LotteryResultHandler handler) {
		synchronized (bean) {
			if(StringUtils.isBlank(bean.getVc_term()) || StringUtils.isBlank(bean.getVc_code_content())) return false;
			if(checkUnique(bean.getVc_term(), bean.getInt_lottery_type())){
				Integer lotteryType = bean.getInt_lottery_type();
				if (lotteryType.intValue() == LotteryTypeDef.PL_3.intValue()) {
					lotteryType = LotteryTypeDef.PL_5;
				}
				EcpRichLottery lottery = findByExample(bean.getVc_term(), lotteryType);
				if(null == lottery){
					Map<String, String> lotterys = new HashMap<String, String>();
					lotterys.put("4", "七星彩");
					lotterys.put("13", "排列3排列5");
					lotterys.put("14", "排列3排列5");
					lotterys.put("23", "超级大乐透");
					EmailUtil.sendToGroup("lottery_term_error", "体彩安徽微信系统抓取开奖结果缺少"+lotterys.get(lotteryType+"")+"（"+bean.getVc_term()+"）期彩期，请及时跟进！ No.14", config);
					return false;
				}
				
//				if (lottery.getDt_open_time().after(new Date())) {
//					return false;
//				}
				bean.setDt_open_time(lottery.getDt_open_time());
				bean.setVc_pool_award(handler.getPoolAward(bean.getVc_term()));
				save(bean);
				return true;
			}
		}
		return false;
	}
	
	public void push(EcpLotteryOpenResult bean){
		final String _url = StringUtils.replace(url, "{type}", bean.getInt_lottery_type()+"");
		if(StringUtils.isNotBlank(_url) && !"null".equals(_url)){
			String[] urls = {_url};
			if(_url.contains(",")){
				urls = _url.split(",");
			}
			for(final String url : urls){
				threadPoolTaskExecutor.execute(new Runnable() {
					public void run() {
						List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
						list.add(new BasicNameValuePair("request_data", ""));
						HttpUtil.post(url, list);
					}
				});
			}
		}
	}
	
	
	
	public void save(EcpLotteryOpenResult bean){
		if(null == bean.getDt_open_time()){
			String openDateTime = DateTimeUtil.toString(DateTimeUtil.YYYY_MM_DD);
			openDateTime += " 00:00:00";
			bean.setDt_open_time(DateTimeUtil.toDate(openDateTime));
		}
		Date now = new Date(System.currentTimeMillis());
		bean.setDt_create_time(now);
		bean.setVc_input_name("system");// 录入人名字
		bean.setVc_adut_name("system");// 审核人名字
		this.lotteryTermDao.save(bean);
	}
	
	public boolean checkUnique(String term, Integer type) {
		EcpLotteryOpenResult bean = findByTermAndType(term, type);
		if(null == bean) return true;
		return false;
	}
}
