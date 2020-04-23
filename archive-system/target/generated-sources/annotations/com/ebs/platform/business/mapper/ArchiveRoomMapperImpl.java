package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.professionentity.ArchiveRoom;
import com.ebs.platform.business.dto.archive.ArchiveRoomByAdd;
import com.ebs.platform.business.dto.archive.ArchiveRoomDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-12-03T17:06:49+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class ArchiveRoomMapperImpl implements ArchiveRoomMapper {

    @Override
    public ArchiveRoom DTOtoDO(ArchiveRoomByAdd roomDTO) {
        if ( roomDTO == null ) {
            return null;
        }

        ArchiveRoom archiveRoom = new ArchiveRoom();

        archiveRoom.setId( roomDTO.getId() );
        archiveRoom.setName( roomDTO.getName() );
        archiveRoom.setTheRank( roomDTO.getTheRank() );
        if ( roomDTO.getParentId() != null ) {
            archiveRoom.setParentId( Integer.parseInt( roomDTO.getParentId() ) );
        }
        archiveRoom.setTheRow( roomDTO.getTheRow() );
        archiveRoom.setTheColumn( roomDTO.getTheColumn() );
        archiveRoom.setRemark( roomDTO.getRemark() );
        archiveRoom.setAmount( roomDTO.getAmount() );

        return archiveRoom;
    }

    @Override
    public List<ArchiveRoomDTO> DOtoDTO(List<ArchiveRoom> roomDOS) {
        if ( roomDOS == null ) {
            return null;
        }

        List<ArchiveRoomDTO> list = new ArrayList<ArchiveRoomDTO>( roomDOS.size() );
        for ( ArchiveRoom archiveRoom : roomDOS ) {
            list.add( archiveRoomToArchiveRoomDTO( archiveRoom ) );
        }

        return list;
    }

    protected ArchiveRoomDTO archiveRoomToArchiveRoomDTO(ArchiveRoom archiveRoom) {
        if ( archiveRoom == null ) {
            return null;
        }

        ArchiveRoomDTO archiveRoomDTO = new ArchiveRoomDTO();

        archiveRoomDTO.setId( archiveRoom.getId() );
        archiveRoomDTO.setName( archiveRoom.getName() );
        archiveRoomDTO.setTheRank( archiveRoom.getTheRank() );
        archiveRoomDTO.setParentId( archiveRoom.getParentId() );
        archiveRoomDTO.setTheRow( archiveRoom.getTheRow() );
        archiveRoomDTO.setTheColumn( archiveRoom.getTheColumn() );
        archiveRoomDTO.setRemark( archiveRoom.getRemark() );
        archiveRoomDTO.setAmount( archiveRoom.getAmount() );

        return archiveRoomDTO;
    }
}
