package com.geovis.cyyj.controller;

import com.geovis.cyyj.common.core.controller.BaseController;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.domain.R;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.ArticleInsertDTO;
import com.geovis.cyyj.dto.PublicServerQueryDTO;
import com.geovis.cyyj.service.IPublicServerService;
import com.geovis.cyyj.vo.PublicServerVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 公共服务
 * </p>
 *
 * @author jay
 * @since 2023-10-18
 */
@RestController
@RequestMapping("/fzjz/publicServer")
@RequiredArgsConstructor
@Api(value = "公共服务相关接口", tags = "公共服务相关接口")
@Slf4j
@Validated
public class PublicServerController extends BaseController {

    @Autowired
    private IPublicServerService iPublicServerService;

    /**
     * 分页查询公共服务列表
     */
    @ApiOperation(value = "分页查询公共服务列表", notes = "分页查询公共服务列表")
    @GetMapping("/queryMainList")
    public TableDataInfo<PublicServerVO> queryMainList(PublicServerQueryDTO publicServerQueryDTO, PageQuery pageQuery) {
        return iPublicServerService.queryMainList(publicServerQueryDTO, pageQuery);
    }

    @ApiOperation(value = "新增文章", notes = "新增文章")
    @PostMapping({"/insertArticle"})
    public R insertArticle(@Validated @RequestBody ArticleInsertDTO articleInsertDTO) {
        if(iPublicServerService.insertArticle(articleInsertDTO)){
            return R.ok("新增文章成功");
        }
        return R.fail("新增文章失败");
    }

    @ApiOperation(value = "文章删除操作", notes = "文章删除操作")
    @PostMapping({"/deleteArticle"})
    public R deleteArticle(@RequestParam("publicServerId") int publicServerId) {
        if(iPublicServerService.deleteArticle(publicServerId)){
            return R.ok("文章删除操作成功");
        }
        return R.fail("文章删除操作失败");
    }

}
