package com.geovis.cyyj.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.DeliverResponseDTO;
import com.geovis.cyyj.dto.ResponseReceiveQueryDTO;
import com.geovis.cyyj.dto.ResponseReceiveStatusDTO;
import com.geovis.cyyj.po.ResponseReceivePO;
import com.geovis.cyyj.vo.ResponseReceiveVO;

/**
 * <p>
 * 响应接收 服务类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
public interface IResponseReceiveService extends IService<ResponseReceivePO> {

    /**
     * 分页查询响应下发数据
     */
    TableDataInfo<ResponseReceiveVO> queryMainList(ResponseReceiveQueryDTO noticeReceiveQueryDTO, PageQuery pageQuery);

    /**
     * 发布响应
     */
    Boolean deliverResponse(DeliverResponseDTO deliverResponseDTO);

}
