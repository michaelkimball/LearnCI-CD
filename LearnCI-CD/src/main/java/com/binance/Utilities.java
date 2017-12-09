package com.binance;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Utilities {
	public static final String apiBase = "https://api.binance.com/api/v1";
	public static final Map<String, String> CRYPTO_MAP;
	
	static {
		Map<String, String> map = new HashMap<String, String>();
		map.put("BITCOIN", "BTC");
		map.put("ETHERIUM", "ETH");
		map.put("LITECOIN", "LTC");
		map.put("TETHER", "USDT");
		map.put("BTC", "BTC");
		map.put("ETH", "ETH");
		map.put("LTC", "LTC");
		map.put("USDT", "USDT");
		CRYPTO_MAP = Collections.unmodifiableMap(map);
	}
}
