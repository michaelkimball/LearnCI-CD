package com.binance.service.depth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DepthChartController {
	
	
	@RequestMapping(
			value = "/depth/{destination}/{source}/{limit}",
			method = RequestMethod.GET
			)
	public String getDepth(ModelMap model, @PathVariable String destination, 
			@PathVariable String source, @PathVariable String limit) {
		model.addAttribute("destination", destination);
		model.addAttribute("source", source);
		model.addAttribute("limit", limit);
		return "depth-chart";
	}

}