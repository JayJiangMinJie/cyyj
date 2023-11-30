package com.geovis.cyyj.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.InsertResponseChangeLogDTO;
import com.geovis.cyyj.dto.ResponseChangeLogQueryDTO;
import com.geovis.cyyj.po.ResponseChangeLogPO;
import com.geovis.cyyj.vo.ResponseChangeLogVO;

import java.util.List;

/**
 * <p>
 * 响应历史 服务类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
public interface IResponseChangeLogService extends IService<ResponseChangeLogPO> {

    /**
     * 分页查询响应历史
     */
    TableDataInfo<ResponseChangeLogVO> queryMainList(ResponseChangeLogQueryDTO noticeReceiveQueryDTO, PageQuery pageQuery);

    List<ResponseChangeLogPO> queryLogList(ResponseChangeLogQueryDTO responseChangeLogQueryDTO);

    /**
     * 新增响应历史
     */
    Boolean insertResponseLog(InsertResponseChangeLogDTO insertResponseChangeLogDTO);

}
