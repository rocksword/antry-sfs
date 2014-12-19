package com.an.sfs.service;

import java.util.ArrayList;
import java.util.List;

public class MockDetailServiceImpl implements DetailService {

    @Override
    public List<List<Object>> getAllDetails(String code) {
        List<List<Object>> result = new ArrayList<List<Object>>();
        for (int i = 0; i < 30; i++) {
            List<Object> row = new ArrayList<Object>();
            row.add("03-1" + i);
            if (i % 2 == 0) {
                row.add(i * 10);
            } else {
                row.add(-i * 10);
            }
            row.add(100);
            result.add(row);
        }
        return result;
    }

}
