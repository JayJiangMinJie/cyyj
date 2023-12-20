package com.geovis.cyyj.service.meeting;

import com.geovis.cyyj.common.core.domain.FileReturn;
import com.geovis.cyyj.common.core.domain.PageQuery;
import com.geovis.cyyj.common.core.page.TableDataInfo;
import com.geovis.cyyj.dto.FileQueryDTO;
import com.geovis.cyyj.dto.MeetingPeopleDTO;
import com.geovis.cyyj.vo.FileMediaReturn;
import com.geovis.cyyj.vo.FileVO;
import com.geovis.cyyj.vo.MeetingVO;
import org.springframework.web.multipart.MultipartFile;

public interface MeetingService {

    Boolean createOrDelete(MeetingPeopleDTO meetingPeopleDTO);

    /**
     * 查询参会人
     */
    MeetingVO pollingQuery(MeetingPeopleDTO meetingPeopleDTO);

}