package com.an.sfs.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MaCaculator {
    private static final Log logger = LogFactory.getLog(MaCaculator.class);
    private Queue que;

    public MaCaculator(int dayNum) {
        que = new Queue(dayNum);
    }

    public float getAverage(float f) {
        que.add(f);
        logger.debug(que);
        float avg = que.average();
        String str = ServerConstant.FLOAT_FORMAT.format(avg);
        return Float.parseFloat(str);
    }
}
