package com.binance.service.depth;

import com.binance.Utilities;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.binance.dto.Depth;

@Service
public class DepthServiceBean implements DepthService {
	
	
	private final RestTemplate restTemplate;
	
	public DepthServiceBean(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@Override
	public Depth getDepth(String destination, String source, int limit) {
		Map<String, String> getVariables = new HashMap<String, String>();
		getVariables.put("cryptoDest", destination);
		getVariables.put("cryptoSource", source);
		getVariables.put("limit", String.valueOf(limit));
		Depth depth = this.restTemplate.getForObject(
				Utilities.apiBase + "/depth?symbol={cryptoDest}{cryptoSource}&limit={limit}", Depth.class, getVariables);
		return depth;
	}

}
