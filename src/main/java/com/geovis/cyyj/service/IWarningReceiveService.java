package com.geovis.cyyj.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.DeliverWarningDTO;
import com.geovis.cyyj.dto.WarningReceiveQueryDTO;
import com.geovis.cyyj.dto.WarningReceiveStatusDTO;
import com.geovis.cyyj.po.WarningReceivePO;
import com.geovis.cyyj.vo.WarningReceiveVO;

/**
 * <p>
 * 预警接收 服务类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
public interface IWarningReceiveService extends IService<WarningReceivePO> {

    /**
     * 分页查询预警下发数据
     */
    TableDataInfo<WarningReceiveVO> queryMainList(WarningReceiveQueryDTO noticeReceiveQueryDTO, PageQuery pageQuery);

    /**
     * 发布预警
     */
    Boolean deliverWarning(DeliverWarningDTO deliverWarningDTO);

    /**
     * 状态变更
     */
    Boolean changeStatus(WarningReceiveStatusDTO noticeReceiveStatusDTO);

}
