package com.geovis.cyyj.controller;


import com.geovis.cyyj.common.core.controller.BaseController;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.domain.R;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.*;
import com.geovis.cyyj.service.IStatisticDataService;
import com.geovis.cyyj.service.IStatisticTaskProgressFeedbackService;
import com.geovis.cyyj.service.IStatisticTaskService;
import com.geovis.cyyj.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 统计任务
 * </p>
 *
 * @author jay
 * @since 2023-10-18
 */
@RestController
@RequestMapping("/fzjz/statisticTask")
@RequiredArgsConstructor
@Api(value = "统计任务相关接口", tags = "统计任务相关接口")
@Slf4j
@Validated
public class StatisticTaskController extends BaseController {

    @Autowired
    private IStatisticTaskService iStatisticTaskService;

    @Autowired
    private IStatisticDataService iStatisticDataService;

    @Autowired
    private IStatisticTaskProgressFeedbackService iStatisticTaskProgressFeedbackService;

    /**
     * 分页查询统计任务列表
     */
    @ApiOperation(value = "分页查询统计任务列表", notes = "分页查询统计任务列表")
    @GetMapping("/queryMainList")
    public TableDataInfo<StatisticTaskVO> queryMainList(StatisticTaskQueryDTO statisticTaskQueryDTO, PageQuery pageQuery) {
        return iStatisticTaskService.queryMainList(statisticTaskQueryDTO, pageQuery);
    }

//    /**
//     * 分页查询进度反馈列表
//     */
//    @ApiOperation(value = "分页查询进度反馈列表", notes = "分页查询进度反馈列表")
//    @GetMapping("/queryProgressFeedbackList")
//    public TableDataInfo<ProgressFeedbackVO> queryProgressFeedbackList(@RequestParam("statisticTaskId") int statisticTaskId, PageQuery pageQuery) {
//        return iProgressFeedbackService.queryProgressFeedbackList(statisticTaskId, pageQuery);
//    }

    @ApiOperation(value = "发布任务", notes = "发布任务")
    @PostMapping({"/deliverTask"})
    public R deliverTask(@Validated @RequestBody DeliverTaskDTO deliverTaskDTO) {
        if(iStatisticTaskService.deliverTask(deliverTaskDTO)){
            return R.ok("任务发布成功");
        }
        return R.fail("任务发布失败");
    }

    @ApiOperation(value = "统计数据上传", notes = "统计数据上传")
    @PostMapping({"/statisticDataUpload"})
    public R statisticDataUpload(@Validated @RequestBody StatisticDataDTO statisticDataDTO) {
        if(iStatisticDataService.statisticDataUpload(statisticDataDTO)){
            return R.ok("统计数据录入成功");
        }
        return R.fail("统计数据录入失败");
    }

    @ApiOperation(value = "获取统计数据列表", notes = "获取统计数据列表")
    @PostMapping({"/getStatisticDataList"})
    public R getStatisticDataList(@RequestParam("statisticTaskId") Integer taskId, @RequestParam(value = "userId", required = false) String userId) {
        List<StatisticDataVO> statisticDataVOList = iStatisticDataService.getStatisticDataList(taskId, userId);
        return R.ok(statisticDataVOList);
    }

    @ApiOperation(value = "获取统计任务进度反馈统计", notes = "获取统计任务进度反馈统计")
    @PostMapping({"/getStatisticTaskFeedback"})
    public R getStatisticTaskFeedback(@RequestParam("statisticTaskId") Integer taskId, @RequestParam("statisticTaskId") String userId) {
        StatisticTaskFeedbackVO statisticTaskFeedbackVO = iStatisticDataService.getStatisticTaskFeedback(taskId, userId);
        return R.ok(statisticTaskFeedbackVO);
    }

    @ApiOperation(value = "获取统计任务进度反馈列表", notes = "获取统计任务进度反馈列表")
    @PostMapping({"/getStatisticTaskFeedbackList"})
    public TableDataInfo<StatisticTaskFeedbackListVO> getStatisticTaskFeedbackList(@RequestParam("statisticTaskId") Integer statisticTaskId,
                                                                                   @RequestParam(value = "userId", required = false) String userId,
                                                                                   PageQuery pageQuery) {
        return iStatisticTaskProgressFeedbackService.getStatisticTaskFeedbackList(statisticTaskId, userId, pageQuery);
    }

    @ApiOperation(value = "更新进度反馈", notes = "更新进度反馈")
    @PostMapping({"/updateProgressFeedback"})
    public R updateProgressFeedback(@Validated @RequestBody StatisticTaskProgressFeedbackDTO statisticTaskProgressFeedbackDTO) {
        if(iStatisticTaskProgressFeedbackService.updateProgressFeedback(statisticTaskProgressFeedbackDTO)){
            return R.ok("更新进度反馈成功");
        }
        return R.fail("更新进度反馈失败");
    }

    @ApiOperation(value = "统计任务操作", notes = "统计任务操作")
    @PostMapping({"/operateTask"})
    public R operateTask(@RequestParam("statisticTaskId") Integer statisticTaskId, @RequestParam("userId") String userId, @RequestParam("operateType") String operateType) {
        if(iStatisticTaskService.operateTask(statisticTaskId, userId, operateType)){
            return R.ok("任务操作成功");
        }
        return R.fail("任务操作失败");
    }

}
