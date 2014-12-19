package com.an.sfs.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.an.sfs.SfsConfig;
import com.an.sfs.common.ExchangeDetail;
import com.an.sfs.common.ExchangeSummary;
import com.an.sfs.util.FileUtil;

public class ExchangeDetailProcessor {
    private static final Log logger = LogFactory.getLog(ExchangeDetailProcessor.class);

    public ExchangeDetailProcessor() {
    }

    public void process(String code, int handNum, Map<String, ExchangeSummary> summaries) throws NumberFormatException,
            IOException {
        logger.info("process code: " + code + ", hand number: " + handNum);
        List<ExchangeDetail> details = new ArrayList<ExchangeDetail>();
        load(code, details);

        for (ExchangeDetail detail : details) {
            String date = detail.getDate();
            if (!summaries.containsKey(date)) {
                summaries.put(date, new ExchangeSummary(date));
            }
            summaries.get(date).incrementTimes();
            if (detail.getAmount() > handNum) {
                summaries.get(date).incrementMoney(detail);
            }
        }
    }

    /**
     * @param code
     *            002039
     * @param details
     * @throws NumberFormatException
     * @throws IOException
     */
    private void load(String code, List<ExchangeDetail> details) throws NumberFormatException, IOException {
        logger.info("load code: " + code);
        List<String> files = FileUtil.getFiles("/" + code);
        for (String fn : files) {
            String date = fn.substring(0, 8);
            String path = "/" + code + "/" + fn;
            BufferedReader br = FileUtil.getBufferedReader(path);
            String line = null;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.contains("B") || line.contains("S")) {
                    Pattern pat = Pattern.compile("\\s+");
                    String[] strs = pat.split(line);
                    if (strs.length == 5) {
                        String time = strs[0].trim();
                        float price = Float.parseFloat(strs[1].trim());
                        int amount = Integer.parseInt(strs[2].trim());
                        String type = strs[4].trim();
                        ExchangeDetail detail = new ExchangeDetail(date, time, price, amount, type);
                        details.add(detail);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ExchangeDetailProcessor processor = new ExchangeDetailProcessor();
        try {

            String outFn = FileUtil.getSummaryFileName(SfsConfig.CODE);
            Map<String, ExchangeSummary> summaries = new HashMap<String, ExchangeSummary>();

            processor.process(SfsConfig.CODE, SfsConfig.HAND_NUMBER, summaries);

            List<String> dates = new ArrayList<String>();
            dates.addAll(summaries.keySet());

            Collections.sort(dates);

            List<String> outputData = new ArrayList<String>();
            for (String date : dates) {
                outputData.add(summaries.get(date).toString());
            }
            FileUtil.writeTo(outputData, outFn);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
