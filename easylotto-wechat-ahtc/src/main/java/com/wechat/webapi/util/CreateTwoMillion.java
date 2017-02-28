package com.wechat.webapi.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.mysql.jdbc.Connection;

public class CreateTwoMillion {


	public static void insertActivityCodeTwoMillion(Set<String> keys) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/wechat?useUnicode=true&characterEncoding=UTF-8", "root", "");
        // 关闭事务自动提交
        con.setAutoCommit(false);

        PreparedStatement pst = (PreparedStatement) con.prepareStatement("insert into ecp_activity_code ( INT_STATE, INT_WIN_STATE, DT_END_DATE, VC_CHECK_CODE, VC_LOGISTICS_CODE) values ( 1, 0, '2016-12-31 23:59:59', ?, ?)");
        for(String key : keys){
        	pst.setString(1, StringUtils.substring(key, 10));
        	pst.setString(2, StringUtils.substring(key, 0, 10));
            pst.addBatch();
        }
        // 执行批量更新
        pst.executeBatch();
        // 语句执行完毕，提交本事务
        con.commit();
        pst.close();
        con.close();
	}
	
	public static Set<String> read(){
		BufferedReader bufReader = null;
		Set<String> keys = new HashSet<String>();
		try {
			bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/Users/chenrixiang/Desktop/099916001.JYSJ")), "UTF-8")); 
			String temp = null;
			while ((temp = bufReader.readLine()) != null) {
				keys.add(new String(temp.getBytes(), "UTF-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufReader != null) {
				try {
					bufReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return keys;
	}
	
	
//	  public static void main(String[] args) {
//		    try {
//		    	Set<String> keys = read();
//		    	insertActivityCodeTwoMillion(keys);
////		    	String key = "2749295756f6d18a1e16cb0fc5087e6fda1dc5597e";
////		    	 System.out.println(StringUtils.substring(key, 0, 10));
////		    	 System.out.println(StringUtils.substring(key, 10));
//		    	
//		    }
//		    catch (Exception e) {
//		      e.printStackTrace();
//		    }
//		    
//		    
//		}

}