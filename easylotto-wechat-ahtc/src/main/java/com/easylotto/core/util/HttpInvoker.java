package com.easylotto.core.util;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.protocol.HTTP;

/**
 * HTTP请求类
 * @author LiHong
 */
public class HttpInvoker {

	static int timeOut = 90*1000;

	/**
	 * GET请求
	 * @param getUrl
	 * @throws IOException
	 * @return 提取HTTP响应报文包体，以字符串形式返回
	 */
	public static String httpGet(String getUrl) throws IOException { 
		URL getURL = new URL(getUrl); 
		HttpURLConnection connection = (HttpURLConnection) getURL.openConnection(); 
		connection.connect();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder sbStr = new StringBuilder();
		String line;
		while ((line = bufferedReader.readLine()) != null) { 
			sbStr.append(line); 
		} 
		bufferedReader.close();
		connection.disconnect(); 
		return new String(sbStr.toString().getBytes(),"utf-8");
	}

	/**
	 * POST请求
	 * @param postUrl
	 * @param postHeaders
	 * @param postEntity
	 * @throws IOException
	 * @return 提取HTTP响应报文包体，以字符串形式返回
	 */
	public static String httpPost(String postUrl,Map<String, String> postHeaders, String postEntity) throws IOException {

		URL postURL = new URL(postUrl); 
		HttpURLConnection httpURLConnection = (HttpURLConnection) postURL.openConnection(); 
		httpURLConnection.setDoOutput(true);                 
		httpURLConnection.setDoInput(true); 
		httpURLConnection.setRequestMethod("POST"); 
		httpURLConnection.setUseCaches(false); 
		httpURLConnection.setInstanceFollowRedirects(true); 
		httpURLConnection.setRequestProperty(" Content-Type ", " application/x-www-form-urlencoded ");

		if(postHeaders != null) {
			for(String pKey : postHeaders.keySet()) {
				httpURLConnection.setRequestProperty(pKey, postHeaders.get(pKey));
			}
		}
		if(postEntity != null) {
			DataOutputStream out = new DataOutputStream(httpURLConnection.getOutputStream()); 
			out.writeBytes(postEntity); 
			out.flush(); 
			out.close(); 
		}
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream())); 
		StringBuilder sbStr = new StringBuilder();
		String line;
		while ((line = bufferedReader.readLine()) != null) { 
			sbStr.append(line); 
		} 
		bufferedReader.close();
		httpURLConnection.disconnect(); 
		return new String(sbStr.toString().getBytes(),"utf-8");
	} 
	
	public static String httpPost(String url, String bodyInfo, int retryTimes) {
		String responseBody = null;
		DefaultHttpClient httpClient = null;
		try {
			httpClient = new DefaultHttpClient();
			httpClient.getParams().setParameter("http.protocol.content-charset", HTTP.UTF_8);
			httpClient.getParams().setParameter(HTTP.CONTENT_ENCODING, HTTP.UTF_8);
			httpClient.getParams().setParameter(HTTP.CHARSET_PARAM, HTTP.UTF_8);
			httpClient.getParams().setParameter(HTTP.DEFAULT_PROTOCOL_CHARSET, HTTP.UTF_8);
			HttpPost httpPost = new HttpPost(url); 
			httpPost.getParams().setParameter("http.protocol.content-charset", HTTP.UTF_8);
			httpPost.getParams().setParameter(HTTP.CONTENT_ENCODING, HTTP.UTF_8);
			httpPost.getParams().setParameter(HTTP.CHARSET_PARAM, HTTP.UTF_8);
			httpPost.getParams().setParameter(HTTP.DEFAULT_PROTOCOL_CHARSET, HTTP.UTF_8);
			StringEntity entity = new StringEntity(bodyInfo,"UTF-8");
			entity.setContentType("text/xml;charset=UTF-8");
			entity.setContentEncoding("UTF-8");
			httpPost.setEntity(entity);
			httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
			DefaultHttpRequestRetryHandler handler = new DefaultHttpRequestRetryHandler(retryTimes, false); 
			httpClient.setHttpRequestRetryHandler(handler); 
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			responseBody = httpClient.execute(httpPost, responseHandler);
		} catch (Exception e) {
		} finally {
			if (httpClient != null)
				httpClient.getConnectionManager().shutdown();
		}

		return responseBody;
	}

}
