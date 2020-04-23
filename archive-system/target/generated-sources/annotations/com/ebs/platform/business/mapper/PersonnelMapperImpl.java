package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.AccountDO;
import com.ebs.platform.business.domain.EntDO;
import com.ebs.platform.business.domain.EntDeptDO;
import com.ebs.platform.business.domain.PersonnelDO;
import com.ebs.platform.business.dto.PersonnelDTO;
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
public class PersonnelMapperImpl implements PersonnelMapper {

    @Override
    public PersonnelDTO from(PersonnelDO personnelDO) {
        if ( personnelDO == null ) {
            return null;
        }

        PersonnelDTO personnelDTO = new PersonnelDTO();

        String id = personnelDODeptId( personnelDO );
        if ( id != null ) {
            personnelDTO.setDeptId( id );
        }
        String id1 = personnelDOUserId( personnelDO );
        if ( id1 != null ) {
            personnelDTO.setAccountId( id1 );
        }
        String id2 = personnelDOEnterpriseId( personnelDO );
        if ( id2 != null ) {
            personnelDTO.setEntId( id2 );
        }
        personnelDTO.setId( personnelDO.getId() );
        personnelDTO.setDeleted( personnelDO.getDeleted() );
        personnelDTO.setName( personnelDO.getName() );
        personnelDTO.setSex( personnelDO.getSex() );
        personnelDTO.setTel( personnelDO.getTel() );
        personnelDTO.setEmail( personnelDO.getEmail() );
        personnelDTO.setBirthDate( personnelDO.getBirthDate() );

        return personnelDTO;
    }

    @Override
    public PersonnelDO to(PersonnelDTO personnelDTO) {
        if ( personnelDTO == null ) {
            return null;
        }

        PersonnelDO personnelDO = new PersonnelDO();

        personnelDO.setId( personnelDTO.getId() );
        personnelDO.setDeleted( personnelDTO.getDeleted() );
        personnelDO.setName( personnelDTO.getName() );
        personnelDO.setSex( personnelDTO.getSex() );
        personnelDO.setTel( personnelDTO.getTel() );
        personnelDO.setEmail( personnelDTO.getEmail() );
        personnelDO.setBirthDate( personnelDTO.getBirthDate() );

        return personnelDO;
    }

    @Override
    public List<PersonnelDTO> from(List<PersonnelDO> personnelDOS) {
        if ( personnelDOS == null ) {
            return null;
        }

        List<PersonnelDTO> list = new ArrayList<PersonnelDTO>( personnelDOS.size() );
        for ( PersonnelDO personnelDO : personnelDOS ) {
            list.add( from( personnelDO ) );
        }

        return list;
    }

    @Override
    public List<PersonnelDO> to(List<PersonnelDTO> personnelDTOS) {
        if ( personnelDTOS == null ) {
            return null;
        }

        List<PersonnelDO> list = new ArrayList<PersonnelDO>( personnelDTOS.size() );
        for ( PersonnelDTO personnelDTO : personnelDTOS ) {
            list.add( to( personnelDTO ) );
        }

        return list;
    }

    private String personnelDODeptId(PersonnelDO personnelDO) {
        if ( personnelDO == null ) {
            return null;
        }
        EntDeptDO dept = personnelDO.getDept();
        if ( dept == null ) {
            return null;
        }
        String id = dept.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String personnelDOUserId(PersonnelDO personnelDO) {
        if ( personnelDO == null ) {
            return null;
        }
        AccountDO user = personnelDO.getUser();
        if ( user == null ) {
            return null;
        }
        String id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String personnelDOEnterpriseId(PersonnelDO personnelDO) {
        if ( personnelDO == null ) {
            return null;
        }
        EntDO enterprise = personnelDO.getEnterprise();
        if ( enterprise == null ) {
            return null;
        }
        String id = enterprise.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
