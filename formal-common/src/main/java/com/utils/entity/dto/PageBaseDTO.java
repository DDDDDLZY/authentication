package com.utils.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName PageBaseDTO
 * @Description
 * @Author linzhixiong
 * @Date 2021/7/22 下午5:28
 **/
@Data
public class PageBaseDTO implements Serializable {


    /**
     * 当前页数
     */
    private Integer currentPage;

    /**
     * 每页显示的数量
     */
    private Integer pageSize;
    /**
     * 数据总条数
     */
    private Long totalCount;
}
