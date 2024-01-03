package com.geovis.cyyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.common.utils.BeanCopyUtils;
import com.geovis.cyyj.common.utils.StringUtils;
import com.geovis.cyyj.dto.GroupInsertDTO;
import com.geovis.cyyj.dto.GroupManagementQueryDTO;
import com.geovis.cyyj.mapper.GroupManagementMapper;
import com.geovis.cyyj.po.DisasterWarningPO;
import com.geovis.cyyj.po.GroupManagementPO;
import com.geovis.cyyj.service.IGroupManagementService;
import com.geovis.cyyj.vo.GroupManagementVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * <p>
 * 群组管理 服务实现类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class GroupManagementServiceImpl extends ServiceImpl<GroupManagementMapper, GroupManagementPO> implements IGroupManagementService {

    @Autowired
    private final GroupManagementMapper groupManagementMapper;


    @Override
    public TableDataInfo<GroupManagementVO> queryMainList(GroupManagementQueryDTO groupManagementQueryDTO, PageQuery pageQuery) {
        LambdaQueryWrapper<GroupManagementPO> lqw = buildQueryWrapper(groupManagementQueryDTO);
        Page<GroupManagementVO> result = groupManagementMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    private LambdaQueryWrapper<GroupManagementPO> buildQueryWrapper(GroupManagementQueryDTO groupManagementQueryDTO) {
        LambdaQueryWrapper<GroupManagementPO> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(groupManagementQueryDTO.getKeyWord()), GroupManagementPO::getName, groupManagementQueryDTO.getKeyWord());
        lqw.eq(StringUtils.isNotBlank(groupManagementQueryDTO.getUserId()), GroupManagementPO::getUserId, groupManagementQueryDTO.getUserId());
        lqw.orderByDesc(GroupManagementPO::getCreateTime);
        return lqw;
    }

    @Override
    public Boolean addOrUpdateGroup(GroupInsertDTO groupInsertDTO) {
        if(groupInsertDTO.getId() != null){
            //更新
            LambdaQueryWrapper<GroupManagementPO> lambdaQueryWrapper = new LambdaQueryWrapper();
            lambdaQueryWrapper.eq(GroupManagementPO::getId, groupInsertDTO.getId());
            GroupManagementPO groupManagementPO = groupManagementMapper.selectOne(lambdaQueryWrapper);
            //先查询后更新
            groupManagementPO.setName(groupInsertDTO.getName());
            groupManagementPO.setPeopleContent(groupInsertDTO.getPeopleContent());
            groupManagementPO.setRemark(groupInsertDTO.getRemark());
            groupManagementPO.setPeopleNum(groupInsertDTO.getPeopleNum());
            int resultUpdateGroupManagement = groupManagementMapper.updateById(groupManagementPO);
            if(resultUpdateGroupManagement <= 0){
                log.error("resultUpdateNoticeFeedback Result failed, userid is : " + groupManagementPO.getUserId());
                return false;
            }
        }
        //新增
        GroupManagementPO groupManagementPO = BeanCopyUtils.copy(groupInsertDTO, GroupManagementPO.class);
        return groupManagementMapper.insertOrUpdate(groupManagementPO);
    }

    @Override
    public Boolean deleteGroup(Integer id) {
        LambdaUpdateWrapper<GroupManagementPO> reportLuw = Wrappers.lambdaUpdate();
        reportLuw.eq(id != 0, GroupManagementPO::getId, id);
        int resultDelete = groupManagementMapper.delete(reportLuw);
        if(resultDelete > 0){
            return true;
        }
        return false;
    }
}
