package com.security.basic.client.controller;

import com.security.basic.client.service.ClientLogService;
import com.security.basic.vo.ClientLogVO;
import com.utils.common.annotation.LogApi;
import com.utils.entity.PageObject;
import com.utils.entity.pojo.ClientLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: zhanyang
 * @Date: 2022-01-04 17:30:45
 */
@RequestMapping("/ClientLog/")
@RestController
//(tags = "客户端日志")
public class ClientLogController {

    @Autowired
    private ClientLogService clientLogService;

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    //(value = "List")
    @GetMapping("list")
    public List<ClientLog> listAll() {
        return clientLogService.listAll();
    }


    /**
     * @param clientLog 新增的记录
     * @return 返回影响行数
     */
    //(value = "新增记录")
    @LogApi("ClientLog/insert")
    @PostMapping("insert")
    public int insertIgnoreNull(@RequestBody ClientLog clientLog) {
        return clientLogService.insertIgnoreNull(clientLog);
    }


    /**
     * @param clientLog 修改的记录
     * @return 返回影响行数
     */
    //(value = "修改记录")
    @PutMapping("updateIgnoreNull")
    public int updateIgnoreNull(@RequestBody ClientLog clientLog) {
        return clientLogService.updateIgnoreNull(clientLog);
    }

    /**
     * @param clientLog 待删除的记录
     * @return 返回影响行数
     */
    //(value = "删除记录")
    @DeleteMapping("delete")
    public int delete(@RequestBody ClientLog clientLog) {
        return clientLogService.delete(clientLog);
    }

    /**
     * 分页列表
     *
     * @param clientLogVO 查询VO对象
     * @return 分页对象
     */
    //("分页列表")
    @LogApi("客户端日志/分页列表")
    @PostMapping("pageList")
    public PageObject pageList(@RequestBody ClientLogVO clientLogVO) {
        return clientLogService.pageList(clientLogVO);
    }

}
