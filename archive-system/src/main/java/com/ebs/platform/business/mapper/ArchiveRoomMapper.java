package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.professionentity.ArchiveRoom;
import com.ebs.platform.business.dto.archive.ArchiveRoomByAdd;
import com.ebs.platform.business.dto.archive.ArchiveRoomDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author lwy
 * @Date 2019-06-06 11:30
 */
@Mapper(componentModel = "spring")
public interface ArchiveRoomMapper {

    ArchiveRoom DTOtoDO(ArchiveRoomByAdd roomDTO);

    List<ArchiveRoomDTO> DOtoDTO(List<ArchiveRoom> roomDOS);
}
