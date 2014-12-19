package com.an.sfs.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.an.sfs.SfsConfig;
import com.an.sfs.service.TrendService;

@Controller
@RequestMapping("/rest/trend")
public class Trend {
    private static final Logger logger = LoggerFactory.getLogger(Detail.class);

    @Autowired
    private TrendService trendService;

    @RequestMapping(value = "/forecastExchanges", method = RequestMethod.GET)
    public @ResponseBody
    List<List<Object>> getForecastExchanges() {
        List<List<Object>> result = trendService.getForecastExchanges(SfsConfig.TREND_CODE, SfsConfig.TREND_FORECAST_DAY_NUM,
                SfsConfig.TREND_EXPECTED_PRICE);
        return result;
    }
}
