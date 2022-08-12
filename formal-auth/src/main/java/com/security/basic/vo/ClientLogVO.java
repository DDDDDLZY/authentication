package com.security.basic.vo;

import com.utils.entity.dto.PageBaseDTO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: zhanyang
 * @Date: 2022/1/5 9:04
 */
@Data
@Accessors
public class ClientLogVO extends PageBaseDTO {
    /**
     * 主键
     */
    private String id;
    /**
     * 客户端Id
     */
    private String clientId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 内容
     */
    private String content;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 搜索开始时间
     */
    private String startTime;
    /**
     * 搜索结束时间
     */
    private String endTime;
}
