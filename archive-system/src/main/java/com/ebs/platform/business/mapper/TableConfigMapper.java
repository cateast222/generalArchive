package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.professionentity.TableConfig;
import com.ebs.platform.business.dto.archive.TableConfigDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Created by liujie on 2019/11/7.
 */
@Mapper(componentModel = "spring")
public interface TableConfigMapper {

    @Mappings({
            @Mapping(source = "id",target = "id"),
            @Mapping(source = "columnName",target = "columnName"),
            @Mapping(source = "columnCode",target = "columnCode"),
            @Mapping(source = "columnType",target = "columnType"),
            @Mapping(source = "columnTypecode",target = "columnTypecode"),
            @Mapping(source = "typeCode",target = "typeCode"),
            @Mapping(source = "typeLevel",target = "typeLevel"),
            @Mapping(source = "minLength",target = "minLength"),
            @Mapping(source = "columnLength",target = "columnLength"),
            @Mapping(source = "isPrimary",target = "isPrimary"),
            @Mapping(source = "isForeign",target = "isForeign"),
            @Mapping(source = "isRequired",target = "isRequired"),
            @Mapping(source = "columnComponent",target = "columnComponent"),
            @Mapping(source = "columnDatasource",target = "columnDatasource"),
            @Mapping(source = "columnDict",target = "columnDict"),
            @Mapping(source = "cascadeData",target = "cascadeData"),
            @Mapping(source = "columnLimit",target = "columnLimit"),
            @Mapping(source = "defaultValue",target = "defaultValue"),
            @Mapping(source = "queryShow",target = "queryShow"),
            @Mapping(source = "listShow",target = "listShow"),
            @Mapping(source = "editShow",target = "editShow"),
            @Mapping(source = "editOrder",target = "editOrder"),
            @Mapping(source = "queryOrder",target = "queryOrder"),
            @Mapping(source = "listOrder",target = "listOrder"),
            @Mapping(source = "archiveType",target = "archiveType")
    })

    TableConfigDTO from(TableConfig tableConfig);

    TableConfig to(TableConfigDTO tableConifgDTO);

    List<TableConfigDTO> from(List<TableConfig> tableConfigs);

    List<TableConfig> to(List<TableConfigDTO> tableConifgDTOS);
}
