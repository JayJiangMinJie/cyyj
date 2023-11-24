package com.geovis.cyyj.controller;


import com.geovis.cyyj.common.core.controller.BaseController;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.domain.R;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.DeliverResponseDTO;
import com.geovis.cyyj.dto.ResponseReceiveQueryDTO;
import com.geovis.cyyj.dto.ResponseReceiveStatusDTO;
import com.geovis.cyyj.service.IResponseReceiveService;
import com.geovis.cyyj.vo.ResponseReceiveVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 响应接收
 * </p>
 *
 * @author jay
 * @since 2023-10-18
 */
@RestController
@RequestMapping("/fzjz/responseReceive")
@RequiredArgsConstructor
@Api(value = "响应接收相关接口", tags = "响应接收相关接口")
@Slf4j
@Validated
public class ResponseReceiveController extends BaseController {

    @Autowired
    private IResponseReceiveService iResponseReceiveService;

    /**
     * 分页查询响应下发列表
     */
    @ApiOperation(value = "分页查询响应接收列表", notes = "分页查询响应接收列表")
    @GetMapping("/queryMainList")
    public TableDataInfo<ResponseReceiveVO> queryMainList(ResponseReceiveQueryDTO responseReceiveQueryDTO, PageQuery pageQuery) {
        return iResponseReceiveService.queryMainList(responseReceiveQueryDTO, pageQuery);
    }

    @ApiOperation(value = "发布响应", notes = "发布响应")
    @PostMapping({"/deliverResponse"})
    public R deliverResponse(@Validated @RequestBody DeliverResponseDTO deliverResponseDTO) {
        if(iResponseReceiveService.deliverResponse(deliverResponseDTO)){
            return R.ok("响应发布成功");
        }
        return R.fail("响应发布失败");
    }

    @ApiOperation(value = "响应接受状态变更", notes = "响应接受状态变更")
    @PostMapping({"/changeStatus"})
    public R changeStatus(@Validated @RequestBody ResponseReceiveStatusDTO responseReceiveStatusDTO) {
        if(iResponseReceiveService.changeStatus(responseReceiveStatusDTO)){
            return R.ok("响应接受状态变更成功");
        }
        return R.fail("响应接受状态变更失败");
    }
}
