package com.utils.entity.pojo;

import lombok.Data;

@Data
public class SysPlan {
	/** 主键 */
	private Integer id;
	/** 计划名称 */
	private String planName;
	/** 计划内容 */
	private String planContent;
	/** 计划进度 */
	private String planProgress;

	private String createtime;

	private String updatetime;
}
