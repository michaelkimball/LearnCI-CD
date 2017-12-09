package com.binance.service.depth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.binance.Utilities;
import com.binance.dto.Depth;

@RestController
public class DepthController {
	
	@Autowired
	private DepthService depthService;	
	
	@RequestMapping(
			value = "/api/depth/{destination}/{source}/{limit}",
			method = RequestMethod.GET
			)
	public ResponseEntity<Depth> getDepth(ModelMap model, @PathVariable String destination, 
			@PathVariable String source, @PathVariable String limit) {
		String dest = Utilities.CRYPTO_MAP.get(destination.toUpperCase());
		String src = Utilities.CRYPTO_MAP.get(source.toUpperCase());
		int lim;
		if (dest.isEmpty())
			dest = "BTC";
		if (src.isEmpty())
			src = "USDT";
		try {
			lim = Integer.valueOf(limit);
		} catch (NumberFormatException e) {
			lim = 100;
		}
		Depth depth = depthService.getDepth(dest, src, lim);
		return new ResponseEntity<Depth>(depth, HttpStatus.OK);
	}

}
