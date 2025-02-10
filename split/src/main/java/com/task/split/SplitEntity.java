package com.task.split;

import java.util.Map;

public class SplitEntity {

    private Map<Double, Integer> splitInfo;
    private String resultType;


    public SplitEntity(Map<Double, Integer> splitInfo, String resultType){
        this.splitInfo = splitInfo;
        this.resultType = resultType;
    }

    public Map<Double, Integer> getSplitInfo() {
        return splitInfo;
    }

    public void setSplitInfo(Map<Double, Integer> splitInfo) {
        this.splitInfo = Map.copyOf(splitInfo);
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
