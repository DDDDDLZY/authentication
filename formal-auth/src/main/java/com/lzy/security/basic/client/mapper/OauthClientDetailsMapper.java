package com.lzy.security.basic.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzy.security.basic.vo.OauthClientDetailsVO;
import com.utils.entity.pojo.OauthClientDetails;
import com.utils.entity.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OauthClientDetailsMapper extends BaseMapper<OauthClientDetails> {

	/**
     * 查询记录
     * @return 返回集合
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
     * @param oauthClientDetails 待删除的记录
     * @return 返回影响行数
     */
	int delete(OauthClientDetails oauthClientDetails);

    List<SysUser> getPage(OauthClientDetailsVO oauthClientDetailsVO);
}
