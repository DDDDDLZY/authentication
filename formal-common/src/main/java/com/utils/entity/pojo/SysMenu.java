package com.utils.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 2021.2.19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysMenu implements Serializable {
    private Integer menuId;
    private String menuName;
    private Integer parentId;
    private Integer orderNum;
    private String path;
    private String component;
    private Integer isFrame;
    private Integer isCache;
    private String menuType;
    private String visible;
    private Integer status;
    private String perms;
    private String icon;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String remark;
    /** 子菜单 */
    private List<SysMenu> children = new ArrayList<SysMenu>();

}
