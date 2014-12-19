package com.an.sfs.service;

import java.util.List;

public interface TrendService {
    /**
     * @param code
     * @param forecastDayNum
     * @param expectedPrice
     * @return {{"03-18",111},{"03-19",222},{"03-20",333}}
     */
    public List<List<Object>> getForecastExchanges(String code, int forecastDayNum, float expectedPrice);
}
