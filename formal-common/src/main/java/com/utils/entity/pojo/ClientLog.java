package com.utils.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.utils.entity.vo.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName(value = "client_log")
public class ClientLog extends BaseEntity {
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
     * 操作
     */
    private String operation;
    /**
     * 方法
     */
    private String method;
    /**
     * 参数
     */
    private String params;
    /**
     * 返回参数
     */
    private String returnParams;
    /**
     * 错误
     */
    private String error;
    /**
     * ip
     */
    private String ip;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;
    /**
     * 日志类型
     */
    private String type;
}
