package com.utils.entity.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author liuzhanyang
 * 服务表
 */
@Data
@Accessors(chain = true)
public class SysProject {
	/** 主键 */
	private Integer id;
	/** 客户端id */
	private String clientId;
	/** 项目名称 */
	private String serviceName;
	/** 开始时间 */
	private Date starttime;
	/** 结束时间 */
	private Date endtime;
	/** 可用工作日 */
	private Integer workday;
	/** 管理员id */
	private Integer managerId;
	/** 管理员 */
	private String managerName;
	/** 备注 */
	private String remark;

}
