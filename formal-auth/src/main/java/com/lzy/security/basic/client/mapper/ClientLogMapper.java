package com.lzy.security.basic.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.utils.entity.pojo.ClientLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClientLogMapper extends BaseMapper<ClientLog> {

	/**
     * 查询记录
     * @return 返回集合
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
     * @param clientLog 待删除的记录
     * @return 返回影响行数
     */
	int delete(ClientLog clientLog);

}
