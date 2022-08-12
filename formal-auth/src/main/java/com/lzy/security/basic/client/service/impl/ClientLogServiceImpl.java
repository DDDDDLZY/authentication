package com.lzy.security.basic.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzy.security.basic.vo.ClientLogVO;
import com.lzy.security.basic.client.mapper.ClientLogMapper;
import com.lzy.security.basic.client.service.ClientLogService;
import com.utils.common.StringUtils;
import com.utils.common.constant.ClientConstants;
import com.utils.entity.PageObject;
import com.utils.entity.pojo.ClientLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientLogServiceImpl extends ServiceImpl<ClientLogMapper, ClientLog> implements ClientLogService {

    @Autowired
    private ClientLogMapper clientLogMapper;

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    public List<ClientLog> listAll() {
        return clientLogMapper.listAll();
    }


    /**
     * @param clientLog 新增的记录
     * @return 返回影响行数
     */
    public int insertIgnoreNull(ClientLog clientLog) {
        return this.save(clientLog) == true ? 1 : 0;
    }

    /**
     * @param clientLog 修改的记录
     * @return 返回影响行数
     */
    public int updateIgnoreNull(ClientLog clientLog) {
        return clientLogMapper.updateIgnoreNull(clientLog);
    }

    /**
     * 删除记录
     *
     * @param clientLog 待删除的记录
     * @return 返回影响行数
     */
    public int delete(ClientLog clientLog) {
        return clientLogMapper.delete(clientLog);
    }

    /**
     * 分页列表
     *
     * @param clientLogVO
     * @return
     */
    @Override
    public PageObject pageList(ClientLogVO clientLogVO) {
        QueryWrapper<ClientLog> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(clientLogVO.getClientId())) {
            queryWrapper.eq("client_id", clientLogVO.getClientId());
        }
        if (!StringUtils.isEmpty(clientLogVO.getStartTime()) && !StringUtils.isEmpty(clientLogVO.getEndTime())) {
            queryWrapper.between("create_time", clientLogVO.getStartTime(), clientLogVO.getEndTime());
        }
        queryWrapper.eq("del_flag", ClientConstants.NORMAL);
        Page<ClientLog> page = new Page<>(clientLogVO.getCurrentPage(), clientLogVO.getPageSize());
        IPage<ClientLog> iPage = this.page(page, queryWrapper);
        return PageObject.PageResult(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), iPage.getRecords());
    }

}
