package com.geovis.cyyj.controller;

import com.geovis.cyyj.common.core.controller.BaseController;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.domain.R;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.DeliverWarningDTO;
import com.geovis.cyyj.dto.DisasterWarningQueryDTO;
import com.geovis.cyyj.dto.WarningProgressFeedbackDTO;
import com.geovis.cyyj.dto.WarningProgressFeedbackDTO;
import com.geovis.cyyj.service.IDisasterWarningService;
import com.geovis.cyyj.service.IWarningProgressFeedbackService;
import com.geovis.cyyj.vo.DisasterWarningVO;
import com.geovis.cyyj.vo.WarningProgressFeedbackVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 临灾预警
 * </p>
 *
 * @author jay
 * @since 2023-10-18
 */
@RestController
@RequestMapping("/fzjz/disasterWarning")
@RequiredArgsConstructor
@Api(value = "临灾预警相关接口", tags = "临灾预警相关接口")
@Slf4j
@Validated
public class DisasterWarningController extends BaseController {

    @Autowired
    private IDisasterWarningService iDisasterWarningService;

    @Autowired
    private IWarningProgressFeedbackService iWarningProgressFeedbackService;

    /**
     * 分页查询临灾预警列表
     */
    @ApiOperation(value = "分页查询临灾预警列表", notes = "分页查询临灾预警列表")
    @GetMapping("/queryMainList")
    public TableDataInfo<DisasterWarningVO> queryMainList(DisasterWarningQueryDTO disasterWarningQueryDTO, PageQuery pageQuery) {
        return iDisasterWarningService.queryMainList(disasterWarningQueryDTO, pageQuery);
    }

    @ApiOperation(value = "发布预警", notes = "发布预警")
    @PostMapping({"/deliverWarning"})
    public R deliverWarning(@Validated @RequestBody DeliverWarningDTO deliverWarningDTO) {
        if(iDisasterWarningService.deliverWarning(deliverWarningDTO)){
            return R.ok("预警发布成功");
        }
        return R.fail("预警发布失败");
    }

    @ApiOperation(value = "预警操作", notes = "预警操作")
    @PostMapping({"/operateWarning"})
    public R operateWarning(@RequestParam("disasterWarningId") Integer disasterWarningId, @RequestParam("userId") String userId, @RequestParam("operateType") String operateType) {
        if(iDisasterWarningService.operateWarning(disasterWarningId, userId, operateType)){
            return R.ok("预警操作成功");
        }
        return R.fail("预警操作失败");
    }

    /**
     * 分页查询进度反馈列表
     */
    @ApiOperation(value = "分页查询进度反馈列表", notes = "分页查询进度反馈列表")
    @GetMapping("/queryWarningProgressFeedbackList")
    public TableDataInfo<WarningProgressFeedbackVO> queryWarningProgressFeedbackList(@RequestParam("disasterWarningId") Integer disasterWarningId, String userId, PageQuery pageQuery) {
        return iWarningProgressFeedbackService.queryWarningProgressFeedbackList(disasterWarningId, userId, pageQuery);
    }

    @ApiOperation(value = "新增更新进度反馈", notes = "新增更新进度反馈")
    @PostMapping({"/addOrUpdateWarningProgressFeedback"})
    public R addOrUpdateWarningProgressFeedback(@Validated @RequestBody WarningProgressFeedbackDTO warningProgressFeedbackDTO) {
        if(iWarningProgressFeedbackService.addOrUpdateWarningProgressFeedback(warningProgressFeedbackDTO)){
            return R.ok("新增进度反馈成功");
        }
        return R.fail("新增进度反馈失败");
    }

}
