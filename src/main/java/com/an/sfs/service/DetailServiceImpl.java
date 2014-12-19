package com.an.sfs.service;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.an.sfs.SfsConfig;
import com.an.sfs.server.DateUtil;
import com.an.sfs.util.FileUtil;

public class DetailServiceImpl implements DetailService {
    private static final Logger logger = LoggerFactory.getLogger(DetailServiceImpl.class);

    @Override
    public List<List<Object>> getAllDetails(String code) {
        logger.info("code: " + code);

        String summaryFileName = FileUtil.getSummaryFileName(SfsConfig.CODE);
        List<List<Object>> result = new ArrayList<List<Object>>();
        try {
            BufferedReader br = FileUtil.getBufferedReader("/" + summaryFileName);
            String line = null;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                String[] strs = line.split(",");
                List<Object> row = new ArrayList<Object>();
                String date = DateUtil.formatDate(strs[0].trim());
                row.add(date);
                row.add(Integer.parseInt(strs[1].trim()));
                row.add(Integer.parseInt(strs[2].trim()));
                result.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
