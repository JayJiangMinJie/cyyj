package com.geovis.cyyj.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.cyyj.po.DistrictListPO;
import com.geovis.cyyj.vo.DistrictListPersonVO;
import com.geovis.cyyj.vo.DistrictListVO;

import java.util.List;

/**
 * <p>
 * 区划查询 服务类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
public interface IDistrictListService extends IService<DistrictListPO> {

    /**
     * 查询区划树
     */
    List<DistrictListVO> getDistrict(String level);

}
