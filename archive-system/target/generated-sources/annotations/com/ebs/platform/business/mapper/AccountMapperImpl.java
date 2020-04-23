package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.AccountDO;
import com.ebs.platform.business.dto.AccountDTO;
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
public class AccountMapperImpl implements AccountMapper {

    @Override
    public AccountDTO from(AccountDO accountDO) {
        if ( accountDO == null ) {
            return null;
        }

        AccountDTO accountDTO = new AccountDTO();

        accountDTO.setUserName( accountDO.getUserName() );
        accountDTO.setId( accountDO.getId() );
        accountDTO.setDeleted( accountDO.getDeleted() );
        accountDTO.setPassword( accountDO.getPassword() );
        accountDTO.setUserType( accountDO.getUserType() );

        return accountDTO;
    }

    @Override
    public AccountDO to(AccountDTO accountDTO) {
        if ( accountDTO == null ) {
            return null;
        }

        AccountDO accountDO = new AccountDO();

        accountDO.setId( accountDTO.getId() );
        accountDO.setDeleted( accountDTO.getDeleted() );
        accountDO.setUserName( accountDTO.getUserName() );
        accountDO.setPassword( accountDTO.getPassword() );
        accountDO.setUserType( accountDTO.getUserType() );

        return accountDO;
    }

    @Override
    public List<AccountDTO> from(List<AccountDO> accountDOS) {
        if ( accountDOS == null ) {
            return null;
        }

        List<AccountDTO> list = new ArrayList<AccountDTO>( accountDOS.size() );
        for ( AccountDO accountDO : accountDOS ) {
            list.add( from( accountDO ) );
        }

        return list;
    }

    @Override
    public List<AccountDO> to(List<AccountDTO> accountDTOS) {
        if ( accountDTOS == null ) {
            return null;
        }

        List<AccountDO> list = new ArrayList<AccountDO>( accountDTOS.size() );
        for ( AccountDTO accountDTO : accountDTOS ) {
            list.add( to( accountDTO ) );
        }

        return list;
    }
}
