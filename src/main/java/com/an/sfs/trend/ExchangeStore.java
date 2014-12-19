package com.an.sfs.trend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.an.sfs.common.Exchange;

@Component
@Scope("singleton")
public class ExchangeStore {
    private static final Log logger = LogFactory.getLog(ExchangeStore.class);
    private Map<String, List<Exchange>> exchangeMap = new HashMap<String, List<Exchange>>();
    private ExchangeFileReader reader = new ExchangeFileReader();

    public ExchangeStore() {
    }

    /**
     * @param code
     * @param exchanges
     */
    public void getExchanges(String code, List<Exchange> exchanges) {
        logger.info("Get exchanges by code: " + code);

        if (!exchangeMap.containsKey(code)) {
            List<Exchange> list = new ArrayList<Exchange>();
            String filepath = getFilePath(code);
            logger.info("Read exchanges from file: " + filepath);
            reader.readfile(filepath, list);
            exchangeMap.put(code, list);
        }

        exchanges.addAll(exchangeMap.get(code));
    }

    private String getFilePath(String code) {
        return "/" + code + "/" + code + ".txt";
    }
}
