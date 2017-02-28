package com.easylotto.core.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
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

public class PickDataUtil {
	
	//private static StatData statData;
	
	public static CookieStore cookieStore = null;
	public static HttpClientContext context = null;
	
	static Logger log = Logger.getLogger(PickDataUtil.class);
	
	
	public static String replace(String[] levels, String prizeContent, String value, String name){
		String[]_values = null;
		for(String level : levels){
			_values = value.split(level);
			if(null == _values || 1 == _values.length) continue;
			String _tmpvalue = _values[1];
			_values = _tmpvalue.split("注");
			String count = _values[0];
			count = StringUtils.replace(count, " ", "");
			
			_tmpvalue = _values[1];
			_values = _tmpvalue.split("元");
			String prize = _values[0];
			prize = StringUtils.replace(prize, " ", "");
			
			prizeContent = StringUtils.replace(prizeContent, name + level + "注数", count);
			prizeContent = StringUtils.replace(prizeContent, name + level + "奖金", prize);
		}
		return prizeContent;
	}
	
	public static String getValue(String url) {
		try {
			CloseableHttpClient client = createDefault();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse httpResponse = client.execute(httpGet);
			HttpEntity entity = httpResponse.getEntity();
			String responseString = EntityUtils.toString(entity);
			return responseString;
		} catch (ParseException | IOException e) {
			log.error("", e);
		}
		return null;
	}
	
	
	public static String getPoolAward(String url) {
		try {
			CloseableHttpClient client = createDefault();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse httpResponse = client.execute(httpGet);
			HttpEntity entity = httpResponse.getEntity();
			String responseString = EntityUtils.toString(entity);
			if(StringUtils.isNotBlank(responseString) && StringUtils.contains(responseString, "滚入下期奖池")){
				String[] values = responseString.split("滚入下期奖池");
				String value = StringUtils.replace(values[1], "：", "");
				if(StringUtils.contains(value, " ")){
					values = value.split(" ");
					return StringUtils.trim(values[0]);
				}
			}
		} catch (ParseException | IOException e) {
			log.error("", e);
		}
		return null;
	}
	
	public static CloseableHttpClient createDefault(){
		return HttpClients.createDefault();//直接创建client
	}
	
	
//	public static void main(String[] args) {
//		try {
////			execute("");
////			System.out.println(StringUtils.replace("&nbsp;&nbsp;&nbsp;&nbsp;一等奖 ", "&nbsp;", ""));
//			
//			String[] values = "<font color=#dc2903 size=3><b>0 0 6 1 7 5 5</b></font>&nbsp;".split("</b></font>");
//			for(String value : values)
//				System.out.println(value);	
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	
	/**
	 * 抓取比赛时间数据	
	 * @param urlParameters [sport,match,cat,tournament]
	 * @return Map<sourceValue,startDate>
	 * @throws Exception
	 */
	public static Map<String, String> execute(String url) throws Exception {
		CloseableHttpClient client = PickDataUtil.createDefault();
		Map<String, String> result = new HashMap<String, String>();
		long time = System.currentTimeMillis();
		try {
			url = "http://www.ahtycp.cn/kaijiang.ahtc?sdate=20161218";
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
//					filter = new AndFilter
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
										if(null != values && 0 < values.size()){
											
										}
										System.out.println(" new ----" + column.getStringText());
										values = new ArrayList<String>(0);
									}else{
										System.out.println(column.getStringText());
									}
								}
//								String sourceValue= columns[0].toPlainTextString();
//								String startDateString = columns[1].toPlainTextString().trim().replace("&nbsp;","");
//								
//								result.put(sourceValue, startDateString);
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
		} 
		
		return result;
	}
	

	public static List<NameValuePair> getParam(Map parameterMap) {
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		Iterator it = parameterMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry parmEntry = (Entry) it.next();
			param.add(new BasicNameValuePair((String) parmEntry.getKey(), (String) parmEntry.getValue()));
		}
		return param;
	}
	
	
	/**
	 * 七星彩
	 **/
	public void lottery4(){
		
	}
	
//	七星彩 4
	
//	0,0,6,1,7,5,5
//	firstCount|1|firstPrize|1@secCount|1|secPrize|1@thirdCount|1|thirdPrize|1@forthCount|1|forthPrize|300.0@fifthCount|1|fifthPrize|20.0@sixthCount|1|sixthPrize|5.0
//	poolAward奖池
//	term 彩期
	
//	firstCount|1|firstPrize|11111@secCount|2|secPrize|22222@thirdCount|3|thirdPrize|3333@forthCount|4|forthPrize|300.0@fifthCount|5|fifthPrize|20.0@sixthCount|6|sixthPrize|5.0
	
}

