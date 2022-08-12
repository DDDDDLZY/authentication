package com.security.basic.client.service;

import com.security.basic.vo.OauthClientVO;
import com.utils.entity.PageObject;
import com.utils.entity.pojo.OauthClient;

import java.util.List;

public interface OauthClientService {

    /**
     * 查询记录
     *
     * @return 返回影响行数
     */
    List<OauthClient> listAll();


    /**
     * @param oauthClient 新增的记录
     * @return 返回影响行数
     */
    int insertIgnoreNull(OauthClient oauthClient);

    /**
     * @param oauthClient 修改的记录
     * @return 返回影响行数
     */
    int updateIgnoreNull(OauthClient oauthClient);

    /**
     * 删除记录
     *
     * @param oauthClient 待删除的记录
     * @return 返回影响行数
     */
    int delete(OauthClient oauthClient);

    /**
     * 分页列表
     *
     * @param oauthClientVO
     * @return
     */
    PageObject pageList(OauthClientVO oauthClientVO);
}
