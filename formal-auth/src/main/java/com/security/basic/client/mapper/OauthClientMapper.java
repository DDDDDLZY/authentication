package com.security.basic.client.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.security.basic.vo.OauthClientVO;
import com.utils.entity.pojo.OauthClient;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OauthClientMapper {

	/**
     * 查询记录
     * @return 返回集合
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
     * @param oauthClient 待删除的记录
     * @return 返回影响行数
     */
	int delete(OauthClient oauthClient);

	IPage<OauthClientVO> findPage(IPage page, @Param("body") OauthClientVO oauthClientVO);
}
