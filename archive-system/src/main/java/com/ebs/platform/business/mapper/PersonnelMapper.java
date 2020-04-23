package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.PersonnelDO;
import com.ebs.platform.business.dto.PersonnelDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/5 10:36
 */
@Mapper(componentModel = "spring")
public interface PersonnelMapper {
    /**
     * @param personnelDO
     * @return
     */
    @Mappings({
            @Mapping(source = "enterprise.id",target = "entId"),
            @Mapping(source = "dept.id",target = "deptId"),
            @Mapping(source = "user.id",target = "accountId")
    })
    PersonnelDTO from(PersonnelDO personnelDO);

    /**
     * @param personnelDTO
     * @return
     */
    PersonnelDO to(PersonnelDTO personnelDTO);

    /**
     * @param personnelDOS
     * @return
     */
    List<PersonnelDTO> from(List<PersonnelDO> personnelDOS);

    /**
     * @param personnelDTOS
     * @return
     */
    List<PersonnelDO> to(List<PersonnelDTO> personnelDTOS);
}
