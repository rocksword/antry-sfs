package com.an.sfs.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.an.sfs.common.Exchange;
import com.an.sfs.trend.ExchangeHandler;

public class TrendServiceImpl implements TrendService {
    private static final Log logger = LogFactory.getLog(TrendServiceImpl.class);

    @Autowired
    private ExchangeHandler exchangeHandler;

    @Override
    public List<List<Object>> getForecastExchanges(String code, int forecastDayNum, float expectedPrice) {
        logger.info("code: " + code + ", forecastDayNum: " + forecastDayNum + ", expectedPrice: " + expectedPrice);
        List<List<Object>> result = new ArrayList<List<Object>>();

        List<Exchange> list = exchangeHandler.getExchanges(code, forecastDayNum, expectedPrice);

        int total = list.size();
        for (int i = 0; i < total; i++) {
            Exchange ex = list.get(total - (i + 1));

            List<Object> row = new ArrayList<Object>();
            row.add(ex.getDate());
            row.add(ex.getClose());
            row.add(ex.getMa1());
            row.add(ex.getMa2());
            row.add(ex.getMa3());
            result.add(row);
        }

        return result;
    }
}
