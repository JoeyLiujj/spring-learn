package cn.joey.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class RiskExpressionRef implements Serializable{
    private String riskCode;
    private String accType;
    private String calCode;
    private String state;
    private Date makeDate;
    private String makeTime;
    private Date modifyDate;
    private String modifyTime;

    public String getRiskCode() {
        return riskCode;
    }

    public void setRiskCode(String riskCode) {
        this.riskCode = riskCode;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public String getCalCode() {
        return calCode;
    }

    public void setCalCode(String calCode) {
        this.calCode = calCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(Date makeDate) {
        this.makeDate = makeDate;
    }

    public String getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(String makeTime) {
        this.makeTime = makeTime;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "RiskExpressionRef{"+
                "riskCode:"+riskCode+","+
                "calCode:"+calCode+","+
                "accType:"+accType+","+
                "makeDate:"+makeDate+","+
                "makeTime:"+makeTime+","+
                "modifyDate:"+modifyDate+","+
                "modifyTime:"+modifyTime+"}";
    }
}
