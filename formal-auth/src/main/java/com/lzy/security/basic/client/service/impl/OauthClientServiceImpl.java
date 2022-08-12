package com.lzy.security.basic.client.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzy.security.basic.client.mapper.OauthClientMapper;
import com.lzy.security.basic.client.service.OauthClientService;
import com.lzy.security.basic.vo.OauthClientVO;
import com.utils.entity.PageObject;
import com.utils.entity.pojo.OauthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OauthClientServiceImpl implements OauthClientService {

    @Autowired
    private OauthClientMapper oauthClientMapper;

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    public List<OauthClient> listAll() {
        return oauthClientMapper.listAll();
    }


    /**
     * @param oauthClient 新增的记录
     * @return 返回影响行数
     */
    public int insertIgnoreNull(OauthClient oauthClient) {
        return oauthClientMapper.insertIgnoreNull(oauthClient);
    }

    /**
     * @param oauthClient 修改的记录
     * @return 返回影响行数
     */
    public int updateIgnoreNull(OauthClient oauthClient) {
        return oauthClientMapper.updateIgnoreNull(oauthClient);
    }

    /**
     * 删除记录
     *
     * @param oauthClient 待删除的记录
     * @return 返回影响行数
     */
    public int delete(OauthClient oauthClient) {
        return oauthClientMapper.delete(oauthClient);
    }

    /**
     * 分页列表
     *
     * @param oauthClientVO
     * @return
     */
    @Override
    public PageObject<OauthClientVO> pageList(OauthClientVO oauthClientVO) {
        IPage page = new Page(oauthClientVO.getCurrentPage(), oauthClientVO.getPageSize(),true);
        IPage<OauthClientVO> list = oauthClientMapper.findPage(page, oauthClientVO);
        return PageObject.PageResult(list.getTotal(), list.getCurrent(), list.getSize(), list.getRecords());
    }

}
