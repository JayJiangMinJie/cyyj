package com.geovis.cyyj.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.DeliverNoticeDTO;
import com.geovis.cyyj.dto.GroupInsertDTO;
import com.geovis.cyyj.dto.GroupManagementQueryDTO;
import com.geovis.cyyj.po.GroupManagementPO;
import com.geovis.cyyj.vo.GroupManagementVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 群组管理 服务类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
public interface IGroupManagementService extends IService<GroupManagementPO> {

    /**
     * 分页查询群组管理数据
     */
    TableDataInfo<GroupManagementVO> queryMainList(GroupManagementQueryDTO groupManagementQueryDTO, PageQuery pageQuery);

    /**
     * 新增群组管理
     */
    Boolean addOrUpdateGroup(GroupInsertDTO groupInsertDTO);

    /**
     * 群组管理删除
     */
    @Transactional(rollbackFor = Exception.class)
    Boolean deleteGroup(Integer id);

}
