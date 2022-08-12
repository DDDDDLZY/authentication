package com.lzy.security.basic.client.controller;

import com.lzy.security.basic.client.service.OauthClientService;
import com.lzy.security.basic.vo.OauthClientVO;
import com.utils.common.annotation.LogApi;
import com.utils.entity.PageObject;
import com.utils.entity.Result;
import com.utils.entity.pojo.OauthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * lzy
 * 2021-12-31 14:15:13
 */
@RequestMapping("/oauthClient/")
@RestController
//(tags = "客户端")
public class OauthClientController {

    @Autowired
    private OauthClientService oauthClientService;

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    @GetMapping("list")
    //(value = "查询所有记录")
    public Result listAll() {
        return Result.success(oauthClientService.listAll());
    }

    /**
     * @param oauthClient 新增的记录oauthClientDetails
     * @return 返回影响行数
     */
    @PostMapping("insert")
    //(value = "新增的记录")
    public int insertIgnoreNull(@RequestBody OauthClient oauthClient) {
        return oauthClientService.insertIgnoreNull(oauthClient);
    }

    /**
     * @param oauthClient 修改的记录
     * @return 返回影响行数
     */
    @PostMapping("updateIgnoreNull")
    //(value = "修改的记录")
    public int updateIgnoreNull(@RequestBody OauthClient oauthClient) {
        //TODO 同时操作多表内容
        return oauthClientService.updateIgnoreNull(oauthClient);
    }

    /**
     * @param oauthClient 待删除的记录
     * @return 返回影响行数
     */
    @PostMapping("delete")
    //(value = "待删除的记录")
    public int delete(@RequestBody OauthClient oauthClient) {
        return oauthClientService.delete(oauthClient);
    }

    //("分页列表")
    @LogApi("客户端/分页列表")
    @PostMapping("pageList")
    public PageObject pageList(@RequestBody OauthClientVO oauthClientVO){
        return oauthClientService.pageList(oauthClientVO);
    }
}
