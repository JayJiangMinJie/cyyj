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
    public R getStatisticDataList() {
        List<StatisticDataVO> statisticDataVOList = iStatisticDataService.getStatisticDataList();
        return R.ok(statisticDataVOList);
    }

    @ApiOperation(value = "获取统计任务进度反馈统计", notes = "获取统计任务进度反馈统计")
    @PostMapping({"/getStatisticTaskFeedback"})
    public R getStatisticTaskFeedback() {
        StatisticTaskFeedbackVO statisticTaskFeedbackVO = iStatisticDataService.getStatisticTaskFeedback();
        return R.ok(statisticTaskFeedbackVO);
    }

    @ApiOperation(value = "获取统计任务进度反馈列表", notes = "获取统计任务进度反馈列表")
    @PostMapping({"/getStatisticTaskFeedbackList"})
    public TableDataInfo<StatisticTaskFeedbackListVO> getStatisticTaskFeedbackList(@RequestParam("statisticTaskId") int statisticTaskId, PageQuery pageQuery) {
        return iStatisticTaskProgressFeedbackService.getStatisticTaskFeedbackList(statisticTaskId, pageQuery);
    }

    @ApiOperation(value = "新增更新进度反馈", notes = "新增更新进度反馈")
    @PostMapping({"/addOrUpdateProgressFeedback"})
    public R addOrUpdateProgressFeedback(@Validated @RequestBody StatisticTaskProgressFeedbackDTO statisticTaskProgressFeedbackDTO) {
        if(iStatisticTaskProgressFeedbackService.addOrUpdateProgressFeedback(statisticTaskProgressFeedbackDTO)){
            return R.ok("新增进度反馈成功");
        }
        return R.fail("新增进度反馈失败");
    }

    @ApiOperation(value = "统计任务操作", notes = "统计任务操作")
    @PostMapping({"/operateNotice"})
    public R operateNotice(@RequestParam("statisticTaskId") int statisticTaskId, @RequestParam("operateType") String operateType) {
        if(iStatisticTaskService.operateNotice(statisticTaskId, operateType)){
            return R.ok("任务操作成功");
        }
        return R.fail("任务操作失败");
    }
//
//    @ApiOperation(value = "修改机构信息", notes = "修改资源信息")
//    @PostMapping({"/update"})
//    public R update(@Validated @RequestBody SystemDeptUpdateDTO updateDTO) {
//        SystemDept systemDept = BeanUtil.toBean(updateDTO, SystemDept.class);
//        baseService.updateById(systemDept);
//        return R.ok();
//    }
//
//    @ApiOperation(value = "查询所有机构数据", notes = "查询所有机构数据")
//    @PostMapping("/list")
//    public R<List<SystemDept>> list(@RequestBody SystemDept queryDTO) {
//        LambdaQueryWrapper<SystemDept> wrapper = Wrappers.lambdaQuery(SystemDept.class).orderByAsc(SystemDept::getIdx);
//        if (ObjectUtil.isNotEmpty(queryDTO)) {
//            wrapper.like(ObjectUtil.isNotEmpty(queryDTO.getDeptName()), SystemDept::getDeptName, queryDTO.getDeptName())
//                    .like(ObjectUtil.isNotEmpty(queryDTO.getFullName()), SystemDept::getFullName, queryDTO.getFullName())
//                    .like(ObjectUtil.isNotEmpty(queryDTO.getDeptCode()), SystemDept::getDeptCode, queryDTO.getDeptCode())
//                    .eq(ObjectUtil.isNotEmpty(queryDTO.getParentId()), SystemDept::getParentId, queryDTO.getParentId())
//                    .eq(ObjectUtil.isNotEmpty(queryDTO.getTypeGrade()), SystemDept::getTypeGrade, queryDTO.getTypeGrade());
//        }
//        return R.ok(baseService.list(wrapper));
//    }
//
//    @ApiOperation(value = "批量删除机构数据", notes = "批量删除机构数据")
//    @ApiImplicitParams({@ApiImplicitParam(name = "idList", value = "删除id的list", required = true, dataType = "java.util.Set", paramType = "body")})
//    @PostMapping("/removeByIdList")
//    public R removeByIdList(@NotNull(message = "删除的id集合不能为空") @RequestBody(required = false) Set<String> idList) {
//        baseService.removeByIdList(idList);
//        return R.ok();
//    }

}
