package com.utils.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 *
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PageObject<T> implements Serializable {


    /**
     * 页码
     */
    private long currentPage;

    /**
     * 每页条数
     */
    private long pageSize;

    /**
     * 总记录数
     */
    private long total;

    /**
     * 列表数据
     */
    private List<T> rows;

    /**
     * 消息内容
     */
    private Integer state;


    public PageObject(long total, long currentPage, long pageSize, List<T> rows) {
        this.total = total;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.rows = rows;
        this.state = 200;
    }

    public static PageObject PageResult(long total, long currentPage, long pageSize, List rows) {
        return new PageObject(total, currentPage, pageSize, rows);
    }
}
