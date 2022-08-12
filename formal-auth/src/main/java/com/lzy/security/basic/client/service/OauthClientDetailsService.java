package com.lzy.security.basic.client.service;

import com.lzy.security.basic.vo.OauthClientDetailsVO;
import com.utils.entity.PageObject;
import com.utils.entity.pojo.OauthClientDetails;

import java.util.List;

public interface OauthClientDetailsService {

    /**
     * 查询记录
     * @return 返回影响行数
     */
    List<OauthClientDetails> listAll(OauthClientDetailsVO oauthClientDetailsVO);



    /**
     * @param oauthClientDetails 新增的记录
     * @return 返回影响行数
     */
    int insertIgnoreNull(OauthClientDetails oauthClientDetails);

    /**
     * @param oauthClientDetails 修改的记录
     * @return 返回影响行数
     */
    int updateIgnoreNull(OauthClientDetails oauthClientDetails);

    /**
     * 删除记录
     * @param oauthClientDetails 待删除的记录
     * @return 返回影响行数
     */
    int delete(OauthClientDetails oauthClientDetails);

    PageObject getPage(OauthClientDetailsVO oauthClientDetailsVO);
}
