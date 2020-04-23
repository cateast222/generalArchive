package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.AccountDO;
import com.ebs.platform.business.dto.AccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mappings({
            @Mapping(source = "userName",target = "userName")
    })
    AccountDTO from(AccountDO accountDO);

    AccountDO to(AccountDTO accountDTO);

    List<AccountDTO> from(List<AccountDO> accountDOS);

    List<AccountDO> to(List<AccountDTO> accountDTOS);
}
