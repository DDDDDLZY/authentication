package com.utils.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.utils.entity.vo.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName(value = "sys_file")
public class Files extends BaseEntity {

    @TableId
    private Long fileId;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 显示文件名
     */
    private String fileShowName;
    /**
     * 文件全路径
     */
    private String fileUrl;
    /**
     * 外部路径
     */
    private String fileUrlWeb;
    /**
     * 1：local  2:cos 3:oss
     */
    private Integer fileType;

}
