package com.an.sfs.trend;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.an.sfs.SfsFileReader;
import com.an.sfs.common.Exchange;

public class ExchangeFileReader {
    private static final Log logger = LogFactory.getLog(ExchangeFileReader.class);
    // Support year after 2000
    private static final String LINE_PREFIX = "20";

    public ExchangeFileReader() {
    }

    public void readfile(String filepath, List<Exchange> exchanges) {
        logger.info("filepath: " + filepath);
        SfsFileReader reader = new SfsFileReader(filepath);

        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty() || !line.startsWith(LINE_PREFIX)) {
                logger.debug("Invalid line: " + line);
                continue;
            }
            Pattern pat = Pattern.compile("\\s+");
            String[] strs = pat.split(line.toString());
            int count = strs.length;
            if (count < 6) {
                logger.error("Invalid line: " + line);
                continue;
            }

            Exchange exchange = new Exchange();

            String date = strs[0].trim();
            float open = Float.parseFloat(strs[1].trim());
            float max = Float.parseFloat(strs[2].trim());
            float min = Float.parseFloat(strs[3].trim());
            float close = Float.parseFloat(strs[4].trim());
            float volume = Float.parseFloat(strs[5].trim());
            exchange.set(date, open, max, min, close, volume);

            float ma1 = 0;
            float ma2 = 0;
            float ma3 = 0;
            float ma4 = 0;
            float ma5 = 0;
            float ma6 = 0;
            if (count > 6) {
                ma1 = Float.parseFloat(strs[6].trim());
                if (count > 7) {
                    ma2 = Float.parseFloat(strs[7].trim());
                    if (count > 8) {
                        ma3 = Float.parseFloat(strs[8].trim());
                        if (count > 9) {
                            ma4 = Float.parseFloat(strs[9].trim());
                            if (count > 10) {
                                ma5 = Float.parseFloat(strs[10].trim());
                                if (count > 11) {
                                    ma6 = Float.parseFloat(strs[11].trim());
                                }
                            }
                        }
                    }
                }
            }
            exchange.set(ma1, ma2, ma3, ma4, ma5, ma6);

            exchanges.add(exchange);
        }

        reader.close();
    }
}
