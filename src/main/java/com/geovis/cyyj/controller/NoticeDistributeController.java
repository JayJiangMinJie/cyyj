package com.geovis.cyyj.controller;


import com.geovis.cyyj.common.core.controller.BaseController;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.domain.R;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.DeliverNoticeDTO;
import com.geovis.cyyj.dto.NoticeDistributeQueryDTO;
import com.geovis.cyyj.dto.NoticeProgressFeedbackDTO;
import com.geovis.cyyj.service.INoticeDistributeService;
import com.geovis.cyyj.service.INoticeProgressFeedbackService;
import com.geovis.cyyj.vo.NoticeDistributeVO;
import com.geovis.cyyj.vo.NoticeProgressFeedbackVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 通知下发
 * </p>
 *
 * @author jay
 * @since 2023-10-18
 */
@RestController
@RequestMapping("/fzjz/noticeDistribute")
@RequiredArgsConstructor
@Api(value = "通知下发相关接口", tags = "通知下发相关接口")
@Slf4j
@Validated
public class NoticeDistributeController extends BaseController {

    @Autowired
    private INoticeDistributeService iNoticeDistributeService;

    @Autowired
    private INoticeProgressFeedbackService iNoticeProgressFeedbackService;

    /**
     * 分页查询通知下发列表
     */
    @ApiOperation(value = "分页查询通知下发列表", notes = "分页查询通知下发列表")
    @GetMapping("/queryMainList")
    public TableDataInfo<NoticeDistributeVO> queryMainList(NoticeDistributeQueryDTO noticeDistributeQueryDTO, PageQuery pageQuery) {
        return iNoticeDistributeService.queryMainList(noticeDistributeQueryDTO, pageQuery);
    }

    /**
     * 分页查询进度反馈列表
     */
    @ApiOperation(value = "分页查询进度反馈列表", notes = "分页查询进度反馈列表")
    @GetMapping("/queryNoticeProgressFeedbackList")
    public TableDataInfo<NoticeProgressFeedbackVO> queryNoticeProgressFeedbackList(@RequestParam("noticeDistributeId") Integer noticeDistributeId, PageQuery pageQuery) {
        return iNoticeProgressFeedbackService.queryNoticeProgressFeedbackList(noticeDistributeId, pageQuery);
    }

    @ApiOperation(value = "更新进度反馈", notes = "更新进度反馈")
    @PostMapping({"/updateNoticeProgressFeedback"})
    public R updateNoticeProgressFeedback(@Validated @RequestBody NoticeProgressFeedbackDTO noticeProgressFeedbackDTO) {
        if(iNoticeProgressFeedbackService.updateNoticeProgressFeedback(noticeProgressFeedbackDTO)){
            return R.ok("更新进度反馈成功");
        }
        return R.fail("更新进度反馈失败");
    }

    @ApiOperation(value = "发布通知", notes = "发布通知")
    @PostMapping({"/deliverNotice"})
    public R deliverNotice(@Validated @RequestBody DeliverNoticeDTO deliverNoticeDTO) {
        if(iNoticeDistributeService.deliverNotice(deliverNoticeDTO)){
            return R.ok("通知发布成功");
        }
        return R.fail("通知发布失败");
    }

    @ApiOperation(value = "通知操作", notes = "通知操作")
    @PostMapping({"/operateNotice"})
    public R operateNotice(@RequestParam("noticeDistributeId") Integer noticeDistributeId, @RequestParam("userId") String userId, @RequestParam("operateType") String operateType) {
        if(iNoticeDistributeService.operateNotice(noticeDistributeId, userId, operateType)){
            return R.ok("通知操作成功");
        }
        return R.fail("通知操作失败");
    }

}
