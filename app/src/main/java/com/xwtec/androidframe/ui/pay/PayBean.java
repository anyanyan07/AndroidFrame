package com.xwtec.androidframe.ui.pay;

/**
 * @Author ayy
 * @Date 2018/10/22.
 * Describe:xxx
 */

public class PayBean {

    /**
     * appId : wxba59f5fdc6d39408
     * partnerId : 1516309931
     * prepayId : wx2214350816102016d915f72e1540860229
     * packageStr : Sign=WXPay
     * nonceStr : Z7o404V1e56eqgExtypGgbN61QQsAu1Y
     * timeStamp : 1540190108
     * sign : B4D75D0940B9C3CF624CD21C2BCB52EE
     */

    private String appId;
    private String partnerId;
    private String prepayId;
    private String packageStr;
    private String nonceStr;
    private String timeStamp;
    private String sign;

    private int status;
    private String data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getPackageStr() {
        return packageStr;
    }

    public void setPackageStr(String packageStr) {
        this.packageStr = packageStr;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
