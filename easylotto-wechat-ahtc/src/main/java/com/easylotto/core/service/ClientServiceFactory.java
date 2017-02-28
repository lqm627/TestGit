package com.easylotto.core.service;

import java.util.Map;

import org.springframework.stereotype.Service;
@Service
public class ClientServiceFactory {

	private Map<String, IClientService> serviceMap;
	
	public IClientService getService(String type) {
		if (serviceMap.containsKey(type))
			return serviceMap.get(type);
		
		return null;
	}

	public void setServiceMap(Map<String, IClientService> serviceMap) {
		this.serviceMap = serviceMap;
	}


}
