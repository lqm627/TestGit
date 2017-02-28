package com.easylotto.core.service;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springside.modules.mapper.JsonMapper;

import com.easylotto.commons.util.ClobFileUtil;
import com.easylotto.commons.util.DateTimeUtil;
import com.easylotto.core.dao.ContentDao;
import com.easylotto.core.entity.ContentData;
@Service
public class ContentService extends BaseService {
	
	@Autowired
	private ContentDao contentDao;
	
	@Value("${wx.content}")
	private String contentUrl;
	
	@Value("${content.cache.timeunit}")
	private int timeunit;
	
	
	
	public List<ContentData> findList(String wechat,String type){
		List<ContentData> list = null;
		if(getJedisTemplate().exists("WXCONTENT:LIST:" + wechat + ":" + type)){
			String value = getJedisTemplate().get("WXCONTENT:LIST:" + wechat + ":" + type);
			list = JsonMapper.nonEmptyMapper().fromJson(value, List.class);
		}else{
			list = contentDao.findList(wechat,type);
			getJedisTemplate().set("WXCONTENT:LIST:" + wechat + ":" + type, JsonMapper.nonEmptyMapper().toJson(list), timeunit, TimeUnit.SECONDS);
		}
		return list;
	}
	
	public ContentData findDetail(String id){
		ContentData contentData = new ContentData();
		if(!getJedisTemplate().exists("WXCONTENT:"+id)){
			contentData = contentDao.findDetail(id);
			File file = new File(contentUrl+contentData.getVcContent());
			logger.info("file.exists()-> "+file.exists());
			String content = ClobFileUtil.readFile(file);
			logger.info("content-> "+content);
			contentData.setVcContent(content);
			contentData.setCreateDateStr(DateTimeUtil.format(DateTimeUtil.YYYY_MM_DD,contentData.getCreateDate()));
			getJedisTemplate().set("WXCONTENT:"+id, JsonMapper.nonEmptyMapper().toJson(contentData), timeunit, TimeUnit.SECONDS);
		}else {
			String value = getJedisTemplate().get("WXCONTENT:"+id);
			contentData = JsonMapper.nonEmptyMapper().fromJson(value, ContentData.class);
		}
		return contentData;
	}
}
