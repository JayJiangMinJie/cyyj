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
import com.geovis.cyyj.dto.ArticleInsertDTO;
import com.geovis.cyyj.dto.PublicServerQueryDTO;
import com.geovis.cyyj.mapper.PublicServerMapper;
import com.geovis.cyyj.po.PublicServerPO;
import com.geovis.cyyj.service.IPublicServerService;
import com.geovis.cyyj.vo.PublicServerVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 公共服务 服务实现类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PublicServerServiceImpl extends ServiceImpl<PublicServerMapper, PublicServerPO> implements IPublicServerService {

    @Autowired
    private final PublicServerMapper publicServerMapper;

    /**
     * 分页查询文章列表
     */
    @Override
    public TableDataInfo<PublicServerVO> queryMainList(PublicServerQueryDTO publicServerQueryDTO, PageQuery pageQuery) {
        LambdaQueryWrapper<PublicServerPO> lqw = buildQueryWrapper(publicServerQueryDTO);
        Page<PublicServerVO> result = publicServerMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    private LambdaQueryWrapper<PublicServerPO> buildQueryWrapper(PublicServerQueryDTO publicServerQueryDTO) {
        LambdaQueryWrapper<PublicServerPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(publicServerQueryDTO.getKeyWord()), PublicServerPO::getTitle, publicServerQueryDTO.getKeyWord());
        lqw.eq(StringUtils.isNotBlank(publicServerQueryDTO.getUserId()), PublicServerPO::getUserId, publicServerQueryDTO.getUserId());
        lqw.ge(publicServerQueryDTO.getStartTime() != null, PublicServerPO::getCreateTime, publicServerQueryDTO.getStartTime());
        lqw.lt(publicServerQueryDTO.getEndTime() != null, PublicServerPO::getCreateTime, publicServerQueryDTO.getEndTime());
        return lqw;
    }

    /**
     * 新增文章
     */
    @Override
    public Boolean insertArticle(ArticleInsertDTO articleInsertDTO) {
        PublicServerPO publicServerPO = BeanCopyUtils.copy(articleInsertDTO, PublicServerPO.class);
        return publicServerMapper.insertOrUpdate(publicServerPO);
    }

    /**
     * 删除文章
     */
    @Override
    public Boolean deleteArticle(int publicServerId) {
        LambdaUpdateWrapper<PublicServerPO> reportLuw = Wrappers.lambdaUpdate();
        reportLuw.eq(publicServerId != 0, PublicServerPO::getId, publicServerId);
        int resultDelete = publicServerMapper.delete(reportLuw);
        if(resultDelete > 0){
            return true;
        }
        return false;
    }


}
