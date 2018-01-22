package org.rest.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.rest.bean.DeviceAdditionalAttrBean;
import org.rest.bean.DeviceBean;
import org.rest.bean.DeviceServicesBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("DeviceDaoImpl")
public class DeviceDaoImpl {

	static final String createDevice = "INSERT INTO DEVICE_INFO VALUES(?,?,?, ?,?,?, ?,?,?)";
	static final String createDeviceAttr = "INSERT INTO DEVICE_ADDL_ATTR VALUES(?,?,?)";
	static final String createDeviceServs = "INSERT INTO DEVICE_SERVICES VALUES(?,?,?, ?,?,?)";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public DeviceBean createDevice(DeviceBean deviceBean) {
		try {
			jdbcTemplate.update(createDevice,
					new Object[] { deviceBean.getDeviceId(), deviceBean.getDeviceType(), deviceBean.getSapId(),
							deviceBean.getNodeKey(), deviceBean.getDeviceStatus(), deviceBean.getFirmWareVersionNo(),
							deviceBean.getCentralStationAccntNo(), deviceBean.getInstalledUserId(),
							deviceBean.getParentDeviceId() });
			return deviceBean;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public void createDeviceAttr(DeviceBean deviceBean) {
		final List<DeviceAdditionalAttrBean> devAddnAttr = deviceBean.getListOfAddnAttributes();
		
		jdbcTemplate.batchUpdate(createDeviceAttr, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				DeviceAdditionalAttrBean deviceAdditionalAttrBean = devAddnAttr.get(i);
				ps.setInt(1, Integer.parseInt(deviceAdditionalAttrBean.getDeviceId()));
				ps.setString(2, deviceAdditionalAttrBean.getDeviceAttrName());
				ps.setString(3, deviceAdditionalAttrBean.getDeviceAttrValue());
				
			}

			@Override
			public int getBatchSize() {
				return devAddnAttr.size();
			}
		  });
	}

	public void createDeviceServices(DeviceBean deviceBean) {
		final List<DeviceServicesBean> devSers = deviceBean.getListOfDeviceServices();
		
		jdbcTemplate.batchUpdate(createDeviceServs, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				DeviceServicesBean deviceServicesBean = devSers.get(i);
				ps.setInt(1, Integer.parseInt(deviceServicesBean.getDeviceId()));
				ps.setString(2, deviceServicesBean.getServiceCtn());
				ps.setString(3, deviceServicesBean.getServiceEndDate());
				ps.setString(4, deviceServicesBean.getServiceStartDate());
				ps.setString(5, deviceServicesBean.getServiceStatus());
				ps.setString(6, deviceServicesBean.getServiceName());
			}
			
			@Override
			public int getBatchSize() {
				return devSers.size();
			}
		});
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

}
