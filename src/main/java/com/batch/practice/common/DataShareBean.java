package com.batch.practice.common;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by kanghonggu on 2017. 6. 4..
 */

@Component
public class DataShareBean <T> {

    private static Logger logger = LoggerFactory.getLogger(DataShareBean.class);
    private Map<String, T> shareDataMap;


    public DataShareBean () {
        this.shareDataMap = Maps.newConcurrentMap();
    }

    public void putData(String key, T data) {
        if (shareDataMap ==  null) {
            logger.error("Map is not initialize");
            return;
        }

        shareDataMap.put(key, data);
    }

    public T getData (String key) {

        if (shareDataMap == null) {
            return null;
        }

        return shareDataMap.get(key);
    }

    public int getSize () {
        if (this.shareDataMap == null) {
            logger.error("Map is not initialize");
            return 0;
        }

        return shareDataMap.size();
    }

}
