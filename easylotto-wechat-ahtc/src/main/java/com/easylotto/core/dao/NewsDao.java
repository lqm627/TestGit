package com.easylotto.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import com.easylotto.core.dao.BaseDao;
import com.easylotto.core.entity.EcpNews;
import com.easylotto.core.entity.News;
import com.easylotto.core.util.FieldUtil;
import com.wechat.webapi.util.Pagination;

@Repository
public class NewsDao extends BaseDao<News> {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	

	
	public Pagination getNews(Integer currentPage,Integer numPerPage,int type) {    
        String sql="SELECT e.INT_REC_ID,t.VC_NAME,e.VC_TITLE,e.CB_CONTENT"
        		+ ",e.VC_PIC_URL,e.DT_NEWS_DATE,e.INT_NEWS_TYPE,e.VC_DIGEST,e.VC_HREF_URL,e.VC_MOV_URL,e.VC_MUS_URL "
        		+ "  FROM ecp_news_type t LEFT JOIN ecp_news e on t.INT_REC_ID=e.INT_NEWS_TYPE "
        		+ " WHERE t.INT_REC_ID="+type+""
        	    + " AND e.INT_STATUS = 0 "
        		+ " AND e.INT_WECHAT_TYPE IN(0,1)  ORDER BY e.INT_IS_TOP DESC,e.DT_NEWS_DATE DESC,e.INT_REC_ID DESC";  
        Pagination page=new Pagination(sql, currentPage, numPerPage,  jdbcTemplate);  
        return page;      
    }  
	
	public List<News> getWinOrPromotionList(int newsType){
		String sql="SELECT e.INT_REC_ID,t.VC_NAME,e.VC_TITLE,e.CB_CONTENT,e.VC_PIC_URL,e.DT_NEWS_DATE,e.INT_NEWS_TYPE,e.VC_DIGEST,e.VC_HREF_URL,e.VC_MOV_URL,e.VC_MUS_URL   "
				+ " FROM ecp_news_type t LEFT JOIN ecp_news e on t.INT_REC_ID=e.INT_NEWS_TYPE "
				+ " WHERE t.INT_REC_ID="+newsType+" AND e.INT_STATUS=0 ";
		if(newsType==6){
			   sql+=" AND e.INT_WECHAT_TYPE IN(0,1) ";
		}
               sql+=" ORDER BY e.INT_IS_TOP DESC,e.DT_NEWS_DATE DESC,e.INT_REC_ID DESC LIMIT 0,8";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<News>(News.class));
	}
	
	public List<News> getPoliciesOrGameRuleList(int newsType){
		String sql="SELECT e.INT_REC_ID,t.VC_NAME,e.VC_TITLE,e.CB_CONTENT,e.VC_PIC_URL,e.DT_NEWS_DATE,e.INT_NEWS_TYPE,e.VC_DIGEST,e.VC_HREF_URL,e.VC_MOV_URL,e.VC_MUS_URL FROM ecp_news_type t LEFT JOIN ecp_news e on t.INT_REC_ID=e.INT_NEWS_TYPE WHERE t.INT_REC_ID="+newsType+" AND e.INT_STATUS=0 ORDER BY e.INT_IS_TOP DESC,e.DT_NEWS_DATE DESC,e.INT_REC_ID DESC";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<News>(News.class));
	}
	
	public Map<String,Object> getNewsDetail(int newsId){
		String sql="SELECT e.INT_REC_ID,t.VC_NAME,e.VC_TITLE,e.CB_CONTENT,e.VC_PIC_URL,e.DT_NEWS_DATE,e.INT_NEWS_TYPE,e.VC_DIGEST,e.VC_HREF_URL,e.VC_MOV_URL,e.VC_MUS_URL FROM ecp_news_type t LEFT JOIN ecp_news e on t.INT_REC_ID=e.INT_NEWS_TYPE where e.INT_REC_ID="+newsId+"  AND e.INT_STATUS=0";
		return jdbcTemplate.queryForMap(sql);
	}
	
	public News getNewsBanner(int newsType){
		String sql="SELECT e.INT_REC_ID,t.VC_NAME,e.VC_TITLE,e.CB_CONTENT,e.VC_PIC_URL,e.DT_NEWS_DATE,e.INT_NEWS_TYPE,e.VC_DIGEST,e.VC_HREF_URL,e.VC_MOV_URL,e.VC_MUS_URL FROM ecp_news_type t LEFT JOIN ecp_news e on t.INT_REC_ID=e.INT_NEWS_TYPE WHERE t.INT_REC_ID=? AND e.INT_STATUS=0 AND e.INT_WECHAT_TYPE IN(0,1)  ORDER BY e.INT_IS_TOP DESC,e.DT_NEWS_DATE DESC,e.INT_REC_ID DESC";
		return get(jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<News>(News.class), new Object[]{newsType}));
	}
	
	public News getNews(String title){
		String sql="SELECT t.* FROM ecp_news t WHERE t.vc_title=? ";
		return get(jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<News>(News.class), new Object[]{title}));
	}
	
	public void save(final EcpNews transientInstance) {
		try {
			getJdbcTemplate().update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					try {
						return FieldUtil.buildInsert(conn, transientInstance);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return null;
				}
			});
		} catch (Exception re) {
			re.printStackTrace();
		}
	}
}
