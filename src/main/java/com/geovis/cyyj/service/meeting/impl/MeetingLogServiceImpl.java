package com.geovis.cyyj.service.meeting.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.common.utils.BeanCopyUtils;
import com.geovis.cyyj.common.utils.StringUtils;
import com.geovis.cyyj.dto.meeting.MeetingLogDTO;
import com.geovis.cyyj.mapper.meeting.MeetingLogMapper;
import com.geovis.cyyj.po.meeting.MeetingLogPO;
import com.geovis.cyyj.service.meeting.MeetingLogService;
import com.geovis.cyyj.vo.meeting.MeetingLogVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MeetingLogServiceImpl implements MeetingLogService {

    private static final Logger logger = LoggerFactory.getLogger(MeetingLogServiceImpl.class);

    @Autowired
    MeetingLogMapper meetingLogMapper;

    @Override
    public Boolean createMeetingLog(MeetingLogDTO meetingLogDTO) {
        MeetingLogPO meetingLogPO = BeanCopyUtils.copy(meetingLogDTO, MeetingLogPO.class);
        meetingLogPO.setStatus("进行中");
        int createMeetingResult = meetingLogMapper.insert(meetingLogPO);
        if(createMeetingResult > 0){
            return true;
        }
        logger.warn("创建会议记录异常");
        return false;
    }

    @Override
    public Boolean updateMeetingLog(MeetingLogDTO meetingLogDTO) {
        LambdaQueryWrapper<MeetingLogPO> lqw = new LambdaQueryWrapper<>();
        MeetingLogPO meetingLogPOQuery = meetingLogMapper.selectOne(lqw);
        int logId = meetingLogPOQuery.getId();
        MeetingLogPO meetingLogPO = BeanCopyUtils.copy(meetingLogDTO, MeetingLogPO.class);
        LambdaUpdateWrapper<MeetingLogPO> luw = Wrappers.lambdaUpdate();
        luw.eq(MeetingLogPO::getId, logId);
        int updateMeeting = meetingLogMapper.update(meetingLogPO, luw);
        if(updateMeeting > 0){
            return true;
        }
        logger.warn("更新会议记录异常");
        return false;
    }

    @Override
    public TableDataInfo<MeetingLogVO> queryMeetingLog(String shouyingType, String shouyingId, PageQuery pageQuery) {
        LambdaQueryWrapper<MeetingLogPO> lqw = buildQueryWrapper(shouyingType, shouyingId);
        Page<MeetingLogVO> result = meetingLogMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    private LambdaQueryWrapper<MeetingLogPO> buildQueryWrapper(String shouyingType, String shouyingId) {
        LambdaQueryWrapper<MeetingLogPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(shouyingType), MeetingLogPO::getShouyingType, shouyingType);
        lqw.eq(StringUtils.isNotBlank(shouyingId), MeetingLogPO::getShouyingId, shouyingId);
        return lqw;
    }
}

