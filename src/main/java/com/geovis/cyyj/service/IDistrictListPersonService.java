package com.geovis.cyyj.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.cyyj.po.DistrictListPersonPO;
import com.geovis.cyyj.vo.DistrictListPersonVO;

import java.util.List;

/**
 * <p>
 * 区划查询 服务类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
public interface IDistrictListPersonService extends IService<DistrictListPersonPO> {

    /**
     * 查询区划树及人员
     */
    List<DistrictListPersonVO> getDistrictPerson();

}
