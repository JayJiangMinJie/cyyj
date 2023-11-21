package com.geovis.cyyj.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geovis.cyyj.common.core.controller.BaseController;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.domain.R;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.DeliverNoticeDTO;
import com.geovis.cyyj.dto.NoticeDistributeDTO;
import com.geovis.cyyj.dto.ProgressFeedbackDTO;
import com.geovis.cyyj.service.INoticeDistributeService;
import com.geovis.cyyj.service.IProgressFeedbackService;
import com.geovis.cyyj.vo.NoticeDistributeVO;
import com.geovis.cyyj.vo.ProgressFeedbackVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Set;

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
    private IProgressFeedbackService iProgressFeedbackService;

    /**
     * 分页查询通知下发列表
     */
    @ApiOperation(value = "分页查询通知下发列表", notes = "分页查询通知下发列表")
    @GetMapping("/queryMainList")
    public TableDataInfo<NoticeDistributeVO> queryMainList(NoticeDistributeDTO noticeDistributeDTO, PageQuery pageQuery) {
        return iNoticeDistributeService.queryMainList(noticeDistributeDTO, pageQuery);
    }

    /**
     * 分页查询进度反馈列表
     */
    @ApiOperation(value = "分页查询进度反馈列表", notes = "分页查询进度反馈列表")
    @GetMapping("/queryProgressFeedbackList")
    public TableDataInfo<ProgressFeedbackVO> queryProgressFeedbackList(@RequestParam("noticeDistributeId") int noticeDistributeId, PageQuery pageQuery) {
        return iProgressFeedbackService.queryProgressFeedbackList(noticeDistributeId, pageQuery);
    }

    @ApiOperation(value = "新增更新进度反馈", notes = "新增更新进度反馈")
    @PostMapping({"/addOrUpdateProgressFeedback"})
    public R addOrUpdateProgressFeedback(@Validated @RequestBody ProgressFeedbackDTO progressFeedbackDTO) {
        if(iProgressFeedbackService.addOrUpdateProgressFeedback(progressFeedbackDTO)){
            return R.ok("新增进度反馈成功");
        }
        return R.fail("新增进度反馈失败");
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
    public R operateNotice(@RequestParam("noticeDistributeId") int noticeDistributeId, @RequestParam("operateType") String operateType) {
        if(iNoticeDistributeService.operateNotice(noticeDistributeId, operateType)){
            return R.ok("通知操作成功");
        }
        return R.fail("通知操作失败");
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
