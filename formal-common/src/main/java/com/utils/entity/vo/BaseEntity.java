package com.utils.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author: lzy
 * @Date: 2021/12/31 8:44
 */
@Data
public class BaseEntity implements Serializable {
    //(value = "创建时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:dd:ss", timezone = "GMT+8")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Timestamp createTime;

    //(value = "创建人")
    //@TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:dd:ss", timezone = "GMT+8")
//    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    //(value = "修改时间")
    private Timestamp updateTime;

    //(value = "修改人")
//    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private String updateBy;

    @TableField(exist = false)
    private Integer pageNum;

    @TableField(exist = false)
    private Integer pageSize;
}
