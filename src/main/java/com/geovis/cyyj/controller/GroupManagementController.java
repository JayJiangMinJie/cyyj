package com.geovis.cyyj.controller;

import com.geovis.cyyj.service.IDistrictListPersonService;
import com.geovis.cyyj.service.IDistrictListService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/groupManagement")
@Api(value = "GroupManagementController", tags = "群组管理接口")
@Slf4j
public class GroupManagementController {

    @Autowired
    private IDistrictListService iDistrictListService;

    @Autowired
    private IDistrictListPersonService iDistrictListPersonService;

//    /**
//     * 分页查询群组列表
//     */
//    @ApiOperation(value = "分页查询群组列表", notes = "分页查询群组列表")
//    @GetMapping("/queryMainList")
//    public TableDataInfo<NoticeDistributeVO> queryMainList(NoticeDistributeQueryDTO noticeDistributeQueryDTO, PageQuery pageQuery) {
//        return iNoticeDistributeService.queryMainList(noticeDistributeQueryDTO, pageQuery);
//    }
//
//    @@ApiOperation(value = "新增更新群组", notes = "新增更新群组")
//    @PostMapping({"/addOrUpdateGroup"})
//    public R addOrUpdateGroup(@Validated @RequestBody NoticeProgressFeedbackDTO noticeProgressFeedbackDTO) {
//        if(iNoticeProgressFeedbackService.addOrUpdateGroup(noticeProgressFeedbackDTO)){
//            return R.ok("新增进度反馈成功");
//        }
//        return R.fail("新增进度反馈失败");
//    }
//
//    @ApiOperation(value = "删除群组", notes = "删除群组")
//    @PostMapping({"/deliverNotice"})
//    public R deliverNotice(@Validated @RequestBody DeliverNoticeDTO deliverNoticeDTO) {
//        if(iNoticeDistributeService.deliverNotice(deliverNoticeDTO)){
//            return R.ok("通知发布成功");
//        }
//        return R.fail("通知发布失败");
//    }

}
