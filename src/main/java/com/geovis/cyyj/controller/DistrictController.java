package com.geovis.cyyj.controller;

import com.geovis.cyyj.common.core.domain.R;
import com.geovis.cyyj.service.IDistrictListPersonService;
import com.geovis.cyyj.service.IDistrictListService;
import com.geovis.cyyj.vo.DistrictListPersonVO;
import com.geovis.cyyj.vo.DistrictListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/district")
@Api(value = "DistrictController", tags = "行政区划接口")
@Slf4j
public class DistrictController {

    @Autowired
    private IDistrictListService iDistrictListService;

    @Autowired
    private IDistrictListPersonService iDistrictListPersonService;

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

    /**
     * 查询行政区划人员
     */
    @ApiOperation(value = "查询行政区划人员", notes = "查询行政区划人员")
    @GetMapping("/districtPerson")
    public R queryDistrictPerson() {
        Map<String, List<Map<String, String>>> districtPersonMap = new HashMap<>();
        //查询区县及人员
        List<DistrictListPersonVO> districtPersonList = iDistrictListPersonService.getDistrictPerson();
        List<Map<String, String>> userMapList;
        Map<String, String> userMap;
        for(DistrictListPersonVO districtListPersonVO : districtPersonList){
            String userName = districtListPersonVO.getUserName();
            String userId = districtListPersonVO.getUserId();
            String orgName = districtListPersonVO.getOrgName();
            userMap = new HashMap<>();
            userMap.put(userName, userId);
            if(districtPersonMap.containsKey(orgName)){
                userMapList = districtPersonMap.get(orgName);
                userMapList.add(userMap);
            }else {
                userMapList = new ArrayList<>();
                userMapList.add(userMap);
                districtPersonMap.put(orgName, userMapList);
            }
        }
        return R.ok(districtPersonMap);
    }

}
