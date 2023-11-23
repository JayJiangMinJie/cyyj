package com.geovis.cyyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.cyyj.common.utils.BeanCopyUtils;
import com.geovis.cyyj.mapper.DistrictListMapper;
import com.geovis.cyyj.po.DistrictListPO;
import com.geovis.cyyj.po.DistrictListPersonPO;
import com.geovis.cyyj.service.IDistrictListService;
import com.geovis.cyyj.vo.DistrictListPersonVO;
import com.geovis.cyyj.vo.DistrictListVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 通知下发 服务实现类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DistrictListServiceImpl extends ServiceImpl<DistrictListMapper, DistrictListPO> implements IDistrictListService {

    @Autowired
    private final DistrictListMapper baseMapper;

    /**
     * 分页查询通知下发列表
     */
    @Override
    public List<DistrictListVO> getDistrict(String level) {
        LambdaQueryWrapper<DistrictListPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(DistrictListPO::getLevel, level);
        List<DistrictListPO> result = baseMapper.selectList(lqw);
        List<DistrictListVO> districtListVOS = new ArrayList<>();
        DistrictListVO districtListVO;
        for(DistrictListPO districtListPO : result){
            districtListVO = BeanCopyUtils.copy(districtListPO, DistrictListVO.class);
            districtListVOS.add(districtListVO);
            districtListVO.setCode(districtListPO.getCode());
            if(districtListPO.getParentId() != null){
                districtListVO.setParentId(districtListPO.getParentId());
            }
        }
        return districtListVOS;
    }
}
