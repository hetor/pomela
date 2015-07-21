package com.tangerine.yuzu.questions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AttackerChecker {
    private static Map<String, List<Long>> ipVisitTimes = new HashMap<>();
    
    public boolean access(String ip, long timestamp) {
        List<Long> timestamps = ipVisitTimes.get(ip);
        if(null == timestamps) {
            ipVisitTimes.put(ip, new LinkedList<Long>());
        }
        for (int i=0; i<timestamps.size(); i++) {
            if((timestamp-timestamps.get(i)) > 60*60) {
                timestamps.remove(i);
            }else {
                break;
            }
        }
        timestamps.add(timestamp);
        return timestamps.size() > 1000;
    }
}
