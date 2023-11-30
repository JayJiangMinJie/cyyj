package com.geovis.cyyj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.ArticleInsertDTO;
import com.geovis.cyyj.dto.PublicServerQueryDTO;
import com.geovis.cyyj.po.PublicServerPO;
import com.geovis.cyyj.vo.PublicServerVO;

/**
 * <p>
 * 公共服务 服务类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
public interface IPublicServerService extends IService<PublicServerPO> {

    /**
     * 分页查询公共服务数据
     */
    TableDataInfo<PublicServerVO> queryMainList(PublicServerQueryDTO publicServerQueryDTO, PageQuery pageQuery);

    /**
     * 新增文章
     */
    Boolean insertOrUpdateArticle(ArticleInsertDTO articleInsertDTO);

    /**
     * 删除文章
     */
    Boolean deleteArticle(int publicServerId);

}
