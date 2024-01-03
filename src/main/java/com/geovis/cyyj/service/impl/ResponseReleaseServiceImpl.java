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
import com.geovis.cyyj.dto.*;
import com.geovis.cyyj.mapper.DistrictListPersonMapper;
import com.geovis.cyyj.mapper.ResponseReleaseMapper;
import com.geovis.cyyj.mapper.ResponseReceiveMapper;
import com.geovis.cyyj.po.*;
import com.geovis.cyyj.service.*;
import com.geovis.cyyj.vo.ResponseReleaseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 响应下发 服务实现类
 * </p>
 *
 * @author Jay
 * @since 2023-10-18
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ResponseReleaseServiceImpl extends ServiceImpl<ResponseReleaseMapper, ResponseReleasePO> implements IResponseReleaseService {

    @Autowired
    private final ResponseReleaseMapper responseReleaseMapper;

    @Autowired
    private final ResponseReceiveMapper responseReceiveMapper;

    @Autowired
    private final IResponseChangeLogService iResponseChangeLogService;

    @Autowired
    private IResponseReceiveService iResponseReceiveService;

    @Autowired
    private final DistrictListPersonMapper districtListPersonMapper;

    @Autowired
    private IResponseProgressFeedbackService iResponseProgressFeedbackService;
    /**
     * 分页查询响应下发列表
     */
    @Override
    public TableDataInfo<ResponseReleaseVO> queryMainList(ResponseReleaseQueryDTO responseReleaseQueryDTO, PageQuery pageQuery) {
        LambdaQueryWrapper<ResponseReleasePO> lqw = buildQueryWrapper(responseReleaseQueryDTO);
        Page<ResponseReleaseVO> result = responseReleaseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    private LambdaQueryWrapper<ResponseReleasePO> buildQueryWrapper(ResponseReleaseQueryDTO responseReleaseQueryDTO) {
        LambdaQueryWrapper<ResponseReleasePO> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(responseReleaseQueryDTO.getKeyWord()), ResponseReleasePO::getTitle, responseReleaseQueryDTO.getKeyWord());
        lqw.eq(StringUtils.isNotBlank(responseReleaseQueryDTO.getUserId()), ResponseReleasePO::getUserId, responseReleaseQueryDTO.getUserId());
        lqw.like(StringUtils.isNotBlank(responseReleaseQueryDTO.getRegion()), ResponseReleasePO::getReceiveUnit, responseReleaseQueryDTO.getRegion());
        lqw.eq(StringUtils.isNotBlank(responseReleaseQueryDTO.getType()), ResponseReleasePO::getType, responseReleaseQueryDTO.getType());
        lqw.eq(StringUtils.isNotBlank(responseReleaseQueryDTO.getStatus()), ResponseReleasePO::getStatus, responseReleaseQueryDTO.getStatus());
        lqw.ge(responseReleaseQueryDTO.getStartTime() != null, ResponseReleasePO::getCreateTime, responseReleaseQueryDTO.getStartTime());
        lqw.le(responseReleaseQueryDTO.getEndTime() != null, ResponseReleasePO::getCreateTime, responseReleaseQueryDTO.getEndTime());
        lqw.orderByDesc(ResponseReleasePO::getCreateTime);
        return lqw;
    }

    /**
     * 发布响应
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deliverResponse(DeliverResponseDTO deliverResponseDTO) {
        if(StringUtils.isEmpty(deliverResponseDTO.getReceiveUnit())){
            log.error("响应下发 receiveUnit is null, userid : " + deliverResponseDTO.getUserId());
            return false;
        }
        ResponseReleasePO responseReleasePO = BeanCopyUtils.copy(deliverResponseDTO, ResponseReleasePO.class);
        responseReleasePO.setStatus("未解除");
        responseReleasePO.setMaxLevel(deliverResponseDTO.getCurrentLevel());
        responseReleasePO.setMaxLevelStartTime(LocalDateTime.now());
        responseReleasePO.setCurrentLevelAdjustTime(LocalDateTime.now());
        int responseRelease = responseReleaseMapper.insertResponseRelease(responseReleasePO);
        if(responseRelease < 1){
            log.error("响应下发 insert into responseRelease failed, userid : " + deliverResponseDTO.getUserId());
            return false;
        }
        InsertResponseChangeLogDTO responseChangeLogDTO = BeanCopyUtils.copy(responseReleasePO, InsertResponseChangeLogDTO.class);
        responseChangeLogDTO.setResponseReleaseId(responseReleasePO.getId());
        Boolean responseLogResult = iResponseChangeLogService.insertResponseLog(responseChangeLogDTO);
        if(!responseLogResult){
            log.error("响应日志下发 insert into response log failed, userid : " + deliverResponseDTO.getUserId());
            return false;
        }
        ResponseReleasePO insertResponseReleaseResult = responseReleaseMapper.selectById(responseReleasePO.getId());
        if(insertResponseReleaseResult != null){
            //如果下发新增成功了，
            DeliverResponseDTO deliverResponse2ReceiveDTO = new DeliverResponseDTO();
            deliverResponse2ReceiveDTO.setTitle(insertResponseReleaseResult.getTitle());
            deliverResponse2ReceiveDTO.setTextContent(insertResponseReleaseResult.getTextContent());
            deliverResponse2ReceiveDTO.setCurrentLevel(insertResponseReleaseResult.getCurrentLevel());
            deliverResponse2ReceiveDTO.setCurrentLevelAdjustTime(responseReleasePO.getCurrentLevelAdjustTime());
            deliverResponse2ReceiveDTO.setMaxLevel(responseReleasePO.getMaxLevel());
            deliverResponse2ReceiveDTO.setMaxLevelStartTime(responseReleasePO.getMaxLevelStartTime());
            deliverResponse2ReceiveDTO.setReleaseTime(insertResponseReleaseResult.getReleaseTime());
            deliverResponse2ReceiveDTO.setType(insertResponseReleaseResult.getType());
            deliverResponse2ReceiveDTO.setStatus(insertResponseReleaseResult.getStatus());
            deliverResponse2ReceiveDTO.setReceiveUnit("");
            deliverResponse2ReceiveDTO.setFilePath("");
            deliverResponse2ReceiveDTO.setResponseContent(insertResponseReleaseResult.getResponseContent());
            deliverResponse2ReceiveDTO.setParentUserId(insertResponseReleaseResult.getUserId());
            deliverResponse2ReceiveDTO.setResponseReleaseId(responseReleasePO.getId());
            //再生成进度反馈的数据
            ResponseProgressFeedbackDTO responseProgressFeedbackDTO = new ResponseProgressFeedbackDTO();
            responseProgressFeedbackDTO.setResponseReleaseId(responseReleasePO.getId());
//            responseProgressFeedbackDTO.setEndTime(responseReleasePO.getReleaseTime());
            responseProgressFeedbackDTO.setCity("青岛市");
            responseProgressFeedbackDTO.setCounty("城阳区应急管理局");
//            responseProgressFeedbackDTO.setOperatePerson(responseReleasePO.get());
            responseProgressFeedbackDTO.setReceiveStatus("未读");
            responseProgressFeedbackDTO.setParentUserId(responseReleasePO.getUserId());
            responseProgressFeedbackDTO.setFilePath(responseReleasePO.getFilePath());
            //这里暂时查询本地表人员
            //用查询到的接收单位list找出该给哪些单位发信息
            String[] unitArrays = deliverResponseDTO.getReceiveUnit().split(",");
            List<String> unitList = Arrays.asList(unitArrays);
            LambdaQueryWrapper<DistrictListPersonPO> lambdaQueryWrapper = new LambdaQueryWrapper();
            lambdaQueryWrapper.in(DistrictListPersonPO::getOrgName, unitList);
            List<DistrictListPersonPO> districtListPersonPOList = districtListPersonMapper.selectList(lambdaQueryWrapper);
            Map<String, String> revceiveMap = new HashMap<>();
            for(DistrictListPersonPO districtListPersonPO : districtListPersonPOList){
                revceiveMap.put(districtListPersonPO.getOrgName(), districtListPersonPO.getUserId());
            }
            //给各个接收单位发响应
            for(Map.Entry<String, String> entry : revceiveMap.entrySet()){
                deliverResponse2ReceiveDTO.setUserId(entry.getValue());
                Boolean insertResult = iResponseReceiveService.deliverResponse(deliverResponse2ReceiveDTO);
                if(!insertResult){
                    throw new RuntimeException("insert into receive unit failed, userid is : " + deliverResponse2ReceiveDTO.getUserId() + " parentid is : " + deliverResponse2ReceiveDTO.getParentUserId());
                }
                //进度反馈也要给各个单位默认发一个
//                responseProgressFeedbackDTO.setUserId(entry.getValue());
//                responseProgressFeedbackDTO.setOrgName(entry.getKey());
//                Boolean insertFeedbackResult = iResponseProgressFeedbackService.addOrUpdateResponseProgressFeedback(responseProgressFeedbackDTO);
//                if(!insertFeedbackResult){
//                    throw new RuntimeException("insert into feedback failed, userid is : " + responseProgressFeedbackDTO.getUserId() + " parentid is : " + responseProgressFeedbackDTO.getParentUserId());
//                }
            }
            return true;
        }else {
            log.error("响应下发 insertResponseReleaseResult is false, user_id = " + deliverResponseDTO.getUserId());
            return false;
        }
    }
    /**
     * 响应操作
     */
    @Override
//    @Transactional(rollbackFor = Exception.class)
    public Boolean operateResponse(ResponseChangeDTO responseChangeDTO) {
        // 获取SqlSession
        String operateType = responseChangeDTO.getOperateType();
        Integer responseReleaseId = responseChangeDTO.getResponseReleaseId();
        String userId = responseChangeDTO.getUserId();
        ResponseReleasePO releasePO = responseReleaseMapper.selectById(responseReleaseId);
        ResponseReleasePO responseReleasePO = new ResponseReleasePO();
        LambdaUpdateWrapper<ResponseReleasePO> luw = Wrappers.lambdaUpdate();
        //接收部分
        LambdaUpdateWrapper<ResponseReceivePO> luwReceive = Wrappers.lambdaUpdate();
        luwReceive.eq(ResponseReceivePO::getResponseReleaseId, responseReleaseId);


//        ResponseReceivePO responseReceivePO = BeanCopyUtils.copy(responseChangeDTO, ResponseReceivePO.class);
//        LambdaQueryWrapper<ResponseReceivePO> lqwReceive = Wrappers.lambdaQuery();
//        lqwReceive.eq(ResponseReceivePO::getResponseReleaseId, responseReleaseId);
//        ResponseReceivePO responseReceivePO = responseReceiveMapper.selectList();
        int resultSecure = 0;
        int resultUpdate = 0;
        if("secure".equals(operateType)){
            //撤回响应之后下发方消息还在，状态变成已撤回，接收方消息删除
            luw.eq(ResponseReleasePO::getId, responseReleaseId);
            releasePO.setStatus("已解除");
            resultSecure = responseReleaseMapper.update(releasePO, luw);
            if(resultSecure == 0){
             log.warn("下发响应表解除失败，responseReleaseId： " + responseReleaseId);
            }
            //更新接收表状态
            luwReceive.set(ResponseReceivePO::getStatus, "已解除");
            int updateReceiveNum = responseReceiveMapper.update(null, luwReceive);
            if(updateReceiveNum == 0){
                log.warn("响应接收表解除失败，responseReleaseId： " + responseReleaseId);
            }
        } else if ("".equals(operateType) || operateType == null){
           //如果不是变更状态，那么就是变更其他字段
            luw.eq(ResponseReleasePO::getId, responseReleaseId);
            responseReleasePO = BeanCopyUtils.copy(responseChangeDTO, ResponseReleasePO.class);
            resultUpdate = responseReleaseMapper.update(responseReleasePO, luw);
        }
        if((resultUpdate > 0)){
            //要留存一份变更历史
            InsertResponseChangeLogDTO responseChangeLogDTO = BeanCopyUtils.copy(responseReleasePO, InsertResponseChangeLogDTO.class);
            responseChangeLogDTO.setResponseReleaseId(responseReleaseId);
            Boolean responseLog = iResponseChangeLogService.insertResponseLog(responseChangeLogDTO);
            if(responseLog){
                //日志插入成功后需要查询日志里头最大级别并更新下发表
                ResponseChangeLogQueryDTO responseChangeLogQueryDTO = new ResponseChangeLogQueryDTO();
                responseChangeLogQueryDTO.setUserId(userId);
                responseChangeLogQueryDTO.setResponseReleaseId(responseReleaseId);
                List<ResponseChangeLogPO> responseChangeLogPOs = iResponseChangeLogService.queryLogList(responseChangeLogQueryDTO);
                int highestLevel = 4;
                LocalDateTime maxLevelTime = null;
                for(ResponseChangeLogPO responseChangeLogPO : responseChangeLogPOs){
                    int currentLevel = Integer.parseInt(responseChangeLogPO.getCurrentLevel());
                    if (currentLevel < highestLevel) {
                        highestLevel = currentLevel;
                        maxLevelTime = responseChangeLogPO.getCreateTime();
                    }
                }
                ResponseReleasePO updateReleasePO = responseReleaseMapper.selectById(responseReleaseId);
                luw.eq(ResponseReleasePO::getId, responseReleaseId);
                if(maxLevelTime != null && highestLevel != 0){
                    updateReleasePO.setMaxLevelStartTime(maxLevelTime);
                    updateReleasePO.setMaxLevel(String.valueOf(highestLevel));
                }
                updateReleasePO.setCurrentLevelAdjustTime(LocalDateTime.now());
                int resultUpdateMaxTime = responseReleaseMapper.update(updateReleasePO, luw);
                if(resultUpdateMaxTime > 0){
                    //更新完了下发表还要更新接收表
                    luwReceive.eq(ResponseReceivePO::getResponseReleaseId, updateReleasePO.getId());
                    luwReceive.set(ResponseReceivePO::getTitle, updateReleasePO.getTitle());
                    luwReceive.set(ResponseReceivePO::getResponseContent, updateReleasePO.getResponseContent());
                    luwReceive.set(ResponseReceivePO::getTextContent, updateReleasePO.getTextContent());
                    luwReceive.set(ResponseReceivePO::getCurrentLevel, updateReleasePO.getCurrentLevel());
                    luwReceive.set(ResponseReceivePO::getCurrentLevelAdjustTime, updateReleasePO.getCurrentLevelAdjustTime());
                    luwReceive.set(ResponseReceivePO::getMaxLevel, updateReleasePO.getMaxLevel());
                    luwReceive.set(ResponseReceivePO::getMaxLevelStartTime, updateReleasePO.getMaxLevelStartTime());
                    int updateReceiveNum = responseReceiveMapper.update(null, luwReceive);
                    if(updateReceiveNum == 0){
                        log.warn("响应接收表更新失败，responseReleaseId： " + responseReleaseId);
                    }
                    return true;
                }else {
                    log.warn("after update response release level, update max level time failed, userid + id is : " + userId + " + " + responseReleaseId);
                }
            }
            log.warn("after update response release level, insert response log failed, userid + id is : " + userId + " + " + responseReleaseId);
            return false;
        }else if(resultSecure == 1){
            return true;
        }else {
            return false;
        }

    }

}
