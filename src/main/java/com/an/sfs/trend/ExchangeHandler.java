package com.an.sfs.trend;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.an.sfs.SfsConfig;
import com.an.sfs.common.Exchange;

@Component
@Scope("singleton")
public class ExchangeHandler {
    private static final Log logger = LogFactory.getLog(ExchangeHandler.class);
    private static final int DEFAULT_DAY_NUM = 20;
    private static DecimalFormat decimalFormat = new DecimalFormat("###,###,###.##");
    @Autowired
    private ExchangeStore exchangeStore;

    public void setExchangeStore(ExchangeStore exchangeStore) {
        this.exchangeStore = exchangeStore;
    }

    public ExchangeHandler() {
    }

    /**
     * @param code
     *            like 002647
     * @param forecastDayNum
     * @param expectedPrice
     *            <= 0 use last day's close price
     * @return
     */
    public List<Exchange> getExchanges(String code, int forecastDayNum, float expectedPrice) {
        List<Exchange> result = new ArrayList<Exchange>();

        List<Exchange> raw = new ArrayList<Exchange>();
        exchangeStore.getExchanges(code, raw);

        // Default add 20 days exchange value
        int total = raw.size();
        if (total < DEFAULT_DAY_NUM) {
            logger.error("Less than 20 days' raw data.");
            return result;
        }

        Exchange cur = raw.get(total - 1);
        if (expectedPrice <= 0) {
            expectedPrice = cur.getClose();
        }

        for (int i = 0; i < forecastDayNum; i++) {
            calculateMa(expectedPrice, raw);
        }

        total = raw.size();
        for (int i = 0; i < (SfsConfig.TREND_RESERVE_CUR_DAY_NUM + forecastDayNum); i++) {
            result.add(raw.get(total - (i + 1)));
        }
        return result;
    }

    /**
     * Calculate tomorrow's MA1 MA2 MA3 and put it to exchanges
     * 
     * @param list
     * @param tomorrowClose
     */
    public static void calculateMa(float tomorrowClose, List<Exchange> list) {
        int cnt = list.size();
        Exchange cur = list.get(cnt - 1);
        // MA5
        Exchange firstDay1 = list.get(cnt - Exchange.MA1_DAY_NUM);
        Exchange firstDay2 = list.get(cnt - Exchange.MA2_DAY_NUM);
        Exchange firstDay3 = list.get(cnt - Exchange.MA3_DAY_NUM);

        Exchange tomorrow = new Exchange();
        tomorrow.setDate("DATE");
        tomorrow.setClose(tomorrowClose);

        float ma1 = (cur.getTotal1() - firstDay1.getClose() + cur.getClose()) / Exchange.MA1_DAY_NUM;
        float ma2 = (cur.getTotal2() - firstDay2.getClose() + cur.getClose()) / Exchange.MA2_DAY_NUM;
        float ma3 = (cur.getTotal3() - firstDay3.getClose() + cur.getClose()) / Exchange.MA3_DAY_NUM;

        tomorrow.setMa1(Float.parseFloat(decimalFormat.format(ma1)));
        tomorrow.setMa2(Float.parseFloat(decimalFormat.format(ma2)));
        tomorrow.setMa3(Float.parseFloat(decimalFormat.format(ma3)));

        list.add(tomorrow);
    }
}
