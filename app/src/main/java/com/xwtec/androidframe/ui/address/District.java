package com.xwtec.androidframe.ui.address;

/**
 * Created by ayy on 2018/6/24.
 * Describe:xxx
 */

public class District {
    private String districtId;
    private String districtName;

    @Override
    public String toString() {
        return districtName;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
