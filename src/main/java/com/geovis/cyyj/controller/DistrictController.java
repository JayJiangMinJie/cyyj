package com.geovis.cyyj.controller;

import com.geovis.cyyj.common.core.domain.R;
import com.geovis.cyyj.service.IDistrictListService;
import com.geovis.cyyj.vo.DistrictListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/district")
@Api(value = "DistrictController", tags = "行政区划接口")
@Slf4j
public class DistrictController {

    @Autowired
    private IDistrictListService iDistrictListService;

    /**
     * 查询行政树
     */
    @ApiOperation(value = "查询行政树", notes = "查询行政树")
    @GetMapping("/districtTree")
    public R queryDistrictTree() {
        //查询区县
        List<DistrictListVO> districtList = iDistrictListService.getDistrict("1");
        for (int i = 0; i < districtList.size(); i++) {
//            districtList.get(i).setChildren(districtList.subList(1, districtList.size()));
            List<DistrictListVO> city = iDistrictListService.getDistrict("2");
            // 将街道插入省份子列表
            districtList.get(i).setChildren(city);
        }
        return R.ok(districtList);
    }

}
