package com.ebs.platform.business.mapper;


import com.ebs.platform.business.domain.professionentity.BorrowApply;
import com.ebs.platform.business.dto.archive.BorrowApplyDto;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface BorrowApplyMapper {

    BorrowApply DtoToDo(BorrowApplyDto borrowApplyDto);

    BorrowApplyDto DoToDto(BorrowApply borrowApply);

    List<BorrowApplyDto> DoToDto(List<BorrowApply> borrowApply);

}
