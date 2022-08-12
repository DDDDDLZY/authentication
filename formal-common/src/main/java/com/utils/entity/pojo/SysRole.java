package com.utils.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 2021.2.19
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRole implements Serializable {
    private Integer roleId;
    private String roleName;
    private String roleKey;
    private Integer roleSort;
    private String dataScope;
    private Integer menuCheckStrictly;
    private String deptCheckStrictly;
    private String status;
    private Integer delFlag;
    private String createBy;
    private Date createTime;
    private Integer updateBy;
    private Date updateTime;
    private String remark;

}
