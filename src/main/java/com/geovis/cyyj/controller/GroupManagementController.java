package com.geovis.cyyj.controller;

import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.domain.R;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.GroupInsertDTO;
import com.geovis.cyyj.dto.GroupManagementQueryDTO;
import com.geovis.cyyj.service.IGroupManagementService;
import com.geovis.cyyj.vo.GroupManagementVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/groupManagement")
@Api(value = "GroupManagementController", tags = "群组管理接口")
@Slf4j
public class GroupManagementController {

    @Autowired
    private IGroupManagementService iGroupManagementService;

    /**
     * 分页查询群组列表
     */
    @ApiOperation(value = "分页查询群组列表", notes = "分页查询群组列表")
    @GetMapping("/queryMainList")
    public TableDataInfo<GroupManagementVO> queryMainList(GroupManagementQueryDTO groupManagementQueryDTO, PageQuery pageQuery) {
        return iGroupManagementService.queryMainList(groupManagementQueryDTO, pageQuery);
    }

    @ApiOperation(value = "新增更新群组", notes = "新增更新群组")
    @PostMapping({"/addOrUpdateGroup"})
    public R addOrUpdateGroup(@Validated @RequestBody GroupInsertDTO groupInsertDTO) {
        if(iGroupManagementService.addOrUpdateGroup(groupInsertDTO)){
            return R.ok("新增进度反馈成功");
        }
        return R.fail("新增进度反馈失败");
    }

    @ApiOperation(value = "删除群组", notes = "删除群组")
    @PostMapping({"/deleteGroup"})
    public R deleteGroup(@RequestParam("id") Integer id) {
        if(iGroupManagementService.deleteGroup(id)){
            return R.ok("通知发布成功");
        }
        return R.fail("通知发布失败");
    }

}
