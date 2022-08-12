package com.lzy.security.basic.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzy.security.basic.vo.ClientLogVO;
import com.utils.entity.PageObject;
import com.utils.entity.pojo.ClientLog;

import java.util.List;

public interface ClientLogService extends IService<ClientLog> {

    /**
     * 查询记录
     * @return 返回影响行数
     */
    List<ClientLog> listAll();



    /**
     * @param clientLog 新增的记录
     * @return 返回影响行数
     */
    int insertIgnoreNull(ClientLog clientLog);

    /**
     * @param clientLog 修改的记录
     * @return 返回影响行数
     */
    int updateIgnoreNull(ClientLog clientLog);

    /**
     * 删除记录
     * @param clientLog 待删除的记录
     * @return 返回影响行数
     */
    int delete(ClientLog clientLog);

    PageObject pageList(ClientLogVO clientLogVO);
}
