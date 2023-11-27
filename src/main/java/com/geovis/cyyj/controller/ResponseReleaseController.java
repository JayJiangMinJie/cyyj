package com.geovis.cyyj.controller;


import com.geovis.cyyj.common.core.controller.BaseController;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.domain.R;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.DeliverResponseDTO;
import com.geovis.cyyj.dto.ResponseReleaseQueryDTO;
import com.geovis.cyyj.dto.ResponseProgressFeedbackDTO;
import com.geovis.cyyj.service.IResponseReleaseService;
import com.geovis.cyyj.service.IResponseProgressFeedbackService;
import com.geovis.cyyj.vo.ResponseReleaseVO;
import com.geovis.cyyj.vo.ResponseProgressFeedbackVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 响应下发
 * </p>
 *
 * @author jay
 * @since 2023-10-18
 */
@RestController
@RequestMapping("/fzjz/responseRelease")
@RequiredArgsConstructor
@Api(value = "响应下发相关接口", tags = "响应下发相关接口")
@Slf4j
@Validated
public class ResponseReleaseController extends BaseController {

    @Autowired
    private IResponseReleaseService iResponseReleaseService;

    @Autowired
    private IResponseProgressFeedbackService iResponseProgressFeedbackService;

    /**
     * 分页查询响应下发列表
     */
    @ApiOperation(value = "分页查询响应下发列表", notes = "分页查询响应下发列表")
    @GetMapping("/queryMainList")
    public TableDataInfo<ResponseReleaseVO> queryMainList(ResponseReleaseQueryDTO responseReleaseQueryDTO, PageQuery pageQuery) {
        return iResponseReleaseService.queryMainList(responseReleaseQueryDTO, pageQuery);
    }

    @ApiOperation(value = "发布响应", notes = "发布响应")
    @PostMapping({"/deliverResponse"})
    public R deliverResponse(@Validated @RequestBody DeliverResponseDTO deliverResponseDTO) {
        if(iResponseReleaseService.deliverResponse(deliverResponseDTO)){
            return R.ok("响应发布成功");
        }
        return R.fail("响应发布失败");
    }

    @ApiOperation(value = "响应操作", notes = "响应操作")
    @PostMapping({"/operateResponse"})
    public R operateResponse(@RequestParam("responseReleaseId") Integer responseReleaseId, @RequestParam("userId") String userId, @RequestParam("operateType") String operateType) {
        if(iResponseReleaseService.operateResponse(responseReleaseId, userId, operateType)){
            return R.ok("响应操作成功");
        }
        return R.fail("响应操作失败");
    }

    /**
     * 分页查询进度反馈列表
     */
    @ApiOperation(value = "分页查询进度反馈列表", notes = "分页查询进度反馈列表")
    @GetMapping("/queryResponseProgressFeedbackList")
    public TableDataInfo<ResponseProgressFeedbackVO> queryResponseProgressFeedbackList(@RequestParam("responseReleaseId") Integer responseReleaseId,
                                                                                       @RequestParam("userId") String userId,
                                                                                       PageQuery pageQuery) {
        return iResponseProgressFeedbackService.queryResponseProgressFeedbackList(responseReleaseId, userId, pageQuery);
    }

    @ApiOperation(value = "新增更新进度反馈", notes = "新增更新进度反馈")
    @PostMapping({"/addOrUpdateResponseProgressFeedback"})
    public R addOrUpdateResponseProgressFeedback(@Validated @RequestBody ResponseProgressFeedbackDTO responseProgressFeedbackDTO) {
        if(iResponseProgressFeedbackService.addOrUpdateResponseProgressFeedback(responseProgressFeedbackDTO)){
            return R.ok("新增进度反馈成功");
        }
        return R.fail("新增进度反馈失败");
    }

}
