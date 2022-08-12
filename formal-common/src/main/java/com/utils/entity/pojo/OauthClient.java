package com.utils.entity.pojo;

import com.utils.entity.vo.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OauthClient extends BaseEntity {
	/**
	 * 主键
	 */
    private Integer id;
	/**
	 * 客户端Id
	 */
    private String clientId;
	/**
	 * 客户端名称
	 */
    private String clientName;
	/**
	 * 服务地址
	 */
    private String address;
	/**
	 * 状态 异常：-1 正常：0
	 */
    private String state;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

	private String secretKey;

	/**
	 * 1oauth 2jwt 3cas
	 * */
	private String singleType;

	private String remark;
}
