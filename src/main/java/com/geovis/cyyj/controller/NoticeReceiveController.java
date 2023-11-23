package com.geovis.cyyj.controller;


import com.geovis.cyyj.common.core.controller.BaseController;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.domain.R;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.DeliverNoticeDTO;
import com.geovis.cyyj.dto.NoticeReceiveQueryDTO;
import com.geovis.cyyj.dto.NoticeReceiveStatusDTO;
import com.geovis.cyyj.service.INoticeReceiveService;
import com.geovis.cyyj.vo.NoticeReceiveVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 通知接收
 * </p>
 *
 * @author jay
 * @since 2023-10-18
 */
@RestController
@RequestMapping("/fzjz/noticeReceive")
@RequiredArgsConstructor
@Api(value = "通知接收相关接口", tags = "通知接收相关接口")
@Slf4j
@Validated
public class NoticeReceiveController extends BaseController {

    @Autowired
    private INoticeReceiveService iNoticeReceiveService;

    /**
     * 分页查询通知下发列表
     */
    @ApiOperation(value = "分页查询通知接收列表", notes = "分页查询通知接收列表")
    @GetMapping("/queryMainList")
    public TableDataInfo<NoticeReceiveVO> queryMainList(NoticeReceiveQueryDTO noticeReceiveQueryDTO, PageQuery pageQuery) {
        return iNoticeReceiveService.queryMainList(noticeReceiveQueryDTO, pageQuery);
    }

    @ApiOperation(value = "发布通知", notes = "发布通知")
    @PostMapping({"/deliverNotice"})
    public R deliverNotice(@Validated @RequestBody DeliverNoticeDTO deliverNoticeDTO) {
        if(iNoticeReceiveService.deliverNotice(deliverNoticeDTO)){
            return R.ok("通知发布成功");
        }
        return R.fail("通知发布失败");
    }

    @ApiOperation(value = "通知接受状态变更", notes = "通知接受状态变更")
    @PostMapping({"/changeStatus"})
    public R changeStatus(@Validated @RequestBody NoticeReceiveStatusDTO noticeReceiveStatusDTO) {
        if(iNoticeReceiveService.changeStatus(noticeReceiveStatusDTO)){
            return R.ok("通知接受状态变更成功");
        }
        return R.fail("通知接受状态变更失败");
    }
}
