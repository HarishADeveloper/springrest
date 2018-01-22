package org.rest.controller;

import org.rest.bean.DeviceBean;
import org.rest.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeviceController {

	@Autowired
	private DeviceService deviceService;

	@RequestMapping(value = "/createDevice", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody DeviceBean createDevice(@RequestBody DeviceBean deviceBean) {
		try {
			return deviceService.createDevice(deviceBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new DeviceBean();
	}

	public DeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}
}
