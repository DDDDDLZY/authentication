package com.utils.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: zhanyang
 * @Date: 2022/1/13 11:16
 */
@Data
@Accessors(chain = true)
@TableName("sys_department")
public class SysDepartment {
    /** 部门id */
    @TableId(value = "dep_id")
    private Long depId;
    /** 部门名称 */
    private String name;
    /** 部门全称 */
    private String fullName;
    /** 父部门id */
    private Long pid;
    /** 显示顺序 */
    private Integer sortNum;
    /** 是否有效0：无效 1：有效 */
    private Byte enabled;
    /** 是否删除0：删除  1：未删除 */
    private Byte deleted;
    /** 备注 */
    private String remark;
    /** 组织类型：0-公司 1-部门 */
    private String type;

}
