package com.ebs.platform.business.mapper;


import com.ebs.platform.business.domain.professionentity.ArchiveBorrowItem;
import com.ebs.platform.business.dto.archive.ArchiveBorrowItemDto;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface BorrowItemMapper {

    ArchiveBorrowItem DtoToDo(ArchiveBorrowItemDto archiveBorrowItemDto);

    List<ArchiveBorrowItem> DtoToDo(List<ArchiveBorrowItemDto> archiveBorrowItemDtoList);


}
