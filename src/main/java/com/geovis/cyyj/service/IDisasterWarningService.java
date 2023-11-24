package com.geovis.cyyj.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.DeliverWarningDTO;
import com.geovis.cyyj.dto.DisasterWarningQueryDTO;
import com.geovis.cyyj.po.DisasterWarningPO;
import com.geovis.cyyj.vo.DisasterWarningVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 预警下发 服务类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
public interface IDisasterWarningService extends IService<DisasterWarningPO> {

    /**
     * 分页查询预警下发数据
     */
    TableDataInfo<DisasterWarningVO> queryMainList(DisasterWarningQueryDTO disasterWarningQueryDTO, PageQuery pageQuery);

    /**
     * 发布预警
     */
    Boolean deliverWarning(DeliverWarningDTO deliverWarningDTO);

    /**
     * 预警操作
     */
    @Transactional(rollbackFor = Exception.class)
    Boolean operateWarning(Integer noticeDistributeId, String operateType);

}
