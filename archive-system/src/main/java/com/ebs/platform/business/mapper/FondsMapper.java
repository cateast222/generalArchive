package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.professionentity.Fonds;
import com.ebs.platform.business.dto.fonds.FondsDTOByPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper(componentModel = "spring")
public interface FondsMapper {
    @Mappings({
            @Mapping(source = "id",target = "id"),
            @Mapping(source = "code" ,target = "code"),
            @Mapping(source = "name" ,target = "name"),
            @Mapping(source = "description" ,target = "description")
    })
    public FondsDTOByPage from(Fonds entDO);

    /**
     * @param entDTO
     * @return
     */
    public Fonds to(FondsDTOByPage entDTO);

    /**
     * @param entDOS
     * @return
     */
    public List<FondsDTOByPage> from(List<Fonds> entDOS);

    /**
     * @param entDTOS
     * @return
     */
    public List<Fonds> to(List<FondsDTOByPage> entDTOS);
}
