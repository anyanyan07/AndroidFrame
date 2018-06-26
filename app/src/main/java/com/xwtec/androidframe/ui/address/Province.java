package com.xwtec.androidframe.ui.address;

import java.util.List;

public class Province {
	/**
	 * 省份ID
	 * */
	private String provinceId;
	/**
	 * 省份名称
	 * */
	private String provinceName;

	private List<City> cities;

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	@Override
	public String toString() {
		return provinceName;
	}
	
	
}
