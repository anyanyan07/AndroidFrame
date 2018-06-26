package com.xwtec.androidframe.ui.address;

import java.util.List;

public class City implements Comparable<City>{
    private String cityId;
    private String cityName;
    private String pinyin;
    private List<District> districtList;

    public List<District> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(List<District> districtList) {
        this.districtList = districtList;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    private String provinceId;


    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return cityName;
    }

    @Override
    public int compareTo(City city) {
        String a1 = this.getPinyin();
        String a2 = city.getPinyin();
        return a1.compareTo(a2);
    }
}
