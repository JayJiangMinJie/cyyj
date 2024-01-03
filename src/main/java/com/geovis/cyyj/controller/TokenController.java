package com.geovis.cyyj.controller;

import com.geovis.cyyj.common.media.RtcTokenBuilder2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@Api(value = "声网token相关接口", tags = "声网token相关接口")
@Slf4j
@Validated
@RequiredArgsConstructor
public class TokenController {

    @Value("${token.appId}")
    String appId;

    @Value("${token.appCertificate}")
    String appCertificate;

    static int tokenExpirationInSeconds = 3600;
    static int privilegeExpirationInSeconds = 3600;
    static int joinChannelPrivilegeExpireInSeconds = 3600;
    static int pubAudioPrivilegeExpireInSeconds = 3600;
    static int pubVideoPrivilegeExpireInSeconds = 3600;
    static int pubDataStreamPrivilegeExpireInSeconds = 3600;

    @GetMapping("/uid")
    @ApiOperation(value = "通过UID生成token", notes = "通过UID生成token")
    public String generateTokenWithUid(@RequestParam("channelName") String channelName,
                                       @RequestParam("uid") int uid) {
        RtcTokenBuilder2 token = new RtcTokenBuilder2();
        return token.buildTokenWithUid(appId, appCertificate, channelName, uid, RtcTokenBuilder2.Role.ROLE_SUBSCRIBER,
                tokenExpirationInSeconds, privilegeExpirationInSeconds);
    }

    @GetMapping("/account")
    @ApiOperation(value = "通过account生成token", notes = "通过account生成token")
    public String generateTokenWithAccount(@RequestParam("channelName") String channelName,
                                           @RequestParam("account") String account) {
        RtcTokenBuilder2 token = new RtcTokenBuilder2();
        return token.buildTokenWithUserAccount(appId, appCertificate, channelName, account, RtcTokenBuilder2.Role.ROLE_SUBSCRIBER,
                tokenExpirationInSeconds, privilegeExpirationInSeconds);
    }

    @GetMapping("/uidAndPrivilege")
    @ApiOperation(value = "通过UID和privilege生成token", notes = "通过UID和privilege生成token")
    public String generateTokenWithUidAndPrivilege(@RequestParam("channelName") String channelName,
                                                   @RequestParam("uid") int uid) {
        RtcTokenBuilder2 token = new RtcTokenBuilder2();
        return token.buildTokenWithUid(appId, appCertificate, channelName, uid, tokenExpirationInSeconds,
                joinChannelPrivilegeExpireInSeconds, pubAudioPrivilegeExpireInSeconds, pubVideoPrivilegeExpireInSeconds,
                pubDataStreamPrivilegeExpireInSeconds);
    }

    @GetMapping("/accountAndPrivilege")
    @ApiOperation(value = "通过account和privilege生成token", notes = "通过account和privilege生成token")
    public String generateTokenWithAccountAndPrivilege(@RequestParam("channelName") String channelName,
                                                       @RequestParam("account") String account) {
        RtcTokenBuilder2 token = new RtcTokenBuilder2();
        return token.buildTokenWithUserAccount(appId, appCertificate, channelName, account, tokenExpirationInSeconds,
                joinChannelPrivilegeExpireInSeconds, pubAudioPrivilegeExpireInSeconds,
                pubVideoPrivilegeExpireInSeconds, pubDataStreamPrivilegeExpireInSeconds);
    }
}