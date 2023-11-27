package com.geovis.cyyj.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.DeliverResponseDTO;
import com.geovis.cyyj.dto.ResponseReleaseQueryDTO;
import com.geovis.cyyj.po.ResponseReleasePO;
import com.geovis.cyyj.vo.ResponseReleaseVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 通知下发 服务类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
public interface IResponseReleaseService extends IService<ResponseReleasePO> {

    /**
     * 分页查询通知下发数据
     */
    TableDataInfo<ResponseReleaseVO> queryMainList(ResponseReleaseQueryDTO noticeDistributeQueryDTO, PageQuery pageQuery);

    /**
     * 发布通知
     */
    Boolean deliverResponse(DeliverResponseDTO deliverResponseDTO);

    /**
     * 通知操作
     */
    @Transactional(rollbackFor = Exception.class)
    Boolean operateResponse(Integer noticeDistributeId, String userId, String operateType);

}
