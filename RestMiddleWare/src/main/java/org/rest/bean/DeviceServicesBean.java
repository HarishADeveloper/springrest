package org.rest.bean;

//DEVICE_SERVICES
public class DeviceServicesBean {

	private String deviceId;
	private String serviceCtn;
	private String serviceEndDate;
	private String serviceStartDate;
	private String serviceStatus;
	private String serviceName;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getServiceCtn() {
		return serviceCtn;
	}

	public void setServiceCtn(String serviceCtn) {
		this.serviceCtn = serviceCtn;
	}

	public String getServiceEndDate() {
		return serviceEndDate;
	}

	public void setServiceEndDate(String serviceEndDate) {
		this.serviceEndDate = serviceEndDate;
	}

	public String getServiceStartDate() {
		return serviceStartDate;
	}

	public void setServiceStartDate(String serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}

	public String getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

}
