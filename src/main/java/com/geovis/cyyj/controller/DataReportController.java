package com.geovis.cyyj.controller;

import com.geovis.cyyj.common.core.controller.BaseController;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.domain.R;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.*;
import com.geovis.cyyj.service.*;
import com.geovis.cyyj.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 数据上传
 * </p>
 *
 * @author jay
 * @since 2023-10-18
 */
@RestController
@RequestMapping("/fzjz/dataReport")
@RequiredArgsConstructor
@Api(value = "数据上传相关接口", tags = "数据上传相关接口")
@Slf4j
@Validated
public class DataReportController extends BaseController {

    @Autowired
    private IDataReportService iDataReportService;

    /**
     * 分页查询数据上传列表
     */
    @ApiOperation(value = "分页查询数据上传列表", notes = "分页查询数据上传列表")
    @GetMapping("/queryMainList")
    public TableDataInfo<DataReportVO> queryMainList(DataReportQueryDTO dataReportQueryDTO, PageQuery pageQuery) {
        return iDataReportService.queryMainList(dataReportQueryDTO, pageQuery);
    }

    @ApiOperation(value = "数据上传", notes = "数据上传")
    @PostMapping({"/dataReport"})
    public R dataReport(@Validated @RequestBody DataReportDTO dataReportDTO) {
        if(iDataReportService.dataReport(dataReportDTO)){
            return R.ok("通知发布成功");
        }
        return R.fail("通知发布失败");
    }

    @ApiOperation(value = "数据上报状态变更", notes = "数据上报状态变更")
    @PostMapping({"/changeStatus"})
    public R changeStatus(@Validated @RequestBody DataReportStatusDTO dataReportStatusDTO) {
        if(iDataReportService.changeStatus(dataReportStatusDTO)){
            return R.ok("数据上报状态变更成功");
        }
        return R.fail("数据上报状态变更失败");
    }
}
