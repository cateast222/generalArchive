package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.professionentity.BorrowApply;
import com.ebs.platform.business.dto.archive.BorrowApplyDto;
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
public class BorrowApplyMapperImpl implements BorrowApplyMapper {

    @Override
    public BorrowApply DtoToDo(BorrowApplyDto borrowApplyDto) {
        if ( borrowApplyDto == null ) {
            return null;
        }

        BorrowApply borrowApply = new BorrowApply();

        if ( borrowApplyDto.getId() != null ) {
            borrowApply.setId( Integer.parseInt( borrowApplyDto.getId() ) );
        }
        borrowApply.setBorrowTime( borrowApplyDto.getBorrowTime() );
        borrowApply.setReturnTime( borrowApplyDto.getReturnTime() );
        borrowApply.setActualTime( borrowApplyDto.getActualTime() );
        borrowApply.setReturnStatus( borrowApplyDto.getReturnStatus() );
        borrowApply.setBorrowType( borrowApplyDto.getBorrowType() );
        borrowApply.setBorrowPurpose( borrowApplyDto.getBorrowPurpose() );
        borrowApply.setBorrowRange( borrowApplyDto.getBorrowRange() );
        borrowApply.setBorrower( borrowApplyDto.getBorrower() );
        borrowApply.setUserName( borrowApplyDto.getUserName() );
        borrowApply.setUsePattern( borrowApplyDto.getUsePattern() );
        borrowApply.setBorrowExplain( borrowApplyDto.getBorrowExplain() );
        borrowApply.setApprovalName( borrowApplyDto.getApprovalName() );
        borrowApply.setApprovalFormUrl( borrowApplyDto.getApprovalFormUrl() );
        borrowApply.setUnitId( borrowApplyDto.getUnitId() );

        return borrowApply;
    }

    @Override
    public BorrowApplyDto DoToDto(BorrowApply borrowApply) {
        if ( borrowApply == null ) {
            return null;
        }

        BorrowApplyDto borrowApplyDto = new BorrowApplyDto();

        if ( borrowApply.getId() != null ) {
            borrowApplyDto.setId( String.valueOf( borrowApply.getId() ) );
        }
        borrowApplyDto.setBorrowTime( borrowApply.getBorrowTime() );
        borrowApplyDto.setReturnTime( borrowApply.getReturnTime() );
        borrowApplyDto.setActualTime( borrowApply.getActualTime() );
        borrowApplyDto.setReturnStatus( borrowApply.getReturnStatus() );
        borrowApplyDto.setBorrowType( borrowApply.getBorrowType() );
        borrowApplyDto.setBorrowPurpose( borrowApply.getBorrowPurpose() );
        borrowApplyDto.setBorrowRange( borrowApply.getBorrowRange() );
        borrowApplyDto.setBorrower( borrowApply.getBorrower() );
        borrowApplyDto.setUserName( borrowApply.getUserName() );
        borrowApplyDto.setUsePattern( borrowApply.getUsePattern() );
        borrowApplyDto.setBorrowExplain( borrowApply.getBorrowExplain() );
        borrowApplyDto.setApprovalName( borrowApply.getApprovalName() );
        borrowApplyDto.setApprovalFormUrl( borrowApply.getApprovalFormUrl() );
        borrowApplyDto.setUnitId( borrowApply.getUnitId() );

        return borrowApplyDto;
    }

    @Override
    public List<BorrowApplyDto> DoToDto(List<BorrowApply> borrowApply) {
        if ( borrowApply == null ) {
            return null;
        }

        List<BorrowApplyDto> list = new ArrayList<BorrowApplyDto>( borrowApply.size() );
        for ( BorrowApply borrowApply1 : borrowApply ) {
            list.add( DoToDto( borrowApply1 ) );
        }

        return list;
    }
}
