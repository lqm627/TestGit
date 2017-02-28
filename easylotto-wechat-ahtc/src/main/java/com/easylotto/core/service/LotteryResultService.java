package com.easylotto.core.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easylotto.commons.util.JedisUtil;
import com.easylotto.core.entity.LotteryTypeDef;
import com.easylotto.core.util.PickDataUtil;
import com.wechat.webapi.web.handler.LotteryResultHandlerProxy;

@Service
@Transactional
public class LotteryResultService{
	
	private static Logger logger = Logger.getLogger(LotteryResultService.class);
	
	@Autowired private LotteryResultHandlerProxy lotteryResultHandlerProxy;
	@Autowired private JedisUtil jedisTemplate;
	
	
	public void execute(String date) throws Exception {
		if(jedisTemplate.exists(date)){
			return ;
		}
		jedisTemplate.set(date, date, 60 * 10, TimeUnit.SECONDS);
		CloseableHttpClient client = PickDataUtil.createDefault();
		long time = System.currentTimeMillis();
		try {
			String url = "http://www.ahtycp.cn/kaijiang.ahtc?sdate="+date;
			// 执行get请求
			HttpGet httpGet = new HttpGet(url);
			HttpResponse httpResponse1 = client.execute(httpGet);
			time = System.currentTimeMillis() - time;
			HttpEntity entity = httpResponse1.getEntity();
			String responseString = EntityUtils.toString(entity);

			List<String> values = null;
			// 抓取数据
			try {
				Parser parser = new Parser(responseString);
				if (null != parser || !"".equals(parser)) {
					NodeFilter filter = null;
					NodeList nodeList = null;
					filter = new AndFilter(new TagNameFilter("table"), new HasAttributeFilter("class", "l_A"));
					nodeList = parser.parse(filter);
					for (int i = 0; i < nodeList.size(); i++) {
						if (nodeList.elementAt(i) instanceof TableTag) {
							TableTag tag = (TableTag) nodeList.elementAt(i);
							TableRow[] rows = tag.getRows();
							for (int j = 1; j < rows.length; j++) {
								TableRow row = (TableRow) rows[j];
								TableColumn[] columns = row.getColumns();
								for(TableColumn column : columns){
									if(StringUtils.isNotBlank(column.getStringText()) && StringUtils.startsWith(column.getStringText(), "<HR")){
										execute(values);
										values = new ArrayList<String>(0);
									}else{
										values.add(column.getStringText());
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				// 关闭流并释放资源
				if(null != client) client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			logger.info(" -------------------------------------------- 抓取开奖结果结束！ "+ date);
		} 
	}
	
	public void execute(List<String> values){
		if(null == values || 0 == values.size()) return ;
		Integer lotteryType = null;
		String value = values.get(0);
		if(StringUtils.contains(value, "七星彩")){
			lotteryType = LotteryTypeDef.SEVEN_STAR;
		}
		else 
			if(StringUtils.contains(value, "排列3")){
				lotteryType = LotteryTypeDef.PL_3;
			}
		else
			if(StringUtils.contains(value, "排列5")){
				lotteryType = LotteryTypeDef.PL_5;
			}
		else
			if(StringUtils.contains(value, "超级大乐透")){
				lotteryType = LotteryTypeDef.DLT;
			}
		if(null != lotteryType && 0 < lotteryType.intValue()){
			lotteryResultHandlerProxy.execute(values, lotteryType);
		}
	}

}
	
	