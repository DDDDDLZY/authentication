package com.lzy.security.basic.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzy.security.basic.client.mapper.OauthClientDetailsMapper;
import com.lzy.security.basic.client.service.OauthClientDetailsService;
import com.lzy.security.basic.vo.OauthClientDetailsVO;
import com.utils.entity.PageObject;
import com.utils.entity.pojo.OauthClientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OauthClientDetailsServiceImpl implements OauthClientDetailsService {

    @Autowired
    private OauthClientDetailsMapper oauthClientDetailsMapper;

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    public List<OauthClientDetails> listAll(OauthClientDetailsVO oauthClientDetailsVO) {
        return oauthClientDetailsMapper.listAll(oauthClientDetailsVO);
    }


    /**
     * @param oauthClientDetails 新增的记录
     * @return 返回影响行数
     */
    public int insertIgnoreNull(OauthClientDetails oauthClientDetails) {
        return oauthClientDetailsMapper.insertIgnoreNull(oauthClientDetails);
    }

    /**
     * @param oauthClientDetails 修改的记录
     * @return 返回影响行数
     */
    public int updateIgnoreNull(OauthClientDetails oauthClientDetails) {
        return oauthClientDetailsMapper.updateIgnoreNull(oauthClientDetails);
    }

    /**
     * 删除记录
     *
     * @param oauthClientDetails 待删除的记录
     * @return 返回影响行数
     */
    public int delete(OauthClientDetails oauthClientDetails) {
        return oauthClientDetailsMapper.delete(oauthClientDetails);
    }

    /**
     * 查询分页
     *
     * @param oauthClientDetailsVO
     * @return
     */
    @Override
    public PageObject getPage(OauthClientDetailsVO oauthClientDetailsVO) {
        Page<OauthClientDetails> page = new Page<>(oauthClientDetailsVO.getCurrentPage(), oauthClientDetailsVO.getPageSize());
        QueryWrapper<OauthClientDetails> queryWrapper = new QueryWrapper();
        IPage<OauthClientDetails> iPage = oauthClientDetailsMapper.selectPage(page, queryWrapper);
        return PageObject.PageResult(page.getTotal(), page.getCurrent(), iPage.getSize(),iPage.getRecords());
    }

}
