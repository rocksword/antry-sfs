package com.an.sfs.service;

import java.util.List;

public interface DetailService {

    /**
     * @param code
     * @return {{"03-18",111},{"03-19",222},{"03-20",333}}
     */
    public List<List<Object>> getAllDetails(String code);
}
