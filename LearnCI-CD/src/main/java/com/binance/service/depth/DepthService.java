package com.binance.service.depth;

import com.binance.dto.Depth;

public interface DepthService {
	public Depth getDepth(String destination, String source, int limit);

}
