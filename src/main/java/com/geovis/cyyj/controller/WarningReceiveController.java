package com.geovis.cyyj.controller;


import com.geovis.cyyj.common.core.controller.BaseController;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.domain.R;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.DeliverWarningDTO;
import com.geovis.cyyj.dto.DeliverWarningDTO;
import com.geovis.cyyj.dto.WarningReceiveQueryDTO;
import com.geovis.cyyj.dto.WarningReceiveStatusDTO;
import com.geovis.cyyj.service.IWarningReceiveService;
import com.geovis.cyyj.vo.WarningReceiveVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 预警接收
 * </p>
 *
 * @author jay
 * @since 2023-10-18
 */
@RestController
@RequestMapping("/fzjz/warningReceive")
@RequiredArgsConstructor
@Api(value = "预警接收相关接口", tags = "预警接收相关接口")
@Slf4j
@Validated
public class WarningReceiveController extends BaseController {

    @Autowired
    private IWarningReceiveService iWarningReceiveService;

    /**
     * 分页查询预警下发列表
     */
    @ApiOperation(value = "分页查询预警接收列表", notes = "分页查询预警接收列表")
    @GetMapping("/queryMainList")
    public TableDataInfo<WarningReceiveVO> queryMainList(WarningReceiveQueryDTO warningReceiveQueryDTO, PageQuery pageQuery) {
        return iWarningReceiveService.queryMainList(warningReceiveQueryDTO, pageQuery);
    }

    @ApiOperation(value = "发布预警", notes = "发布预警")
    @PostMapping({"/deliverWarning"})
    public R deliverWarning(@Validated @RequestBody DeliverWarningDTO deliverWarningDTO) {
        if(iWarningReceiveService.deliverWarning(deliverWarningDTO)){
            return R.ok("预警发布成功");
        }
        return R.fail("预警发布失败");
    }

    @ApiOperation(value = "预警接受状态变更", notes = "预警接受状态变更")
    @PostMapping({"/changeStatus"})
    public R changeStatus(@Validated @RequestBody WarningReceiveStatusDTO warningReceiveStatusDTO) {
        if(iWarningReceiveService.changeStatus(warningReceiveStatusDTO)){
            return R.ok("预警接受状态变更成功");
        }
        return R.fail("预警接受状态变更失败");
    }
}
