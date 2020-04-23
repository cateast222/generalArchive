package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.professionentity.ArchiveIdentify;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liujie
 * @date 2019/11/28
 */

@Mapper(componentModel = "spring")
@Component
public interface ArchiveIdentifyMapper {

//    @Mappings({
//            @Mapping(source = "id",target = "id"),
//            @Mapping(source = "createTime",target = "createTime"),
//            @Mapping(source = "operator",target = "operator"),
//            @Mapping(source = "keepTime",target = "keepTime"),
//            @Mapping(source = "identifyPerson",target = "identifyPerson"),
//            @Mapping(source = "identifyDate",target = "identifyDate"),
//            @Mapping(source = "identifyReason",target = "identifyReason"),
//            @Mapping(source = "identifyExplain",target = "identifyExplain"),
//            @Mapping(source = "isDestroy",target = "isDestroy"),
//            @Mapping(source = "archiveId",target = "archiveId")
//    })
//    public ArchiveIdentifyDTO from(ArchiveIdentify appDO);
//
//    public ArchiveIdentify to(ArchiveIdentifyDTO appDTO);
//
//    public List<ArchiveIdentifyDTO> from(List<ArchiveIdentify> appDOS);
//
//    public List<ArchiveIdentify> to(List<ArchiveIdentifyDTO> appDTOS);
}
