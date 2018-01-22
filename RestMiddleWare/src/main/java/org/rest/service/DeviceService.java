package org.rest.service;

import org.rest.bean.DeviceBean;
import org.rest.dao.DeviceDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service("deviceService")
public class DeviceService {

	@Autowired
	private DeviceDaoImpl deviceDaoImpl;

	public DeviceBean createDevice(DeviceBean deviceBean) {

		DeviceBean resDeviceBean = deviceDaoImpl.createDevice(deviceBean);
		if (resDeviceBean != null) {
			if (!CollectionUtils.isEmpty(deviceBean.getListOfAddnAttributes())) {
				deviceDaoImpl.createDeviceAttr(deviceBean);
			}
			if (!CollectionUtils.isEmpty(deviceBean.getListOfDeviceServices())) {
				deviceDaoImpl.createDeviceServices(deviceBean);
			}
		}
		return resDeviceBean;
	}

	public DeviceDaoImpl getDeviceDaoImpl() {
		return deviceDaoImpl;
	}

	public void setDeviceDaoImpl(DeviceDaoImpl deviceDaoImpl) {
		this.deviceDaoImpl = deviceDaoImpl;
	}

}
