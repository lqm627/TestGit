package com.easylotto.core.dao.user;
import org.springframework.stereotype.Repository;

import com.easylotto.core.dao.BaseDao;
import com.easylotto.core.entity.user.EcpUserLog;

/**
 * @源文件：EcpUserLogDAO.java
 * @日 期：2012-06-04
 * @内 容：用户操作日志数据服务类
 * @author huangjun
 */
@Repository
public class EcpUserLogDAO extends BaseDao<EcpUserLog> {

	/**
	 * 添加用户操作日志
	 * 
	 * @param ecpUserLog
	 */
	public void save(EcpUserLog ecpUserLog) {

		String sql = " insert into ecp_user_log "
				+ "(dt_opt_time,int_account,int_opt_method,int_opt_type,"
				+ "vc_new_info,vc_old_info,vc_opt_name) values ( now(),?,?,?,?,?,?)";
		Object[] args = { 
				ecpUserLog.getInt_account(), ecpUserLog.getInt_opt_method(),
				ecpUserLog.getInt_opt_type(), ecpUserLog.getVc_new_info(),
				ecpUserLog.getVc_old_info(), ecpUserLog.getVc_opt_name() };
		getJdbcTemplate().update(sql, args);
	}

}
