package com.an.sfs;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SfsUtil {
    private static final Log logger = LogFactory.getLog(SfsUtil.class);

    /**
     * @param collection
     */
    public static void print(Collection<?> collection) {
        System.out.println("Total size: " + collection.size());
        for (Object obj : collection) {
            System.out.println(obj);
        }
    }

    /**
     * @param collection
     */
    public static void log(Collection<?> collection) {
        logger.info("Total size: " + collection.size());
        for (Object obj : collection) {
            logger.info(obj);
        }
    }
}
