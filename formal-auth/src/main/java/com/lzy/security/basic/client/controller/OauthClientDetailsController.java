package com.lzy.security.basic.client.controller;

import com.lzy.security.basic.client.service.OauthClientDetailsService;
import com.lzy.security.basic.vo.OauthClientDetailsVO;
import com.utils.common.annotation.LogApi;
import com.utils.entity.PageObject;
import com.utils.entity.Result;
import com.utils.entity.pojo.OauthClientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: lzy
 * @Date: 2021/12/30 14:52
 */
@RequestMapping("/oauthClientDetails/")
@RestController
//(tags = "客户端配置接口")
public class OauthClientDetailsController {

    @Autowired
    private OauthClientDetailsService oauthClientDetailsService;

    /**
     * 获取分页列表
     *
     * @return
     */
    @GetMapping("getPage")
    @LogApi("客户端配置接口/分页")
    //(value = "查询列表数据")
    public PageObject getPage(OauthClientDetailsVO oauthClientDetailsVO) {
        PageObject pageObject = null;
        try {
            pageObject = oauthClientDetailsService.getPage(oauthClientDetailsVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageObject;
    }

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    @GetMapping("list")
    @LogApi("客户端配置接口/列表数据")
    //(value = "列表数据")
    public Result listAll(OauthClientDetailsVO oauthClientDetailsVO) {
        return Result.success(oauthClientDetailsService.listAll(oauthClientDetailsVO));
    }


    /**
     * @param oauthClientDetails 新增的记录
     * @return 返回影响行数
     */
    @PostMapping("insert")
    //(value = "新增数据")
    public int insertIgnoreNull(@RequestBody OauthClientDetails oauthClientDetails) {
        return oauthClientDetailsService.insertIgnoreNull(oauthClientDetails);
    }


    /**
     * @param oauthClientDetails 修改的记录
     * @return 返回影响行数
     */
    @PutMapping("updateIgnoreNull")
    //(value = "更新数据")
    public int updateIgnoreNull(@RequestBody OauthClientDetails oauthClientDetails) {
        return oauthClientDetailsService.updateIgnoreNull(oauthClientDetails);
    }

    /**
     * @param oauthClientDetails 待删除的记录
     * @return 返回影响行数
     */
    @DeleteMapping("delete")
    //(value = "删除数据")
    public int delete(@RequestBody OauthClientDetails oauthClientDetails) {
        return oauthClientDetailsService.delete(oauthClientDetails);
    }

}
