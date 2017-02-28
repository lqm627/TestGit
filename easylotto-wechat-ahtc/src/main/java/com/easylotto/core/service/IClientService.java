package com.easylotto.core.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.wechat.webapi.web.model.ResponseBean;
@Service
public interface IClientService {

	public ResponseBean execute(String request_data, Long memberId,Map<String,String> parameterMap);
}
