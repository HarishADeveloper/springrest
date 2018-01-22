package org.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.rest.bean.DeviceAdditionalAttrBean;
import org.rest.bean.DeviceBean;
import org.rest.bean.DeviceServicesBean;
import org.rest.bean.NodeBean;
import org.rest.bean.NodeTreeBean;
import org.rest.dao.NodeDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("nodeService")
public class NodeService {

	@Autowired
	private NodeDaoImpl nodeDaoImpl;
	List<NodeBean> listOfNodeBeans = null;
	List<DeviceBean> listOfDeviceBeans = null;

	public NodeBean createNode(NodeBean nodeBean) {
		try {
			if (nodeDaoImpl.validateSapId(nodeBean.getSapId())) {
				return nodeDaoImpl.creatNode(nodeBean);
			}
		} catch (Exception e) {
			return new NodeBean();
		}
		return new NodeBean();
	}

	public NodeTreeBean getNodes(String sapNumber) {
		listOfDeviceBeans = getCompleteDeviceBens(nodeDaoImpl.getDevices(sapNumber));
		listOfNodeBeans = getNodesWithDevices(nodeDaoImpl.getNodes(sapNumber), listOfDeviceBeans);
		return new NodeTreeBean(listOfNodeBeans.get(0), getChildNodes(listOfNodeBeans, listOfNodeBeans.get(0)));
	}

	private List<NodeBean> getNodesWithDevices(List<NodeBean> nodes, List<DeviceBean> listOfDeviceBeans2) {
		List<NodeBean> resNodeBeansList = new ArrayList<NodeBean>();
		for (DeviceBean deviceBean : listOfDeviceBeans2) {
			for(int i=0; i<nodes.size(); i++) {
				if(deviceBean.getNodeKey().equalsIgnoreCase(nodes.get(i).getId())) {
					if(nodes.get(i).getListOfDeviceBeans()==null) {
						List<DeviceBean> tempDeviceList = new ArrayList<DeviceBean>();
						tempDeviceList.add(deviceBean);
						nodes.get(i).setListOfDeviceBeans(tempDeviceList);
					}else {
						nodes.get(i).getListOfDeviceBeans().add(deviceBean);
					}
					
				}
			}
		}
		return nodes;
	}

	public List<NodeTreeBean> getChildNodes(List<NodeBean> listOfNodeBeans, NodeBean nodeBean) {
		NodeBean finalNodeBean = nodeBean;
		int count = 0;
		for (NodeBean temp : listOfNodeBeans) {
			if (temp.getParentId().equalsIgnoreCase(finalNodeBean.getId())) {
				count++;
			}
		}
		if (count > 0) {
			List<NodeTreeBean> listOfNodeTreeBeans = new ArrayList<NodeTreeBean>();
			for (NodeBean temp : listOfNodeBeans) {
				if (temp.getParentId().equalsIgnoreCase(finalNodeBean.getId())) {
					listOfNodeTreeBeans.add(new NodeTreeBean(temp, getChildNodes(listOfNodeBeans, temp)));
				}
			}
			return listOfNodeTreeBeans;
		}
		return null;
	}

	private List<DeviceBean> getCompleteDeviceBens(List<DeviceBean> devices) {
		List<DeviceBean> listOfDevices = devices;
		if (listOfDevices.size() > 0) {
			String[] deviceIds = new String[devices.size()];
			for (int i = 0; i < listOfDevices.size(); i++) {
				deviceIds[i] = listOfDevices.get(i).getDeviceId();
			}

			List<DeviceAdditionalAttrBean> listOfAttributes = nodeDaoImpl.getAttributesList(deviceIds);
			List<DeviceServicesBean> listOfDeviceServices = nodeDaoImpl.getServicesList(deviceIds);

			return getDevicesWithServices(listOfDeviceServices, getDevicesWithAttributes(listOfAttributes, listOfDevices));
		}
		return listOfDevices;
	}

	private List<DeviceBean> getDevicesWithServices(List<DeviceServicesBean> listOfServices, List<DeviceBean> listOfDevices) {
		List<DeviceBean> responseListOfDevices = new ArrayList<DeviceBean>();
		for (DeviceBean deviceBean : listOfDevices) {
			List<DeviceServicesBean> resListOfServices = new ArrayList<DeviceServicesBean>();
			for (int i = 0; i < listOfServices.size(); i++) {
				
				if (deviceBean.getDeviceId().equalsIgnoreCase(listOfServices.get(i).getDeviceId())) {
					resListOfServices.add(listOfServices.get(i));
				}
			}
			if (resListOfServices.size() > 0) {
				deviceBean.setListOfDeviceServices(resListOfServices);
				responseListOfDevices.add(deviceBean);
			} else {
				responseListOfDevices.add(deviceBean);
			}
		}
		return responseListOfDevices;
	}

	public List<DeviceBean> getDevicesWithAttributes(List<DeviceAdditionalAttrBean> listOfAttributes,
			List<DeviceBean> listOfDevices) {
		List<DeviceBean> responseListOfDevices = new ArrayList<DeviceBean>();
		for (DeviceBean deviceBean : listOfDevices) {
			List<DeviceAdditionalAttrBean> resListOfAttributes = new ArrayList<DeviceAdditionalAttrBean>();
			for (int i = 0; i < listOfAttributes.size(); i++) {
				
				if (deviceBean.getDeviceId().equalsIgnoreCase(listOfAttributes.get(i).getDeviceId())) {
					resListOfAttributes.add(listOfAttributes.get(i));
				}
			}
			if (resListOfAttributes.size() > 0) {
				deviceBean.setListOfAddnAttributes(resListOfAttributes);
				responseListOfDevices.add(deviceBean);
			} else {
				responseListOfDevices.add(deviceBean);
			}
		}
		return responseListOfDevices;
	}

	public NodeDaoImpl getNodeDaoImpl() {
		return nodeDaoImpl;
	}

	public void setNodeDaoImpl(NodeDaoImpl nodeDaoImpl) {
		this.nodeDaoImpl = nodeDaoImpl;
	}
}
