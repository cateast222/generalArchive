package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.professionentity.ArchiveBorrowItem;
import com.ebs.platform.business.dto.archive.ArchiveBorrowItemDto;
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
public class BorrowItemMapperImpl implements BorrowItemMapper {

    @Override
    public ArchiveBorrowItem DtoToDo(ArchiveBorrowItemDto archiveBorrowItemDto) {
        if ( archiveBorrowItemDto == null ) {
            return null;
        }

        ArchiveBorrowItem archiveBorrowItem = new ArchiveBorrowItem();

        if ( archiveBorrowItemDto.getBorrowRegisterId() != null ) {
            archiveBorrowItem.setBorrowRegisterId( Integer.parseInt( archiveBorrowItemDto.getBorrowRegisterId() ) );
        }
        if ( archiveBorrowItemDto.getArchiveItemId() != null ) {
            archiveBorrowItem.setArchiveItemId( Integer.parseInt( archiveBorrowItemDto.getArchiveItemId() ) );
        }

        return archiveBorrowItem;
    }

    @Override
    public List<ArchiveBorrowItem> DtoToDo(List<ArchiveBorrowItemDto> archiveBorrowItemDtoList) {
        if ( archiveBorrowItemDtoList == null ) {
            return null;
        }

        List<ArchiveBorrowItem> list = new ArrayList<ArchiveBorrowItem>( archiveBorrowItemDtoList.size() );
        for ( ArchiveBorrowItemDto archiveBorrowItemDto : archiveBorrowItemDtoList ) {
            list.add( DtoToDo( archiveBorrowItemDto ) );
        }

        return list;
    }
}
