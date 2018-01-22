package org.rest.controller;

import org.rest.bean.NodeBean;
import org.rest.bean.NodeTreeBean;
import org.rest.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NodeController {

	@Autowired
	private NodeService nodeService;

	@RequestMapping(value = "/createNode", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody NodeBean createNode(@RequestBody NodeBean nodeBean) {
		try {
		return nodeService.createNode(nodeBean);
		}catch(Exception e) {
			e.printStackTrace();
			return new NodeBean();
		}
	}

	@RequestMapping(value = "/getNodes/{sapNumber}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody NodeTreeBean getNodes(@PathVariable String sapNumber) {
		try {
		return nodeService.getNodes(sapNumber);
		}catch(Exception e) {
			e.printStackTrace();
			return new NodeTreeBean();
		}
	}
	
	public NodeService getNodeService() {
		return nodeService;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

}
