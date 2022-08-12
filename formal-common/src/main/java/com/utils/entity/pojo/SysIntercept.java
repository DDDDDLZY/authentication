package com.utils.entity.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author liuzhnayang
 * 拦截访问记录
 * 记录白名单部分接口调用及未授权访问记录
 */
@Data
@Accessors(chain = true)
public class SysIntercept {
	/**
	 * id
	 */
	private String id;
	/**
	 * 服务名
	 */
	private String serviceName;
	/**
	 * 服务ip
	 */
	private String serviceIp;
	/**
	 * 拦截ip
	 */
	private String interceptIp;
	/**
	 * 拦截路径
	 */
	private String interceptRoute;
	/**
	 * 拦截时间
	 */
	private Date interceptTime;
	/**
	 * 打印日志
	 */
	private String log;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
